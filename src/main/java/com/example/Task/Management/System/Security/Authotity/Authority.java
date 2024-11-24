package com.example.Task.Management.System.Security.Authotity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;


@Data
public class Authority implements GrantedAuthority {

    public Authority(Role role) {
        this.setRole(role);
    }

    private Role role;

    @Override
    public String getAuthority() {
        return role.name();
    }
}
