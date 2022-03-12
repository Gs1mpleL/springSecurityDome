package com.wanfeng.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wanfeng
 * @create 2022/3/12 16:53
 * @package com.wanfeng.controller
 */
@Controller
public class LoginController {
    @RequestMapping("/login")
    public String login(){
        System.out.println("执行登录页面");
        return "redirect:main.html";
    }

    /**
     * 必须有角色才能访问
     */
    @Secured("ROLE_abc")
    @RequestMapping("/tomain")
    public String tomain(){
        System.out.println("执行登录页面");
        return "redirect:main.html";
    }
}
