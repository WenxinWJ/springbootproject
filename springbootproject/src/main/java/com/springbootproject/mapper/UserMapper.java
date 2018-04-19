package com.springbootproject.mapper;

import com.springbootproject.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    // 增
    @Insert("insert into user(username,password,email,enable,last_password_reset_data,login_time)" +
            "  values(#{username},#{password},#{email},#{enabled},last_password_reset_data = now(),login_time=now())")
    Integer addUser(User user);

    // 删
    @Delete("delete from user where id = #{id}")
    Integer removeUser(Integer id);

    // 改
    @Update("update user set username = #{username},password=#{password}, " +
            "  email=#{email},enabled=#{enabled}")
    Integer modifyUser(User user);

    // 修改密码
    @Update("update user set password =#{password},last_password_reset_date=now() where id = #{id}")
    Integer changePassword(@Param("id") Integer id,
                           @Param("password") String password);

    // 全局查询
    List<User> find();

    // id 查询   权限管理
    User findByUsername(@Param("username") String username);
}
