package com.ericlara.flightBooker.Config;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

import javax.sql.DataSource;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
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

  // The custom user details service that provides access to user information
  @Autowired
  private CustomUserDetailService userDetailsService;

  // The data source that is used to store user information
  @Autowired
  private DataSource dataSource;

  // A bean that is used to configure the security filter chain
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    // Configure the security filter chain
    http
        // Allow all requests to the following paths
        .authorizeHttpRequests((requests) -> requests
            .requestMatchers("/").permitAll()
            .requestMatchers("/flights/**").permitAll()
            .requestMatchers("/h2-console/").permitAll()
            .requestMatchers("/api/v1/**").permitAll()
            .requestMatchers("/register/**").permitAll()
            .requestMatchers("/login/**").permitAll()
            // All other requests require authentication
            .anyRequest().authenticated()
        )
        // Configure the authentication provider
        .authenticationProvider(authenticationProvider())
        // Configure CSRF protection
        .csrf(csrf -> csrf.ignoringRequestMatchers(toH2Console()))
        .csrf(csrf -> csrf.ignoringRequestMatchers("/api/v1/**"))
        // Configure HTTP headers
        .headers(headers -> headers.frameOptions().disable())
        // Configure form login
        .formLogin((form) -> form
            // Set the success handler
            .successHandler(successHandler())
            // Set the login page
            .loginPage("/login")
            // Set the error page
            .failureUrl("/login?error")
            // Set the login processing URL
            .loginProcessingUrl("/login")
            // Allow all requests to the login page
            .permitAll())
        // Configure remember me
        .rememberMe(me -> me.userDetailsService(userDetailsService))
        // Configure logout
        .logout((logout) -> logout
            // Delete the JSESSIONID and dummyCookie cookies
            .deleteCookies("JSESSIONID", "dummyCookie")
            // Allow all requests to the logout page
            .permitAll());

    // Return the security filter chain
    return http.build();
  }

  // A bean that is used to create a DaoAuthenticationProvider
  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    // Create a DaoAuthenticationProvider
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

    // Set the password encoder
    authenticationProvider.setPasswordEncoder(passwordEncoder());

    // Set the user details service
    authenticationProvider.setUserDetailsService(userDetailsService);

    // Return the DaoAuthenticationProvider
    return authenticationProvider;
  }

  // A bean that is used to create an AuthenticationManager
  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
    // Return the AuthenticationManager from the AuthenticationConfiguration
    return authConfig.getAuthenticationManager();
  }

  // A bean that is used to create a BCryptPasswordEncoder
  @Bean
  public static PasswordEncoder passwordEncoder() {
    // Return a BCryptPasswordEncoder
    return new BCryptPasswordEncoder();
  }

  // A bean that is used to create a JdbcTokenRepositoryImpl
  @Bean
  public PersistentTokenRepository persistentTokenRepository() {
    // Create a JdbcTokenRepositoryImpl
    JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();

    // Set the data source
    db.setDataSource(dataSource);

    // Return the JdbcTokenRepositoryImpl
    return db;
  }

  // A method that is used to configure the AuthenticationManagerBuilder
  @Autowired
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    // Configure the AuthenticationManagerBuilder with the custom user details service and password encoder
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
  }

  // A bean that is used to create a CustomLoginSuccessHandler
  @Bean
  public AuthenticationSuccessHandler successHandler() {
    // Create a CustomLoginSuccessHandler
    return new CustomLoginSuccessHandler("/?successLogin");
  }

   // spring boot 2.x Spring Boot redirect port 8080 to 8443
	 //Source: https://mkyong.com/spring-boot/spring-boot-redirect-port-8080-to-8443/
	 //FOR THE RESTAPIT ANY POST, PUT, PATCH, AND DELETE OPERATION WILL BE REDIRECTED TO HTTPS GET
	 @Bean
	 public ServletWebServerFactory servletContainer() {
		 TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
			 @Override
			 protected void postProcessContext(Context context) {
				 SecurityConstraint securityConstraint = new SecurityConstraint();
				 securityConstraint.setUserConstraint("CONFIDENTIAL");
				 SecurityCollection collection = new SecurityCollection();
				 collection.addPattern("/*");
				 securityConstraint.addCollection(collection);
				 context.addConstraint(securityConstraint);
			 }
		 };
		 tomcat.addAdditionalTomcatConnectors(redirectConnector());
		 return tomcat;
	 }
 
	 private Connector redirectConnector() {
		 Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		 connector.setScheme("http");
		 connector.setPort(8080);
		 connector.setSecure(false);
		 connector.setRedirectPort(8443);
		 return connector;
	 }

}