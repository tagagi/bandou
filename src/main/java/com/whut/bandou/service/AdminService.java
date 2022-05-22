package com.whut.bandou.service;

import com.whut.bandou.bean.Admin; 
import com.whut.bandou.bean.User;

public interface AdminService {
    Admin checkAdmin(String adminname, String password);
}
