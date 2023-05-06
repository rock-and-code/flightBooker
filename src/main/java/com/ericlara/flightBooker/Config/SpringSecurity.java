package com.ericlara.flightBooker.Config;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.ericlara.flightBooker.Models.CustomLoginSuccessHandler;
import com.ericlara.flightBooker.Services.CustomUserDetailService;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

    @Autowired
    private CustomUserDetailService userDetailsService;

    @Autowired
    private DataSource dataSource;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/").permitAll() // SPECIFIES SPRING BOOT TO ALLOW ACCESS TO THIS PATH
                        .requestMatchers("/flights/**").permitAll() // SPECIFIES SPRING BOOT TO ALLOW ACCESS TO THIS
                                                                    // PATH
                        .requestMatchers("/h2-console/").permitAll() // SPECIFIES SPRING BOOT TO ALLOW ACCESS TO THIS
                                                                     // PATH
                        .requestMatchers("/api/v1/**").permitAll() // SPECIFIES SPRING BOOT TO ALLOW ACCESS TO THIS PATH
                                                                   // WITHOUT REQUESTING AUTHENTICATION
                        .requestMatchers("/register/**").permitAll() // SPECIFIES SPRING BOOT TO ALLOW ACCESS TO THIS
                                                                     // PATH WITHOUT REQUESTING AUTHENTICATION
                        .requestMatchers("/login/**").permitAll() // SPECIFIES SPRING BOOT TO ALLOW ACCESS TO THIS PATH
                                                                  // WITHOUT REQUESTING AUTHENTICATION
                        .anyRequest().authenticated())
                .authenticationProvider(authenticationProvider())
                .csrf().ignoringRequestMatchers(toH2Console()).and() // TO ALLOW US ACCESS TO THE H2 DATABASE CONSOLE
                .headers().frameOptions().disable().and() // TO ALLOW US ACCESS TO THE H2 DATABASE CONSOLE
                .formLogin((form) -> form
                        //.defaultSuccessUrl("/?successLogin", true) // SPECIFIES SRRINGBOOT THE PATH TO REDIRECT USER FOR
                        .successHandler(successHandler())                                         // SUCESSFULL LOGIN
                        .loginPage("/login")
                        .failureUrl("/login?error")
                        .loginProcessingUrl("/login")
                        .permitAll())
                .rememberMe().userDetailsService(userDetailsService).and()
                // .rememberMe().tokenRepository(persistentTokenRepository()).and() //To create
                // a cookie to remember user
                // .sessionManagement(session -> session
                // .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                // .invalidSessionUrl("/logout")
                // .maximumSessions(1)
                // .maxSessionsPreventsLogin(false))
                .logout((logout) -> logout
                        .deleteCookies("JSESSIONID", "dummyCookie")
                        // .logoutSuccessUrl("/login?logout")
                        // .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        // .logoutRequestMatcher(new AntPathRequestMatcher("/login?logout"))
                        // .logoutSuccessUrl("/login?logout")
                        .permitAll());

        // .exceptionHandling().accessDeniedPage("/access-denied");
        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userDetailsService); // To load user information

        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
        db.setDataSource(dataSource);
        return db;
    }

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return new CustomLoginSuccessHandler("/?successLogin");
    }

}
