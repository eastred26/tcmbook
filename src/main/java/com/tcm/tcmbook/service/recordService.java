package com.tcm.tcmbook.service;

import com.tcm.tcmbook.pojo.record;

import java.util.List;

public interface recordService {
    List<record> getByUid(Integer uid);
    int getMaxId();
    void AddRecord(Integer uid, Integer eid, Integer pid, String qid, String ans, Integer isRight);
    List<record> findAll();
    List<record> findByUidType(Integer uid, String type);
    List<record> findFalse();
    List<record> getByUidEid(Integer uid, Integer eid);
}
