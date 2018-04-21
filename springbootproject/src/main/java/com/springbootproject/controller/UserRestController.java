package com.springbootproject.controller;

import com.springbootproject.domain.User;
import com.springbootproject.security.JwtTokenUtil;
import com.springbootproject.security.domain.JwtUser;
import com.springbootproject.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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

    //
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public JwtUser getAuthenticatedUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader).substring(7); // 获取令牌
        String username = jwtTokenUtil.getUsernameFromToken(token); // 根据令牌获取用户名
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);   // 得到user 类
        return user;
    }

    // 修改密码
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

        Integer count = userService.changePassword(user.getId(), password);
        return ResponseEntity.ok(count);
    }

    // 权限关联 String 切割的用户
    @PostMapping("/Userauthority")
    public ResponseEntity<?> addUA(
            @RequestParam("userId") Integer userId, @RequestParam("authority") String authority) {
        System.out.println("userId" + userId + "  authority" + authority);
        Integer count = userService.addUA(userId, authority);
        return ResponseEntity.ok(count);
    }

    // 权限关联 数组增加
    @PostMapping("/Userauthoritys")
    public ResponseEntity<?> addUA(
            @RequestParam("userId") Integer userId, @RequestParam("authoritys") Integer[] authoritys) {
        System.out.println("*********Userauthoritys(权限关联 数组增加) userId:" + userId + "  authority :" + Arrays.toString(authoritys) + "****");
        Integer count = userService.addUA(userId, authoritys);
        return ResponseEntity.ok(1);
    }

    // userId 查询 得到权限的id
    @GetMapping("/findAI")
    public ResponseEntity<?> findAI(@RequestParam("userId") Integer userId) {
        System.out.println("****************findAI(userId 查询 得到权限的id)*userId:" + userId + "********************");

        List<Integer> authorityIds = userService.findAI(userId);

        return new ResponseEntity<>(authorityIds, HttpStatus.OK);
    }


    // **************************** restful ***************************
    // 增
    @RequestMapping(value = "/user", method = RequestMethod.POST)

    public ResponseEntity<?> addUser(@RequestBody User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setLastPasswordResetDate(new Date());
        user.setLoginDate(new Date());
        int count = userService.addUser(user);
        return ResponseEntity.ok(count);
    }

    // 改 enabled and email
    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public ResponseEntity<?> modifyUser(@RequestBody User user) {
        int count = userService.modifyUser(user);
        return ResponseEntity.ok(count);
    }

    // 查询全部
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<?> getUsers() {
        List<User> users = userService.find();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // id 查询
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable("id") Integer id) {
        User user = userService.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
