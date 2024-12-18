package com.spring.security.authentication.config;

import org.springframework.context.annotation.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.*;
import org.springframework.security.provisioning.*;

@Configuration
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails user1=User.withUsername("user1")
                .password(passwordEncoder().encode("password1"))
                .roles("ADMIN")
                .build();

        UserDetails user2=User.withUsername("user2")
                .password(passwordEncoder().encode("password2"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user1, user2);

    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
