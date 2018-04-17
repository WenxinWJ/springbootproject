package com.springbootproject.security.domain;

import com.springbootproject.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Jwt 用户工厂类
 *
 * @author WenXin
 */
public final class JwtUserFactory {
    private JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                mapToGrantedAuthority(user.getAuthorities()),
                user.getEnabled() == 1 ? true : false,
                user.getLastPasswordResetDate(),
                user.getLoginDate());
    }

    // 获取枚举中的权限名
    private static List<GrantedAuthority> mapToGrantedAuthority(List<Authority> authorities) {
        // authorities.stream().map 相当于for循环  authority 相当于 for循环中的类名
        return authorities.stream().map(authority ->
                new SimpleGrantedAuthority(authority.getName().name()))
                .collect(Collectors.toList());
// authorities.stream().map(authority ->new SimpleGrantedAuthority(authority.getName().name())) 拿到枚举中的name
    }
}
