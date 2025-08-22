package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers( "/login","/logout").permitAll() // /loginのみアクセスを許可
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth -> oauth
                        .loginPage("/login")  // 自作のログインページ
                        .defaultSuccessUrl("/")  // ログイン成功後のリダイレクト先
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")  // ログアウトURL
                        .logoutSuccessUrl("/login")  // ログアウト成功後のリダイレクト先
                        .invalidateHttpSession(true)  // セッションを無効化
                        .deleteCookies("JSESSIONID")  // Cookieを削除
                )
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/logout") // ログアウトのみCSRF無効化
                );

        return http.build();
    }
}