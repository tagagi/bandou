package com.whut.bandou.bean;

import javax.persistence.*; 
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "t_admin")
public class Admin {
    @Id     //主键标识
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //@NotBlank(message = "分类名称不能为空")
    private String adminname;

    private String password;

    public Admin() {
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", adminrname='" + adminname + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getAdminname() {
        return adminname;
    }

    public void setAdminname(String adminname) {
        this.adminname = adminname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
