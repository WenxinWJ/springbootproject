package com.springbootproject.controller;

import com.springbootproject.security.JwtTokenUtil;
import com.springbootproject.security.domain.JwtUser;
import com.springbootproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 获取已授权用户信息
 *
 * @author WenXin
 */
@RestController
@RequestMapping("/api")
public class UserRestController {

    @Value("${jwt.header}")   // 配置文件中的 header
    private String tokenHeader;

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Resource
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    // 加密
    @Resource
    private PasswordEncoder passwordEncoder;

    // UserService
    @Resource(name = "userService")
    private UserService userService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public JwtUser getAuthenticatedUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader).substring(7); // 获取令牌
        String username = jwtTokenUtil.getUsernameFromToken(token); // 根据零牌获取用户名
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);   // 得到user 类
        return user;
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    public ResponseEntity<?> changePassword(
            @RequestParam("password") String password, HttpServletRequest request
    ) {
        System.out.println("加密前" + password);
        password = passwordEncoder.encode(password);
        System.out.println("加密后" + password);

        String token = request.getHeader(tokenHeader).substring(7); // 获取令牌
        String username = jwtTokenUtil.getUsernameFromToken(token); // 根据零牌获取用户名
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);   // 得到user 类

        System.out.println("id" + user.getId());
//$2a$10$VuR5siSNs72B/1RNTA6uaeHlOycx0Elt7/C8TdPEa7ZGDQUZ1jzlS
        Integer count = userService.changePassword(user.getId(), password);
        return ResponseEntity.ok(count);
    }

}
