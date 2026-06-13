package org.example.companyusersystem.service;

import org.example.companyusersystem.entity.SysUser;

public interface AuthService {
    SysUser login(String username, String password);

    boolean register(String username, String password);
}
