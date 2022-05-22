package com.whut.bandou.filter;

import com.whut.bandou.bean.Admin;
import com.whut.bandou.bean.User;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
@WebFilter(urlPatterns = "/*", filterName = "userFilter", initParams = {
        @WebInitParam(name = "URL", value = "http://localhost:8080")})
public class UserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession httpSession = request.getSession();
        User user = (User) httpSession.getAttribute("user");
        Admin admin = (Admin) httpSession.getAttribute("admin");
        String uri = request.getRequestURI();

        if (uri.contains("admin") || admin != null) {
            filterChain.doFilter(request, response);
        }

//        if (user == null && ( false
////                uri.contains("home")
////                        || uri.contains("toBookManage")
////                        || uri.contains("reply")
////                        || uri.contains("comment")
//        )) {
//            System.out.println("user-filter---uri:   " + uri);
//            response.sendRedirect("../");
//        }
        else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}
