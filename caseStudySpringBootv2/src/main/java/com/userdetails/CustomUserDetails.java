package com.userdetails;

import com.entitymodels.PrivilegeEntity;
import com.entitymodels.RoleEntity;
import com.entitymodels.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private UserEntity user;

    public CustomUserDetails(UserEntity user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<RoleEntity> roles = (List<RoleEntity>) user.getRoles();
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (RoleEntity role : roles) {
            Collection<PrivilegeEntity> privileges = role.getPrivileges();
            for (PrivilegeEntity privilege : privileges) {
                authorities.add(new SimpleGrantedAuthority(privilege.getName()));
            }
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getFullName() {
        return user.getFullName();
    }
}
