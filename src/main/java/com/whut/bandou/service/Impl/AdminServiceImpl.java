package com.whut.bandou.service.Impl;

import com.whut.bandou.bean.Admin;
import com.whut.bandou.dao.AdminDao; 
import com.whut.bandou.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    @Override
    public Admin checkAdmin(String adminname, String password) {
        return adminDao.findByAdminnameAndPassword(adminname,password);
    }
}
