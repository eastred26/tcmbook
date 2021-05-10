package com.tcm.tcmbook.service.impl;

import com.tcm.tcmbook.dao.HerbDao;
import com.tcm.tcmbook.service.HerbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HerbServiceImpl implements HerbService {
    @Autowired
    private HerbDao herbDao;
    @Override
    public String getNameById(Integer id){
        return herbDao.findNameById(id);
    }
}
