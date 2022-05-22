package com.whut.bandou.service;
 
import com.whut.bandou.bean.Comment;

public interface CommentService {
    Comment save(Comment comment);

    Comment findById(Long id);

    void deleteById(Long id);
}
