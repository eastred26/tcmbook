package com.tcm.tcmbook.service;

import com.tcm.tcmbook.pojo.question;

import java.util.List;


public interface questionService {
    question findById(String qid);
    void AddRight(String qid);
    void AddFalse(String qid);
    List<question> findByBookType(String type);
    List<question> findAllOrderByFalseDesc(String type);
    void AddUserQuestion(Integer uid,Integer qid);
    List<Integer> FindUserQuestion(Integer uid);
}
