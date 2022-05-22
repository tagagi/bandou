package com.whut.bandou.service.Impl;

import com.whut.bandou.bean.Score;
import com.whut.bandou.dao.ScoreDao; 
import com.whut.bandou.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreDao scoreDao;

    @Override
    public Score getScore(Long userId, Long bookId) {
        return scoreDao.findByUserIdAndBookId(userId, bookId);
    }

    @Override
    public void save(Score score) {
        scoreDao.save(score);
    }
}
