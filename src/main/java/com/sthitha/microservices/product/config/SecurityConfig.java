//package com.sthitha.microservices.product.config;
//
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
// private String[] urls={"/swagger-ui.html","/v3/api-docs/**", "/swagger-ui/**", "/swagger-resources/**", "/webjars/**","/aggregate/**"};
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//        return http
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers(urls).permitAll()
//                        .anyRequest().authenticated()) // require authentication for all request
//                .httpBasic(basic -> basic //lambad for http basic authentication
//                .authenticationEntryPoint(((request, response, authException) -> {
//                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Unauthorized");
//                }))).build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager() throws Exception{
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        // add username and password
//        UserDetails productUser = org.springframework.security.core.userdetails.User
//                .withUsername("user")
//                .password(passwordEncoder().encode("pass"))
//                .build();
//        manager.createUser(productUser);
//        return manager;
//    }
//
//    @Bean
//    public WebMvcConfigurer configurer(){
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedOrigins("http://localhost:9000")
//                        .allowedMethods("GET","POST","PUT","DELETE","OPTIONS")
//                        .allowedHeaders("*")
//                        .allowCredentials(true);
//                WebMvcConfigurer.super.addCorsMappings(registry);
//            }
//        };
//    }
//}
