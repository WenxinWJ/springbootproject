package com.springbootproject.mapper;

import com.springbootproject.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    User findByUsername(@Param("username") String username);
}
