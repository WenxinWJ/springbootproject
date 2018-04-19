package com.springbootproject.domain;

/**
 * user and authority 的连接类
 */
public class UserAuthority {
    private Integer userId;
    private Integer authorityId;

    public UserAuthority() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Integer authorityId) {
        this.authorityId = authorityId;
    }
}
