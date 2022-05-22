package com.whut.bandou.dao;

import com.whut.bandou.bean.Book; 
import com.whut.bandou.bean.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ScoreDao  extends JpaRepository<Score, Long>, JpaSpecificationExecutor<Score> {
    Score findByUserIdAndBookId(Long userId, Long bookId);
}
