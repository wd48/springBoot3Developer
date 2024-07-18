package org.xyd.springbootdeveloper.controller.config.jwt;

import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.xyd.springbootdeveloper.config.JwtProperties;
import org.xyd.springbootdeveloper.config.TokenProvider;
import org.xyd.springbootdeveloper.domain.User;
import org.xyd.springbootdeveloper.repository.UserRepository;

import java.time.Duration;

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
     */
    @DisplayName("generateToken() : 유저 정보와 만료기간을 전달해 토큰을 만들 수 있다")
    @Test
    void generateToken() {
        // given
        User testUser = userRepository.save(User.builder()
                .email("user@gmail.com")
                .password("test")
                .build());

        // when
        String token = tokenProvider.generateToken(testUser, Duration.ofDays(14));

        // then
        Long userId = Jwts.parser()
                .setSigningKey(jwtProperties.getSecretKey())
                .parseClaimsJws(token)
                .getBody()
                .get("id", Long.class);
    }

    /**
     * validToken() 검증 테스트
     */

    /**
     * getAuthentication() 검증 테스트
     */

    /**
     * getUserId() 검증 테스트
     */
}
