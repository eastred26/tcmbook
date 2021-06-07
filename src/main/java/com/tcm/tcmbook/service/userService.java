package com.tcm.tcmbook.service;

import com.tcm.tcmbook.pojo.user;

import java.util.List;

public interface userService {
    user getById(Integer id);
    String getPwdByAccount(String account);
    int getMaxId();
    user check(String account, String pwd);
    void AddUser(Integer id, String name, String account, String pwd);
    Integer findNum();
    List<user> findAll();
    void DeleteUser(Integer uid);
}
