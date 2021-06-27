package com.tcm.tcmbook.service;

import com.tcm.tcmbook.pojo.EntryFangyao;

import java.util.List;

public interface EntryFangyaoService {
    EntryFangyao findByEidPidS(Integer eid, Integer pid);
    EntryFangyao findByEidPidJ(Integer eid,Integer pid);
    List<String> findEidByPid(Integer pid);
    List<Integer> findPidByEid(String eid);
}
