package com.whut.bandou.dao;

import com.whut.bandou.bean.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable; 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserDao extends JpaRepository<User, Long> {
    User findByUsernameAndPassword(String username, String password);

    @Query("select u from User u where u.username like ?1")//?1是第一个参数的意思
    Page<User> findByQuery(String query, Pageable pageable);
}
