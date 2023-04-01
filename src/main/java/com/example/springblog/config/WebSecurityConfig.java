package com.example.springblog.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final DataSource dataSource;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                 .authorizeHttpRequests(req -> req.requestMatchers(HttpMethod.GET, "/login").permitAll()
                         .requestMatchers(HttpMethod.GET, "/users/register").permitAll()
//                .authorizeHttpRequests(req -> req.requestMatchers(new AntPathRequestMatcher("/users/register", "/login")).permitAll()
//                .authorizeHttpRequests()
                .anyRequest().authenticated())
//                .formLogin()
//                .loginPage("/login")
//                .defaultSuccessUrl("/posts")
//                .failureUrl("/login?error")
//                .permitAll()
                .rememberMe()
                .rememberMeCookieName("remember-me-cookie")
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(24 * 60 * 60);
        return http.build();
    }

    PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }





//@Bean
//public SecurityFilterChain filterChain1(HttpSecurity http) throws Exception {
//
//    http
//            .csrf().disable()
//            .exceptionHandling()
//            .authenticationEntryPoint((request, response, authException) -> {
//                response.setHeader("WWW-Authenticate", "Basic realm=SignIn");
//            })
//            .and()
//            .sessionManagement()
//            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            .and()
//            .authorizeHttpRequests(authorize -> authorize.requestMatchers(HttpMethod.POST, "/auth/signup").permitAll()
//                    .requestMatchers(HttpMethod.GET, "/users/register").authenticated())
//            .authorizeHttpRequests(authorize -> authorize.requestMatchers("/users").hasRole("ADMIN")
//                    .requestMatchers("/items").hasAnyRole("ADMIN", "USER")
//                    .anyRequest().authenticated())
//    //.httpBasic()
//    ;
////    http.addFilter(new BasicAuthenticationFilter(authManager));
//    return http.build();
//}

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
////                 .authorizeHttpRequests(req -> req.requestMatchers(HttpMethod.GET, "/user/register").permitAll())                .
////                .authorizeHttpRequests(req -> req.requestMatchers(new AntPathRequestMatcher("/users/register")).permitAll()
//                .authorizeHttpRequests()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .defaultSuccessUrl("/posts")
//                .failureUrl("/login?error")
//                .permitAll()
//                .and()
//                .logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/login?logout")
//                .permitAll();
//        return http.build();
//    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
