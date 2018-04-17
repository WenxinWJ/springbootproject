package com.springbootproject.security.controller;

import com.springbootproject.security.JwtTokenUtil;
import com.springbootproject.security.domain.JwtAuthenticationRequest;
import com.springbootproject.security.domain.JwtAuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 授权控制器(登录 | 刷新)
 *
 * @author WenXin
 */
@RestController
@RequestMapping("/api")
public class AuthenticationController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Resource
    private UserDetailsService userDetailsService;

    /**
     * 创建授权令牌(登录)
     *
     * @param authenticationRequest
     * @param device
     * @return
     * @throws AuthenticationException
     */
    @RequestMapping(value = "${jwt.router.authentication.path}", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(
            @RequestBody JwtAuthenticationRequest authenticationRequest,
            Device device) throws AuthenticationException { // Device 设备

        System.out.println(authenticationRequest.getUsername() + "  " + authenticationRequest.getPassword());

        // 封装用户名和密码到授权令牌
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUsername(), authenticationRequest.getPassword());

        // Perform the security
        final Authentication authentication = authenticationManager.authenticate(upToken);
        // 清除上下文，重新设置   authentication 证明
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-security so we can generate token   Reload 重新  generate 产生
        final UserDetails userDetails = userDetailsService.loadUserByUsername(
                authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails, device);

        // return the token
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }
}
