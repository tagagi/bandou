package com.whut.bandou.controller;

import com.whut.bandou.bean.User;
import com.whut.bandou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("")//从index.html跳转到login.html
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/home")//点击首页，跳回
    public String toHome(){
        return "home";
    }

    @RequestMapping("/toRegister")//跳转到register.html
    public String toRegister() {
        return "register";
    }

    @RequestMapping("/register")//用户注册按钮
    public String register(@RequestParam String username, @RequestParam String password,
                           HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/html;charset=utf-8");
        PrintWriter out = servletResponse.getWriter();

        if (!username.equals("") && username != null &&
                !password.equals("") && password != null) {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            User user1 = userService.saveUser(user);

            if (user1.getId() != null) {
                out.print("<script>alert('注册成功!');</script>");
                out.flush();
                return "/login";
            } else {
                out.print("<script>alert('数据库写入失败!');</script>");
                out.flush();
                return "/register";
            }
        }
        out.print("<script>alert('用户名和密码不能为空!');</script>");
        out.flush();
        return "/register";
    }

    @RequestMapping("/login")//用户登录按钮
    public String login(@RequestParam String username, @RequestParam String password,
                        HttpSession session, HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/html;charset=utf-8");
        PrintWriter out = servletResponse.getWriter();

        User user = userService.checkUsers(username, password);
        if (user != null) {
            user.setPassword(null);
            session.setAttribute("user",user);
            return "home";
        }else{
            out.print("<script>alert('用户验证错误!');</script>");
            out.flush();
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/";
    }


}
