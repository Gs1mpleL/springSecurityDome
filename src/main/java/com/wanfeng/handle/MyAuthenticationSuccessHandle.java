package com.wanfeng.handle;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 重写成功返回的方法
 * @author wanfeng
 * @created 2022/3/13 0:29
 * @package com.wanfeng.handle
 */
public class MyAuthenticationSuccessHandle implements AuthenticationSuccessHandler {
    private final String url;

    public MyAuthenticationSuccessHandle(String url) {
        this.url = url;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 可以使用authentication来获取User对象
        User user = (User) authentication.getPrincipal();
        System.out.println(user.getUsername());
        // 为了安全password会变成null
        System.out.println(user.getPassword());
        System.out.println(user.getAuthorities());
        response.sendRedirect(url);
    }
}
