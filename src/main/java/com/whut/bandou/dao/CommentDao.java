package com.whut.bandou.dao;
 
import com.whut.bandou.bean.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CommentDao  extends JpaRepository<Comment, Long>, JpaSpecificationExecutor<Comment> {


}
