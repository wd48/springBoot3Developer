package org.xyd.springbootdeveloper.controller.config.jwt;

import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.xyd.springbootdeveloper.config.JwtProperties;
import org.xyd.springbootdeveloper.config.TokenProvider;
import org.xyd.springbootdeveloper.domain.User;
import org.xyd.springbootdeveloper.repository.UserRepository;

import java.time.Duration;
import java.util.Date;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TokenProviderTest {
    @Autowired
    private TokenProvider tokenProvider;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * generateToken() 검증 테스트
     * - 토큰을 생성하는 메서드를 테스트한다
     */
    @DisplayName("generateToken() : 유저 정보와 만료기간을 전달해 토큰을 만들 수 있다")
    @Test
    void generateToken() {
        // given
        /* 토큰에 유저 정보를 추가하기 위한 테스트 유저 생성 */
        User testUser = userRepository.save(User.builder()
                .email("user@gmail.com")
                .password("test")
                .build());

        // when
        /* 토큰 제공자의 generateToken() 메서드를 호출해 토큰을 만든다 */
        String token = tokenProvider.generateToken(testUser, Duration.ofDays(14));

        // then
        /* 토큰 복호화 (jjwt)
        *  토큰을 만들 때 claims로 넣어둔 id값이 given 절에서 만든 유저 ID와 동일한지 확인
        */
        Long userId = Jwts.parser()
                .setSigningKey(jwtProperties.getSecretKey())
                .parseClaimsJws(token)
                .getBody()
                .get("id", Long.class);
    }

    /**
     * validToken() 검증 테스트
     * 토큰이 유효한 토큰인지 검증하는 메서드를 테스트
     */
    @DisplayName("validToken(): 만료된 토큰일 때에 유효성 검증에 실패한다")
    @Test
    void validToken_invalidToken() {
        // given
        /* 토큰 생성 (jjwt)
         * 이미 만료된 토큰으로 생성 : 1970년 1월 1일 ~ 현재 시간 - 1000Millis
         */
        String token = JwtFactory.builder()
                .expiration(new Date(new Date().getTime() - Duration.ofDays(7).toMillis()))
                .build()
                .createToken(jwtProperties);

        // when
        /* 토큰 유효성 검증 후 결과값 반환 : validToken() */
        boolean result = tokenProvider.validToken(token);

        // then
        /* 반환값 false 확인 */
        assertThat(result).isFalse();
    }

    @DisplayName("validToken(): 유효한 토큰일 때에 유효성 검증에 성공한다.")
    @Test
    void validToken_validToken() {
        // given
        /* 토큰 생성 (jjwt) */
        String token = JwtFactory.withDefaultValues()
                .createToken(jwtProperties);

        // when
        /* 토큰 유효성 검증 후 결과값 반환 : validToken() */
        boolean result = tokenProvider.validToken(token);

        // then
        /* 반환값 true 확인 */
        assertThat(result).isTrue();
    }

    /**
     * getAuthentication() 검증 테스트
     * 토큰을 전달받아 인증 정보를 담은 객체 Authentication을 반환하는 메서드를 테스트
     */
    @DisplayName("getAuthentication(): 토큰 기반으로 인증 정보를 가져올 수 있다.")
    @Test
    void getAuthentication() {
        // given
        /* 토큰 생성 (jjwt)
        * - subject "user@email.com" */
        String userEmail = "user@email.com";
        String token = JwtFactory.builder()
                .subject(userEmail)
                .build()
                .createToken(jwtProperties);

        // when
        /* 인증 객체 반환 (getAuthentication() 호출) */
        Authentication authentication = tokenProvider.getAuthentication(token);

        // then
        /* 반환받은 인증 객체의 유저 이름을 가져와 subject("user@email.com") 와 동일한지 확인 */
        assertThat(((UserDetails) authentication.getPrincipal()).getUsername()).isEqualTo(userEmail);
    }

    /**
     * getUserId() 검증 테스트
     * 토큰 기반으로 유저 ID를 가져오는 메서드를 테스트
     * :토큰을 properties 파일에 저장한 비밀값으로 복호화한 뒤 클레임을 가져오는 getClaims() 호출
     * -> 클레임 정보를 반환받아 클레임에서 id 키로 저장된 값을 가져와 반환
     */
    @DisplayName("getUserId(): 토큰으로 유저 ID를 가져올 수 있다")
    @Test
    void getUserId() {
        // given
        /* 토큰 생성 (jjwt) + 클레임 추가(key : "id", value : userId) */
        Long userId = 1L;
        String token = JwtFactory.builder()
                .claims(Map.of("id", userId))
                .build()
                .createToken(jwtProperties);

        // when
        /* 유저 ID return (getUserId()) */
        Long userIdByToken = tokenProvider.getUserId(token);

        // then
        /* 반환받은 유저ID(userIdByToken)와 설정한 유저ID와 동일한지 확인 */
        assertThat(userIdByToken).isEqualTo(userId);
    }
}
