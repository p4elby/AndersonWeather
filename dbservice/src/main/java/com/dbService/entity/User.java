package com.dbService.entity;

import javax.persistence.*;

@Entity

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_user")
    private int id_user;
    private String user_name,user_pass;

    public User(String user_name, String user_pass) {
        this.user_name = user_name;
        this.user_pass = user_pass;
    }
    public User(){

    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_pass() {
        return user_pass;
    }

    public void setUser_pass(String user_pass) {
        this.user_pass = user_pass;
    }

    @Override
    public String toString() {
        return "user_name=" + user_name + '\'' +
                ", user_pass=" + user_pass + '\'';
    }
}
