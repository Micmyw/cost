package com.cflb;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.logging.LogRecord;

public class LogFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) {
        String site=filterConfig.getInitParameter("Site");

        System.out.println("网站名:"+site);
    }
    //    public void init(FilterConfig config) throws ServletException{
//        //获取初始参数
//        String site=config.getInitParameter("Site");
//
//        //输出初始化参数
//        System.out.println("网站名:"+site);
//    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        输出站点名
        System.out.println("站点网站:http://www.baidu,com");

        //把请求返回过滤链
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws
//            java.io.IOException, ServletException{
//
//        //输出站点名
//        System.out.println("站点网站:http://www.baidu,com");
//
//        //把请求返回过滤链
//        chain.doFilter(request, response);
//    }

//    public void detroy(){}
}
