package com.cloud.study.security.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @description: 定义登录授权对象
 * @author: dqq
 * @date: 2020/9/28 12:39
 */
@Data
public class AuthorizationUser implements UserDetails, Serializable {

    private final Integer id;
    private final String username;
    private final String password;
    private final List<RoleDto> roleDtoList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }


}
