package com.wanfeng.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author wanfeng
 * @create 2022/3/12 17:14
 * @package com.wanfeng.service
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private PasswordEncoder pw;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loadUser被调用，username = " + username);

        // 模拟用户名，并与前端用户名做比较
        if(!"admin".equals(username)){
            throw new UsernameNotFoundException("用户名不存在");
        }
        // 模拟密码
        String psw = pw.encode("admin");

        // 返回一个UserDetails实现类 需要账户密码和权限  ROlE_xxx中xxx就表示角色
        return new User(username,psw, AuthorityUtils.commaSeparatedStringToAuthorityList("admin,ROLE_abc"));
    }
}
