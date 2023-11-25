//package com.example.admin.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class LoginSecurity {
//    @Autowired
//    CustomSuccessHandler customSuccessHandler;
//
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
//    }
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//        http.authorizeHttpRequests(configurer->
//                        configurer
//
//
//
////                                .requestMatchers("/*", "/signup/*", "/registration/*" ).permitAll()
//                                .requestMatchers("/admin/**").hasAuthority("admin")
//                                .requestMatchers("/css/**", "/imgs/**", "/js/**", "/fonts.material-icon/**", "/sass/**").permitAll()
//
//                                .anyRequest().authenticated()
//
//
//                )
//                .sessionManagement(session -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
//                        .invalidSessionUrl("/")
//                        .maximumSessions(1)
//                        .maxSessionsPreventsLogin(false)
//                )
//
//                .formLogin(form->
//                        form
//                                .loginPage("/login")
//                                .loginProcessingUrl("/authenticateTheUser")
//                                 .defaultSuccessUrl("/dashboard")
//
//                                .successHandler(customSuccessHandler)
//                                .permitAll()
//                )
//                .logout(LogoutConfigurer->
//                        LogoutConfigurer
//                                .logoutSuccessUrl("/login")
//                )
//                .exceptionHandling(configurer->
//                        configurer.accessDeniedPage("/dashboard/accessDenied"));
//
//
//        return http.build();
//    }
//}
