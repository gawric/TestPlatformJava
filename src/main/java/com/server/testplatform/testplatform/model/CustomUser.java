package com.server.testplatform.testplatform.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomUser extends User {
    private static final long serialVersionUID = 1L;

    public CustomUser(UserModel user) {
        //super(user.getUsername(), user.getPassword(), user.getGrantedAuthoritiesList());
        super(user.getUsername(), user.getPassword(), user.isEnabled(), user.isAccountNonExpired(), user.isCredentialsNonExpired(), user.isAccountNonLocked() , user.getGrantedAuthoritiesList());
    }
}
