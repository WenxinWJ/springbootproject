package com.springbootproject.service;

import com.springbootproject.domain.User;
import com.springbootproject.mapper.UserAuthorityMapper;
import com.springbootproject.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author WenXin
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
public class UserService {
    @Resource(name = "userMapper")
    private UserMapper userMapper;

    @Resource(name = "userAuthorityMapper")
    private UserAuthorityMapper userAuthorityMapper;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer changePassword(Integer id, String password) {
        return userMapper.changePassword(id, password);
    }

}
