package com.tcm.tcmbook.service.impl;

import com.tcm.tcmbook.dao.PrescriptionDao;
import com.tcm.tcmbook.dao.SearchDao;
import com.tcm.tcmbook.pojo.Prescription;
import com.tcm.tcmbook.service.PrescriptionService;
import com.tcm.tcmbook.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private SearchDao searchDao;

    @Override
    public List<Integer> findEIDByS(String kw){
        return searchDao.findEIDByS(kw);
    }
    @Override
    public List<Integer> findJIDByS(String kw){
        return searchDao.findJIDByS(kw);
    }
    @Override
    public List<Integer> findPIDByS(String kw){
        return searchDao.findPIDByS(kw);
    }
    @Override
    public List<Integer> findHIDByS(String kw){
        return searchDao.findHIDByS(kw);
    }
}
