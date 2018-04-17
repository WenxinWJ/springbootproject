package com.springbootproject.security.controller;

import com.springbootproject.security.JwtTokenUtil;
import com.springbootproject.security.domain.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Autowired
  @Qualifier("jwtUserDetailsService")
  private UserDetailsService userDetailsService;

  @RequestMapping(value = "/user", method = RequestMethod.GET)
  public JwtUser getAuthenticatedUser(HttpServletRequest request) {
    String token = request.getHeader(tokenHeader).substring(7); // 获取令牌
    String username = jwtTokenUtil.getUsernameFromToken(token); // 根据零牌获取用户名
    JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);   // 得到user 类
    return user;
  }

}
