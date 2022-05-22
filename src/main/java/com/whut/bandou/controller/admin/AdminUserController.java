package com.whut.bandou.controller.admin;

import com.whut.bandou.bean.Admin; 
import com.whut.bandou.bean.User;
import com.whut.bandou.service.AdminService;
import com.whut.bandou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault; 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/admin")
public class AdminUserController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    @RequestMapping("/toLogin")//跳转到admin/login.html
    public String toLogin() {
        return "admin/login";
    }

    @RequestMapping("/home")//点击首页，跳回
    public String toAdminHome() {
        return "admin/home";
    }

    @RequestMapping("/login")//管理员登录按钮
    public String login(@RequestParam String adminname, @RequestParam String password,
                        HttpSession session, HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/html;charset=utf-8");
        PrintWriter out = servletResponse.getWriter();

        Admin admin = adminService.checkAdmin(adminname, password);
        if (admin != null) {
            admin.setPassword(null);
            session.setAttribute("admin", admin);
            return "admin/home";
        } else {
            out.print("<script>alert('管理员验证错误!');</script>");
            out.flush();
            return "admin/login";
        }
    }

    @RequestMapping("/toUserManage")//用户管理按钮,同时也是搜索的请求路径
    public String toUserManage(@PageableDefault(size = 3, sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable,
                               Model model,@RequestParam(defaultValue = "") String query) {
        model.addAttribute("page", userService.listUser("%"+query+"%",pageable));
        model.addAttribute("query",query);
        return "admin/userManage";
    }

    @RequestMapping("/userUpdate")//用户更新按钮
    public String userUpdate(@RequestParam String id, @RequestParam String username,
                             @RequestParam String password, RedirectAttributes attributes) {

        if (username.equals("") || username == null
                || password.equals("") || password == null) {
            //TODO
            attributes.addFlashAttribute("message", "用户名和密码均不能为空!");
            return "redirect:/admin/toUserManage";
        }
        Long userId = new Long(Long.parseLong(id));
        User user = new User();
        user.setId(userId);
        user.setUsername(username);
        user.setPassword(password);

        User user1 = userService.updateUser(userId, user);
        if (user1 == null) {
            //TODO
            attributes.addFlashAttribute("message", "更新失败");
        } else {
            //TODO
            attributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/toUserManage";
    }

    @RequestMapping("/userDelete")//删除用户按钮
    public String delete(@RequestParam Long id, RedirectAttributes attributes) {
        userService.deleteUser(id);
        //TODO 显示信息
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/toUserManage";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("admin");
        return "redirect:/admin";
    }

}
