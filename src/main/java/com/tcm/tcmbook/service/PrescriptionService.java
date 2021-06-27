package com.tcm.tcmbook.service;

import com.tcm.tcmbook.pojo.Prescription;

import java.util.List;

public interface PrescriptionService {
    Prescription getById(Integer id);
    String getNameById(Integer id);
    List<Prescription> findAll();
    void EditP(Integer id,Integer tid);
    List<Prescription> findByTid(Integer tid);
}
