package com.tcm.tcmbook.service;

import com.tcm.tcmbook.pojo.exam;

import java.util.List;


public interface examService {
    exam findById(Integer id);
    int findMaxId();
    List<exam> findByUid(Integer uid);
    List<exam>  findAll();
    void AddExam(Integer eid, Integer uid, Integer pid, String pname, Integer score_all, Integer score_get);
    List<exam> findAllOrderByRateDesc();
    List<Integer> findScoreByPid(Integer pid);
}
