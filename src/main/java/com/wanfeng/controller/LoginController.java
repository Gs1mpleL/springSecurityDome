package com.wanfeng.controller;

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
}
