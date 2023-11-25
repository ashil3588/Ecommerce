package com.example.customer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Properties;

@EnableWebSecurity
@Configuration
public class CustomerSecurityConfig {

    @Autowired
    CustomSuccessHandler customSuccessHandler;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//        http.authorizeHttpRequests(configurer->
//                        configurer
//
//                                .requestMatchers("/**","/about","/contact","/productDetails").permitAll()
//                                .requestMatchers("/css/**","/fonts/**","/images/**","/js/**","/login/**","/register/**").permitAll()
//
//                                .anyRequest().authenticated()
//
//
//
//                )
//
//                .formLogin(form->
//                        form
//                                .loginPage("/login")
//                                .loginProcessingUrl("/authenticateTheUser")
//                                .successHandler(customSuccessHandler)
//                                .permitAll()
//
//                )
//                .sessionManagement(session -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
//                        .invalidSessionUrl("/")
//                        .maximumSessions(1)
//                        .maxSessionsPreventsLogin(false)
//
//
//
//                )
//
//
//                .logout(LogoutConfigurer->
//                        LogoutConfigurer
//                                .logoutSuccessUrl("/login")
//
//                )
//                .csrf(AbstractHttpConfigurer::disable);
//
//
//
//        return http.build();
//    }

        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers("/css/**", "/imgs/**", "/js/**", "/fonts/**", "/register/**", "/do-register/**", "/**", "/address").permitAll()
//                                .requestMatchers("/shop/**").hasAuthority("User")
                                .requestMatchers("/login", "/do-login", "/verify-otp").permitAll()
                                .requestMatchers("/dashboard").authenticated()
                                .anyRequest().authenticated()
                )
                .formLogin(
                        form -> form
                                .loginPage("/login")
                                .loginProcessingUrl("/do-login")
                                .defaultSuccessUrl("/dashboard")
                                .successHandler(customSuccessHandler)
//                                .failureHandler(loginFailureHandler)
                                .permitAll()
                )
                .logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .invalidateHttpSession(true)
                                .permitAll()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                        .invalidSessionUrl("/login?logout")
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(false));
        return http.build();
    }
}
