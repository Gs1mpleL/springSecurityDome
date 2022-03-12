package com.wanfeng.config;

import com.wanfeng.handle.MyAccessDeniedHandle;
import com.wanfeng.handle.MyAuthenticationSuccessHandle;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private MyAccessDeniedHandle myAccessDeniedHandle;
    @Bean
    public PasswordEncoder getPassWordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 表单提交
        http.formLogin()
                // 自定义参数名称
                .usernameParameter("username")
                .passwordParameter("password")
                // 当发现是/login时认为是在登陆，必须和表单提交相同
                .loginProcessingUrl("/login")
                // 自定义表单页面
                .loginPage("/login.html")
                // 成功的跳转页面  这里不能直接跳url，因为只接受post请求，直接跳url属于get请求 失败跳转同理
                .successForwardUrl("/tomain")
                .successHandler(new MyAuthenticationSuccessHandle("https://www.baidu.com"));

        // 授权认证
        http.authorizeRequests()
                // 设置白名单  设置放行
                .antMatchers("/login.html").permitAll()
                // 放行静态资源
                .antMatchers("/js/**,/css/**").permitAll()
                // 访问权限  有admin权限才可以访问
                .antMatchers("/main1.html").hasAuthority("admin")
                // 访问角色 有abc角色才能访问
                .antMatchers("/main1.html").hasRole("abc")
                // 访问ip
                .antMatchers("/main1.html").hasIpAddress("175.24.174.14")
                // 所有请求必须登录后才能访问
                .anyRequest().authenticated();

        // csrf防护关闭
        http.csrf().disable();

        http.exceptionHandling()
                .accessDeniedHandler(myAccessDeniedHandle);
    }
}
