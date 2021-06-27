package com.tcm.tcmbook.service;

import com.tcm.tcmbook.pojo.Herb;
import com.tcm.tcmbook.pojo.Prescription;

import java.util.List;

public interface HerbService {
    String getNameById(Integer id);
    List<Herb> findAll();
    void EditH(Integer id,Integer tid);
    List<Herb> findByTid(Integer tid);
    Herb findById(Integer id);
}
