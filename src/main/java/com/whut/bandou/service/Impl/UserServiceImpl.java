package com.whut.bandou.service.Impl;

import com.whut.bandou.bean.User;
import com.whut.bandou.dao.UserDao; 
import com.whut.bandou.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;


    @Override
    public User saveUser(User user) {
        return userDao.save(user);
    }

    @Override
    public User checkUsers(String username, String password) {
        return userDao.findByUsernameAndPassword(username,password);
    }

    @Override
    public Page<User> listUser(Pageable pageable) {
        return userDao.findAll(pageable);
    }

    @Override
    public Page<User> listUser(String query, Pageable pageable) {
        return userDao.findByQuery(query,pageable);
    }

    @Override
    public User updateUser(Long id,User user) {
        User user1=userDao.findById(id).orElse(null);
        if(user1==null){
            System.out.println("未获得更新对象");
        }
        BeanUtils.copyProperties(user,user1);
        return userDao.save(user1);
    }

    @Override
    public void deleteUser(Long id) {
        userDao.deleteById(id);
    }

}
