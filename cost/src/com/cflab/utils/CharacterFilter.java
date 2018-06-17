package com.cflab.utils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
@WebFilter("/*")
public class CharacterFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        servletRequest.setCharacterEncoding("UTF-8");

        servletResponse.setCharacterEncoding("UTF-8");

        servletResponse.setContentType("text/html;charset=UTF-8");

        filterChain.doFilter(servletRequest, servletResponse );
    }
}
