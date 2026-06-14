package org.example.companyusersystem.entity;

public class SysUser {
    int id;
    String username;
    String password;
    String realName;
    String phone;
    String email;
    String role;
    boolean status;

    public SysUser() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public SysUser(int id, String username, String password, String realName, String phone, String email, String role, boolean status) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.realName = realName;
        this.phone = phone;
        this.email = email;
        this.role = role;
        this.status = status;
    }
}
