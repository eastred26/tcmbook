package com.tcm.tcmbook.dao;

import com.tcm.tcmbook.pojo.paper_question;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface paper_questionDao {
    @Select("SELECT * FROM paper_question WHERE id=${id}")
    paper_question findById(Integer id);
    @Select("SELECT max(id) FROM paper_question")
    Integer findMaxId();
    @Select("SELECT qid FROM paper_question WHERE pid=${pid}")
    List<String> findByPid(Integer pid);
    @Insert("INSERT INTO paper_question VALUES (${id},${pid},#{qid})")
    void AddPq(Integer id, Integer pid, String qid);
    @Delete("DELETE FROM paper_question WHERE pid=${pid}")
    void DeletePq(Integer pid);
    @Delete("DELETE FROM paper_question WHERE pid=${pid} and qid=#{qid}")
    void DeletePqOne(Integer pid, String qid);
}