package com.tcm.tcmbook.service;

import com.tcm.tcmbook.pojo.paper_question;

import java.util.List;


public interface paper_questionService {
    paper_question findById(Integer id);
    int findMaxId();
    List<String> findByPid(Integer pid);
    void AddNew(Integer pid, String qid);
    void DeletePq(Integer pid);
    void DeletePqOne(Integer pid, String qid);
}
