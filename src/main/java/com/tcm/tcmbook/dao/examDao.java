package com.tcm.tcmbook.dao;

import com.tcm.tcmbook.pojo.exam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public interface examDao {
    @Select("SELECT * FROM exam WHERE id=${id}")
    exam findById(Integer id);
    @Select("SELECT max(id) FROM exam")
    Integer findMaxId();
    @Select("SELECT * FROM exam WHERE uid=${uid}")
    List<exam> findByUid(Integer uid);
    @Select("SELECT score_get FROM exam WHERE pid=${pid}")
    List<Integer> findScoreByPid(Integer pid);
    @Select("SELECT * FROM exam")
    List<exam>  findAll();

    @Select("SELECT * FROM exam ORDER BY rate DESC")
    List<exam> findAllOrderByRateDesc();

    @Insert("INSERT INTO exam VALUES(${id},${uid},#{uName},${pid},#{pname},${score_all},${score_get},${rate},#{submitTime})")
    void AddExam(Integer id, Integer uid, String uName, Integer pid, String pname, Integer score_all, Integer score_get, Double rate, Date submitTime);
}