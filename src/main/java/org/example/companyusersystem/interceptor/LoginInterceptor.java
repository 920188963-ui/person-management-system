package org.example.companyusersystem.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.companyusersystem.entity.SysUser;
import org.springframework.web.servlet.HandlerInterceptor;

//登录拦截器，先执行拦截器再访问控制器
public class LoginInterceptor  implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取Session
        SysUser user= (SysUser) request.getSession().getAttribute("loginUser");
        if(user==null){
            response.setStatus(401);//401意为未登录，未授权；null即为未登录
            return false;
        }
        return true;
        //使用session判断登录用户速度快，若使用数据库判断反而会变慢
    }
}
