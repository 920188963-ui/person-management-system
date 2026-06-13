package org.example.companyusersystem.controller;

import jakarta.servlet.http.HttpSession;
import org.example.companyusersystem.entity.SysUser;
import org.example.companyusersystem.service.AuthService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
//controller+responsebody=把返回的内容变成json
@RequestMapping("/api")
//所有接口加统一前缀/api
public class AuthController {
    //定义业务层对象
    private  final AuthService authService;
    public AuthController(AuthService authService){
        this.authService=authService;
    }
    //登录接口
    @PostMapping("/login")
    public Map<String,Object> login(@RequestParam String username, @RequestParam String password, HttpSession session){
        //创建Map，返回封装结果
        Map<String,Object> res =new HashMap<>();
        //调用业务层方法
        SysUser user=authService.login(username,password);
        //登录失败
        if (user == null){
            res.put("success",false);
            res.put("msg","用户名或密码错误");
            return res;
        }
        //登录成功，用户信息存到session
        session.setAttribute("loginUser",user);
        res.put("success",true);
        //角色返回给前端，前端就可以知道当前用户身份
        res.put("role",user.getRole());
        res.put("msg","登录成功");
        return res;
    }
    @PostMapping("/register")
    public Map<String,Object> register(@RequestParam String username,@RequestParam String password){
        Map<String,Object> res =new HashMap<>();
        //判定用户名密码是否为空/ .trim()去掉前后空格
        if(username == null || username.trim().isEmpty()){
            res.put("success",false);
            res.put("msg","用户名不能为空");
            return res;
        }
        if(password == null || password.trim().isEmpty()){
            res.put("success",false);
            res.put("msg","密码不能为空");
            return res;
        }
        boolean ok=authService.register(username,password);
        if(!ok){
            res.put("success",false);
            res.put("msg","注册失败，用户名已存在");
            return res;
        }
        res.put("success",true);
        res.put("msg","注册成功。请重新登录");
        return res;
    }
    @PostMapping("/logout")
    public Map<String,Object> logout(HttpSession session){
        //删除session，清空登录状态
        session.invalidate();
        Map<String,Object> res =new HashMap<>();
        res.put("success",true);
        res.put("msg","已退出登录");
        return res;
    }

    @RequestMapping(value = "/currentUser", method = {RequestMethod.GET, RequestMethod.POST})
    public Map<String,Object> currentUser(HttpSession session){
        Map<String,Object> res = new HashMap<>();
        SysUser user = (SysUser) session.getAttribute("loginUser");
        if (user == null) {
            res.put("success", false);
            res.put("msg", "未登录");
            return res;
        }
        res.put("success", true);
        res.put("username", user.getUsername());
        res.put("role", user.getRole());
        return res;
    }

}
