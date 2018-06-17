package com.cflab.utils;

import com.cflab.domain.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //1.判断用户是否登录或者session是否过期，父类无getSession()使用子类方法获取session
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpSession session =  req.getSession();
        //获取登录时session保存的用户信息
        User user = (User) session.getAttribute("userInfo");

        //        //2.获取用户请求地址
        String url = req.getRequestURI();
        if (user!=null) {//用户已登录，则继续执行
            filterChain.doFilter(servletRequest, servletResponse);
        }else if (url.contains("/login.jsp")){//防止刚进登录界面时，此时没有user，会发生过滤循环
            filterChain.doFilter(servletRequest, servletResponse);
        }else if (url.contains("/resource")){//避免访问登录界面样式文件被过滤
            filterChain.doFilter(servletRequest, servletResponse);
        }else if (url.contains("/doLogin")){//未登录时，要访问@web注解地址，此时不能过滤
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            //如果用户不存在或者session过时，那么通过过滤页面跳转login页面
            req.getRequestDispatcher("/view/loginFilter.jsp").forward(servletRequest, servletResponse);
        }


    }

    @Override
    public void destroy() {

    }
}
