package com.whut.bandou.service;
 
import com.whut.bandou.bean.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    User saveUser(User user);
    User checkUsers(String username, String password);
    Page<User> listUser(Pageable pageable);
    Page<User> listUser(String query, Pageable pageable);
    User updateUser(Long id, User user);
    void deleteUser(Long id);
}
