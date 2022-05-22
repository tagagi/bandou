package com.whut.bandou.controller;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private ScoreService scoreService;

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
            model.addAttribute("page", bookService.listBook(queryOption, "%" + queryInfo + "%", pageable));
        } else {
            model.addAttribute("page", bookService.listBook(pageable));
        }
        return "/bookManage";
    }

    @RequestMapping("/detail/{id}")
    public String detail(@PathVariable Long id,
                         Model model,
                         HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        Book book = bookService.findById(id);
        model.addAttribute("book", book);
        //判断是否评分
        Score score = scoreService.getScore(user.getId(), id);
        if (score != null){
            model.addAttribute("displayScore", false);
            model.addAttribute("myScore",score.getBookScore());
        }else{
            model.addAttribute("displayScore", true);
            model.addAttribute("myScore",-1);
        }
        //获取该book的所有评论
        model.addAttribute("comments",book.getComments());

        return "detail";
    }
}
