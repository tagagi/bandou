package com.whut.bandou.controller.admin;

import com.whut.bandou.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam; 

@Controller
@RequestMapping("/admin")
public class AdminCommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping("/delete/")
    public String delete(@RequestParam Long id,
                         @RequestParam Long bookId){
        commentService.deleteById(id);
        return "redirect:/admin/detail/"+bookId;
    }
}
