package com.whut.bandou.service;
 
import com.whut.bandou.bean.Score;

public interface ScoreService {
    Score getScore(Long userId, Long bookId);

    void save(Score score);
}
