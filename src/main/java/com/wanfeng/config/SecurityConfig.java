package com.wanfeng.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Configuration 指示一个类声明一个或多个@Bean方法，并且可以由Spring容器处理，以便在运行时为这些bean生成BeanDefinition和服务请求
 * @author wanfeng
 * @create 2022/3/12 17:11
 * @package com.wanfeng.config
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public PasswordEncoder getPassWordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 表单提交
        http.formLogin()
                // 当发现是/login时认为是在登陆，必须和表单提交相同
                .loginProcessingUrl("/login")
                // 自定义表单页面
                .loginPage("/login.html");


        // 授权认证
        http.authorizeRequests()
                // 设置白名单
                .antMatchers("/login.html").permitAll()
                // 所有请求必须登录后才能访问
                .anyRequest().authenticated();

        // csrf防护关闭
        http.csrf().disable();
    }
}
