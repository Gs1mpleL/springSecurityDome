package com.wanfeng;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class SpringSecurityDomeApplicationTests {

    @Test
    void contextLoads() {
        PasswordEncoder pe = new BCryptPasswordEncoder();
        String encode = pe.encode("123");



    }

}
