package com.wanfeng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.userdetails.UserDetailsService;

@SpringBootApplication
public class SpringSecurityDomeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityDomeApplication.class, args);
    }
}
