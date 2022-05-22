package com.whut.bandou.controller;

import com.whut.bandou.bean.Book;
import com.whut.bandou.bean.Comment;
import com.whut.bandou.bean.Score;
import com.whut.bandou.bean.User;
import com.whut.bandou.service.BookService;
import com.whut.bandou.service.CommentService;
import com.whut.bandou.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/")
public class CommentController {
    @Autowired
    private BookService bookService;

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private CommentService commentService;


    @PostMapping("/score/{id}")
    public String score(@PathVariable Long id,
                        Float score,
                        HttpSession session) {
        System.out.println(score);
        //先存score
        User user = (User) session.getAttribute("user");
        Book book = bookService.findById(id);
        scoreService.save(new Score(score, user, book));
        //再更新book的总评分
        book.setScore((book.getCount() * book.getScore() + score) / (book.getCount() + 1.0));
        book.setCount(book.getCount() + 1);
        bookService.saveBook(book);


        return "redirect:/detail/" + id;
    }

    @PostMapping("/comment/{id}")
    public String comment(@PathVariable Long id, String content, HttpSession session) {
        System.out.println(id);
        System.out.println(content);

        //先存储
        User user = (User) session.getAttribute("user");
        Book book = bookService.findById(id);
        Date createTime = new Date();
        //跟据bookId查找评论数量以获得楼层数
        Integer floor=1;
        List<Comment> comments=book.getComments();
        if(!(comments==null||comments.size()==0))
        {
            floor=comments.get(comments.size()-1).getFloor()+1;
        }
        Comment comment = new Comment(content,createTime,floor,user,book);
        commentService.save(comment);
        //重定向
        return "redirect:/detail/" + id;
    }

    @PostMapping("/reply/{bookId}/{commentId}")
    public String reply(@PathVariable Long bookId,
                        @PathVariable Long commentId,
                        HttpSession session,
                        String content) {
        //找到user与book
        User user = (User) session.getAttribute("user");
        Book book = bookService.findById(bookId);
        //找到父级评论
        Comment parent = commentService.findById(commentId);
        Date createTime = new Date();
        //跟据bookId查找评论数量以获得楼层数
        Integer floor=1;
        List<Comment> comments=book.getComments();
        if(!(comments==null||comments.size()==0))
        {
            floor=comments.get(comments.size()-1).getFloor()+1;
        }
        Comment reply = new Comment(content,createTime,floor,user,book);
        reply.setParentComment(parent);
        commentService.save(reply);
        return "redirect:/detail/" + bookId;
    }


}
