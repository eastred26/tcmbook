package com.tcm.tcmbook.service.impl;

import com.tcm.tcmbook.dao.HerbDao;
import com.tcm.tcmbook.pojo.Herb;
import com.tcm.tcmbook.service.HerbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HerbServiceImpl implements HerbService {
    @Autowired
    private HerbDao herbDao;
    @Override
    public String getNameById(Integer id){
        return herbDao.findNameById(id);
    }
    @Override
    public List<Herb> findAll(){
        return herbDao.findAll();
    }
    @Override
    public void EditH(Integer id,Integer tid){
        herbDao.EditH(id,tid);
    }
    @Override
    public List<Herb> findByTid(Integer tid){
        return herbDao.findByTid(tid);
    }
    @Override
    public Herb findById(Integer id){
        return herbDao.findById(id);
    }
}
