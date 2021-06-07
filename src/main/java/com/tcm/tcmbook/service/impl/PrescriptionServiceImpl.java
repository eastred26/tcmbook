package com.tcm.tcmbook.service.impl;

import com.tcm.tcmbook.dao.PrescriptionDao;
import com.tcm.tcmbook.pojo.Prescription;
import com.tcm.tcmbook.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {
    @Autowired
    private  PrescriptionDao prescriptionDao;

    @Override
    public Prescription getById(Integer id){
        return prescriptionDao.findById(id);
    }

    @Override
    public String getNameById(Integer id){
        return prescriptionDao.findNameById(id);
    }
    @Override
    public List<Prescription> findAll(){return prescriptionDao.findAll();}
}
