package com.example.example1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class ProjectSecurityConfig {
    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.ignoringRequestMatchers("/saveMsg"))
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/dashboard").authenticated() //for dashboard
                        .requestMatchers("/login").permitAll() //for login page to be visible for all
                        .requestMatchers("/logout").permitAll()
                        .requestMatchers("/","/home").authenticated()
                        .requestMatchers("/holidays/**").permitAll()
                        .requestMatchers("/contact").permitAll()
                        .requestMatchers("/saveMsg").permitAll()
                        .requestMatchers("/about").permitAll()
                        .requestMatchers("/assets/**").permitAll()
                )
                .formLogin(form -> form.loginPage("/login")
                        .defaultSuccessUrl("/dashboard")
                        .failureUrl("/login?error=true")
                        .permitAll())
                //these are of no use as csrf defaultly blocks it
//                .logout(logout -> logout.logoutSuccessUrl("/login?logout=true")
//                        .invalidateHttpSession(true).permitAll())
                .httpBasic(withDefaults());
        return http.build();
    }


//    store multipler users info
//    public InMemoryUserDetailsManager userDetailsManager(){
//        UserDetails admin = User.withDefaultPasswordEncoder()
//                .username("admin")
//                .password("12345")
//                .roles("ADMIN","USER")
//                .build();
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("12345")
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(user,admin);
//    }

//    LATEST METHOD
@Bean
public UserDetailsService userDetailsManager() {
    UserDetails admin = User.builder()
            .username("admin")
            .password(passwordEncoder().encode("12345"))
            .roles("ADMIN", "USER")
            .build();

    UserDetails user = User.builder()
            .username("user")
            .password(passwordEncoder().encode("12345"))
            .roles("USER")
            .build();

    return new InMemoryUserDetailsManager(admin, user);
}

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
