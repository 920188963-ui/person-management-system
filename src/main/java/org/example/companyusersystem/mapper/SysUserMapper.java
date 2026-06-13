package org.example.companyusersystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.companyusersystem.entity.SysUser;

@Mapper
public interface SysUserMapper {
    //根据用户名查询
    //@Param("username")命名为,xml直接写#{username}
    SysUser selectByUsername(@Param("username") String username);
    //插入新用户
    int insert(SysUser sysUser);
}