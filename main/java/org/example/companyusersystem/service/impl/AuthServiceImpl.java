package org.example.companyusersystem.service.impl;

import org.example.companyusersystem.entity.SysUser;
import org.example.companyusersystem.mapper.SysUserMapper;
import org.example.companyusersystem.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    //构造器注入
    private final SysUserMapper sysUserMapper;
    //创建对象时初始化变量
    public AuthServiceImpl(SysUserMapper sysUserMapper) {
        this.sysUserMapper = sysUserMapper;
    }
    @Override
    public SysUser login(String username, String password) {
        //用户名查询
        SysUser user = sysUserMapper.selectByUsername(username);
        if(user == null){
            return null;
        }
        //判断密码
        if(!user.getPassword().equals(password)){
            return null;
        }
        return user;
    }

    @Override
    public boolean register(String username, String password) {
        //需要先判断用户名是否重复
        SysUser oldUser=sysUserMapper.selectByUsername(username);
        if(oldUser!=null){
            return false;
        }
        //创建新用户
        SysUser newUser=new SysUser();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setRole("User");
        //插入数据库
        return sysUserMapper.insert(newUser)>0;
    }
}

