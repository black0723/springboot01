package com.bysj.servletcomponent;

import com.bysj.beans.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /*User user = (User) request.getSession().getAttribute("user");
        if (null == user) {
            response.sendRedirect("nonlogin");
            return false;
        }*/
        return true;
    }
}
