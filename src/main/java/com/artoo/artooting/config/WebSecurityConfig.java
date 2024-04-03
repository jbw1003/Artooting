package com.artoo.artooting.config;

import com.artoo.artooting.api.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        /*http
                .authorizeHttpRequests(authz -> authz
                        // 여기서 공개적으로 접근할 수 있는 URL 패턴을 정의하세요
                        .requestMatchers("/", "/index", "/generic", "/list").permitAll()
                        // /admin/** 경로는 ADMIN 권한을 가진 사용자만 접근 가능
                        .requestMatchers("/admin/**").hasAuthority("ADMIN")
                        // 나머지 요청은 모두 인증을 요구함
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login") // 커스텀 로그인 페이지 경로
                        .defaultSuccessUrl("/dashboard", true) // 로그인 성공 후 리디렉션될 기본 경로
                        .permitAll() // 모든 사용자가 로그인 페이지 접근 허용
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout") // 로그아웃 성공 시 리디렉션될 경로
                        .permitAll()
                );*/
        http
                .authorizeHttpRequests(authz -> authz
                        // 여기서 공개적으로 접근할 수 있는 URL 패턴을 정의하세요
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/generic").permitAll()
                        .requestMatchers("/comments").permitAll()
                        .requestMatchers("/images/**").permitAll() // /images 경로에 있는 모든 리소스에 대한 접근 허용
                        .requestMatchers("/assets/**").permitAll() // /images 경로에 있는 모든 리소스에 대한 접근 허용
                        //.requestMatchers("/api/**").hasAuthority(Role.COMMON.getKey())
                        //.requestMatchers("/api/**").hasRole(Role.COMMON.getKey())
                        //.anyRequest().authenticated() // 그 외 모든 요청에 대해 인증 요구
                )
                .formLogin(form -> form
                        .loginPage("/login") // 커스텀 로그인 페이지 경로
                        .defaultSuccessUrl("/dashboard", true) // 로그인 성공 후 리디렉션될 기본 경로
                        .permitAll() // 모든 사용자가 로그인 페이지 접근 허용
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout") // 로그아웃 성공 시 리디렉션될 경로
                        .permitAll()
                );

        // CSRF 보호 기능을 비활성화하는 경우 (API 서버 등 특정 경우에만 사용)
        // http.csrf().disable();

        return http.build();
    }

    // PasswordEncoder Bean 정의
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
