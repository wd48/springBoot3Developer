package org.xyd.springbootdeveloper.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.xyd.springbootdeveloper.service.UserDetailService;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@RequiredArgsConstructor
@Configuration
public class WebSecurityConfig {

    private final UserDetailService userService;

    /** spring security turn off
     * 인증 인가 서비스를 모든 곳에 적용하지 않는다. 정적 리소스(이미지, html)에 설정한다. 
     * 
     */
    
    @Bean
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring()
                .requestMatchers(toH2Console())
                .requestMatchers("/static/**");
    }

    /** configuring web-based security for specific HTTP requests
     * 특정 HTTP 요청에 대해 웹 기반 보안 구성
     * - 인증/인가 및 로그인, 로그아웃 관련 설정
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests()    // 인증, 인가설정
                // requestMatchers : 특정 요청과 일치하는 url에 대한 엑세스 설정,
                // permitAll : '/login', '/signup', '/user' 요청이 오면 인증/인가 없이도 접근할 수 있다
                .requestMatchers("/login", "/signup", "/user").permitAll()
                // anyRequest : 위에서 설정한 url 이외의 요청에 대해 설정
                // authenticated : 별도의 인가는 필요없지만, 인증이 성공한 상태여야 접근 가능
                .anyRequest().authenticated()
                .and()
                .formLogin()    // 폼 기반 로그인 설정
                // loginPage : 로그인 페이지 경로 설정
                .loginPage("/login")
                // defaultSuccessUrl : 로그인이 완료되었을 때 이동할 경로를 설정
                .defaultSuccessUrl("/articles")
                .and()
                .logout()   // 로그아웃 설정
                // logoutSuccessUrl : 로그아웃이 완료되었을 때 이동할 경로 설정 
                .logoutSuccessUrl("/login")
                // invalidateHttpSession : 로그아웃 이후에 세션을 전체 삭제할지 여부를 설정
                .invalidateHttpSession(true)
                .and()
                .csrf().disable()   // csrf 비활성화
                .build();
    }

    // 인증 관리자 관련 설정
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailService userDetailService) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                // userDetailsService : 사용자 정보를 가져올 서비스 설정, 반드시 UserDetailsService 상속 클래스
                .userDetailsService(userService)    // 사용자 정보 서비스 설정
                .passwordEncoder(bCryptPasswordEncoder)
                .and()
                .build();
    }

    // 패스워드 인코더로 사용할 빈 등록
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
