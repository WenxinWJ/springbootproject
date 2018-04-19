package com.springbootproject.mapper;

import com.springbootproject.domain.UserAuthority;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthorityMapper {

    // 增
    @Insert("insert into user(user_id,authority_id) values(#{userId},#{authorityId})")
    Integer addUA(UserAuthority userAuthority);

    // 删
    @Delete("delete from user_authority where user_id = #{userId}")
    Integer removeUA(Integer userId);
}
