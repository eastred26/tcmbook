package com.tcm.tcmbook.dao;

import com.tcm.tcmbook.pojo.user;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface userDao {
    @Select("SELECT * FROM user WHERE id=${id}")
    user findById(Integer id);
    @Select("SELECT * FROM user")
    List<user> findAll();
    @Select("SELECT max(id) FROM user")
    Integer findMaxId();
    @Select("SELECT pwd FROM user WHERE account=#{account}")
    String findPwdByAccount(String account);
    @Select("SELECT * FROM user WHERE account=#{account} and pwd=#{pwd}")
    user check(String account, String pwd);
    @Select("INSERT INTO user VALUES(${id},#{name},#{account},#{pwd},0)")
    void AddUser(Integer id, String name, String account, String pwd);
    @Select("SELECT count(*) FROM user")
    Integer findNum();
    @Delete("DELETE FROM user WHERE id=${uid}")
    void DeleteUser(Integer uid);
}