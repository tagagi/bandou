package com.whut.bandou.bean;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity 
@Table(name = "t_user")
public class User {
    @Id     //主键标识
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //@NotBlank(message = "分类名称不能为空")
    private String username;

    private String password;

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
