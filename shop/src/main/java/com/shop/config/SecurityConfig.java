package com.shop.config;

import com.shop.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

    /*@Override
    //설정을 추가하지 않으면 더 이상 요청에 인증을 요구하지 않음
    protected void configure(HttpSecurity http) throws Exception {

    }*/
//    @Bean
//    AuthenticationManager authenticationManager(
//            HttpSecurity http) throws Exception {
//        return null;
//    }

    @Autowired
    MemberService memberService;
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//        http
//                .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
//                        .requestMatchers(new AntPathRequestMatcher("/**")).permitAll());
        http.formLogin((formLogin) ->
                formLogin
                    .loginPage("/members/login")
                    .defaultSuccessUrl("/")
                    .usernameParameter("email")
                    .failureUrl("/members/login/error")
                    )
                    .logout((logoutConfig) ->
                            logoutConfig
                                .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))
                                .logoutSuccessUrl("/")
                        )
                    .authorizeRequests((authorizedRequests) ->
                                authorizedRequests
                                    .requestMatchers("/css/**","/js/**","/img/**").permitAll()
                                    .requestMatchers("/","members/**","/item/**","/images/**").permitAll()
                                    .requestMatchers("/admin/**").hasRole("ADMIN")
                                    .anyRequest().authenticated()
                    )
                    .exceptionHandling((exceptionConfig) ->
                            exceptionConfig
                                    .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                    );

        return http.build();


    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
