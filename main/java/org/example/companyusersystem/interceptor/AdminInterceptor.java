package org.example.companyusersystem.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.companyusersystem.entity.SysUser;
import org.springframework.web.servlet.HandlerInterceptor;

public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        SysUser user = (SysUser) request.getSession().getAttribute("loginUser");
        if (user == null) {
            response.setStatus(401);
            return false;
        }
        //判断登录用户是否为管理员
        boolean isAdmin = "admin".equalsIgnoreCase(user.getRole()) || "admin".equals(user.getUsername());
        if (!isAdmin) {
            response.setStatus(403);//没有权限
            return false;
        }
        return true;
    }
}
