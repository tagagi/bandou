package com.whut.bandou.service.Impl;
 
import com.whut.bandou.bean.Comment;
import com.whut.bandou.dao.CommentDao;
import com.whut.bandou.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Override
    public Comment save(Comment comment) {
        return commentDao.save(comment);
    }

    @Override
    public Comment findById(Long id) {
        return commentDao.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        Comment comment = commentDao.findById(id).orElse(null);
        if (comment != null) {
            List<Comment> replies = comment.getReplyComments();
            for (Comment reply : replies) {
                deleteById(reply.getId());
            }
        }
        commentDao.deleteById(id);
    }
}
