package com.whut.bandou.dao;

import com.whut.bandou.bean.Admin;
import com.whut.bandou.bean.User; 
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminDao extends JpaRepository<Admin, Long> {
    Admin findByAdminnameAndPassword(String adminname, String password);
}
