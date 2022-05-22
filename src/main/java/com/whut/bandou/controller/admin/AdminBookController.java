package com.whut.bandou.controller.admin;

import com.whut.bandou.bean.Book;
import com.whut.bandou.bean.Score;
import com.whut.bandou.bean.User;
import com.whut.bandou.service.BookService;
import com.whut.bandou.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort; 
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/admin")
public class AdminBookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private ScoreService scoreService;

//    @RequestMapping("/toBookManage")
//    public String toBookManger(@PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable, Model model){
//        model.addAttribute("page", bookService.listBook(pageable));
//        return "admin/bookManage";
//    }
    @RequestMapping("/toBookManage")
    public String toBookManger(@PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable, Model model,
                               @RequestParam(defaultValue = "0") int type, HttpSession session, String queryOption, String queryInfo) {
        if (type == 1) {
            session.setAttribute("queryOption", queryOption);
            session.setAttribute("queryInfo", queryInfo);

        } else {
            queryOption = (String) session.getAttribute("queryOption");
            queryInfo = (String) session.getAttribute("queryInfo");

        }
        if (!"".equals(queryInfo) && queryInfo != null) {
            model.addAttribute("queryOption", queryOption);
            model.addAttribute("queryInfo", queryInfo);
            model.addAttribute("page", bookService.listBook(queryOption  ,"%" + queryInfo + "%", pageable));
        } else{
            model.addAttribute("page", bookService.listBook(pageable));
        }



        System.out.println(queryInfo);
        System.out.println(queryOption);


        return "admin/bookManage";
    }


    @RequestMapping("/bookAdd")
    public String add(Book book, RedirectAttributes attributes){

        Book book1 = bookService.findByISBN(book.getISBN());
        if(book1!=null){
            attributes.addFlashAttribute("message", "本书已存在，添加失败!");
        }else{
            if(book.getISBN()==null){
                attributes.addFlashAttribute("message", "唯一标识ISBN不存在，添加失败!");
            }else{
                Book book2 =bookService.saveBook(book);
                if(book2 != null){
                    attributes.addFlashAttribute("message", "添加成功");
                }else{
                    attributes.addFlashAttribute("message", "添加失败");
                }
            }
        }
        return "redirect:/admin/toBookManage";
    }

    @RequestMapping("/bookUpdate/{id}")
    public String update(@PathVariable Long id, Book book, RedirectAttributes attributes){
        System.out.println(id);
        Book book1 = bookService.findByISBN(book.getISBN());
        if(book1==null){
            System.out.println("该书不存在！");
        }else {
                Book book2 = bookService.updateBook(id, book);
                if (book2 != null) {
                    attributes.addFlashAttribute("message", "修改成功");
                } else {
                    attributes.addFlashAttribute("message", "修改失败");

                }
        }
        return "redirect:/admin/toBookManage";
    }

    @RequestMapping("/bookDelete/{id}")
    public String delete(@PathVariable Long id){
        bookService.deleteBook(id);
        return "redirect:/admin/toBookManage";
    }

    @RequestMapping("/detail/{id}")
    public String detail(@PathVariable Long id,
                         Model model,
                         HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        Book book = bookService.findById(id);
        model.addAttribute("book", book);
        //获取该book的所有评论
        model.addAttribute("comments",book.getComments());

        return "admin/detail";
    }

}
