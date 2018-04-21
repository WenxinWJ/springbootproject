package com.springbootproject.mapper;

import com.springbootproject.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    // 增
    @Insert("insert into user(username,password,email,enabled,last_password_reset_date,login_time)" +
            "  values(#{username},#{password},#{email},#{enabled},#{lastPasswordResetDate},#{loginDate})")
    Integer addUser(User user);

    // 删    一般不删除(数据删除 一般是修改用户的enable 状态)
    @Delete("delete from user where id = #{id}")
    Integer removeUser(Integer id);

    // 改
    @Update("update user set email=#{email},enabled=#{enabled} where id=#{id}")
    Integer modifyUser(User user);

    // 修改密码
    @Update("update user set password =#{password},last_password_reset_date=now() where id = #{id}")
    Integer changePassword(@Param("id") Integer id,
                           @Param("password") String password);

    // 全局查询
    @Select("select id,username,email,enabled,last_password_reset_date,login_time from user")
    @Results({
            @Result(property = "lastPasswordResetDate", column = "last_password_reset_date"),
            @Result(property = "loginDate", column = "login_time")
    })
    List<User> find();

    // id 查询
    @Select("select id,username,email,enabled,last_password_reset_date lastPasswordResetDate,login_time loginDate from user where id=#{id}")
    User findById(Integer id);

    // username 查询   权限管理
    User findByUsername(@Param("username") String username);

    // ********************************************权限关联****************************************

    // 增
    //@Insert("insert into user_authority(user_id,authority_id) values(#{userId},#{authorityId})")
    Integer addUA(@Param("userId") Integer userId,
                  @Param("authorityIds") Integer[] authorityIds);

    // 删
    @Delete("delete from user_authority where user_id = #{userId}")
    Integer removeUA(Integer userId);

    // userId 查询 得到权限的id
    @Select("select authority_id from user_authority where user_id = #{userId}")
    List<Integer> findAI(Integer userId);
}
