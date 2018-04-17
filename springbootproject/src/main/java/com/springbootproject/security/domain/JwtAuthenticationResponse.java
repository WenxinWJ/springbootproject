package com.springbootproject.security.domain;

import java.io.Serializable;

/**
 * 已授权用户(令牌)响应给客户端
 *
 * @author WenXin
 */
public class JwtAuthenticationResponse implements Serializable {
    private static final long serialVersionUID = 7195813526133931291L;
    private final String token;// 令牌

    public JwtAuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
