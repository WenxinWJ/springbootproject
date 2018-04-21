package com.springbootproject.service;

import com.springbootproject.domain.User;
import com.springbootproject.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author WenXin
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
public class UserService {
    @Resource(name = "userMapper")
    private UserMapper userMapper;

    // 增
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer addUser(User user) {
        return userMapper.addUser(user);
    }

    // 删
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer removeUser(Integer id) {
        return userMapper.removeUser(id);
    }

    // 改 enabled
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer modifyUser(User user) {
        return userMapper.modifyUser(user);
    }

    // id 改密码
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer changePassword(Integer id, String password) {
        return userMapper.changePassword(id, password);
    }

    // id 查
    public User findById(Integer id) {
        return userMapper.findById(id);
    }

    // 查询全部
    public List<User> find() {
        return userMapper.find();
    }

    //************************权限关联******************************


    // 权限关联 String 切割的用户
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer addUA(Integer userId, String authority) {
        int count = 0;
        //增加之前先删除
        userMapper.removeUA(userId);

        // 数组
        String a[] = authority.split(",");
        Integer[] authorityIds = new Integer[a.length];
        for (int i = 0; i < a.length; i++) {    // 循环写入关联的权限
            authorityIds[i] = Integer.parseInt(a[i]);
        }
        count = userMapper.addUA(userId, authorityIds);
        return count;
    }

    // 权限关联 数组增加
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer addUA(Integer userId, Integer[] authorityIds) {
        int count = 0;
        //增加之前先删除       不需要判断是否存在
        userMapper.removeUA(userId);

        // 数组
        if (authorityIds.length > 0) {
            count = userMapper.addUA(userId, authorityIds);
        }
        return count;
    }

    // userId 查询 得到权限的id
    public List<Integer> findAI(Integer userId) {
        return userMapper.findAI(userId);
    }
}
