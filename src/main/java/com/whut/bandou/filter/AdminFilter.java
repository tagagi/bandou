package com.whut.bandou.filter;

import com.whut.bandou.bean.Admin;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
@WebFilter(urlPatterns = "/admin/*", filterName = "adminFilter", initParams = {
        @WebInitParam(name = "URL", value = "http://localhost:8080/admin")})
public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession httpSession = request.getSession();
        Admin admin = (Admin) httpSession.getAttribute("admin");
        String uri = request.getRequestURI();

        if (!uri.contains("admin")) {
            filterChain.doFilter(request, response);
            return;
        }

        if (!uri.contains("toLogin") && !uri.contains("login") && admin == null) {
            System.out.println("admin-filter---uri:   " + uri);
            response.sendRedirect("/admin/toLogin");
        } else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
