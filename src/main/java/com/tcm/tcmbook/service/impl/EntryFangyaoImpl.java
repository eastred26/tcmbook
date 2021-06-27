package com.tcm.tcmbook.service.impl;

import com.tcm.tcmbook.dao.EntryFangyaoDao;
import com.tcm.tcmbook.dao.HerbDao;
import com.tcm.tcmbook.pojo.EntryFangyao;
import com.tcm.tcmbook.pojo.Herb;
import com.tcm.tcmbook.service.EntryFangyaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntryFangyaoImpl implements EntryFangyaoService {
    @Autowired
    private EntryFangyaoDao entryFangyaoDao;
    @Override
    public EntryFangyao findByEidPidS(Integer eid, Integer pid){
        return entryFangyaoDao.findByEidPidS(eid,pid);
    }
    @Override
    public EntryFangyao findByEidPidJ(Integer eid,Integer pid){
        return entryFangyaoDao.findByEidPidJ(eid,pid);
    }
    @Override
    public List<Integer> findPidByEid(String eid){
        return entryFangyaoDao.findPidByEid(eid);
    }
    @Override
    public List<String> findEidByPid(Integer pid){
        return entryFangyaoDao.findEidByPid(pid);
    }
}
