package com.springbootproject.security.service;

import com.springbootproject.domain.User;
import com.springbootproject.mapper.UserMapper;
import com.springbootproject.security.domain.JwtUserFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户详细信息服务类
 */
@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Resource(name = "userMapper")
    private UserMapper userMapper;

    /**
     * 从用户名可以查到用户
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return JwtUserFactory.create(user);
        }
    }
}
