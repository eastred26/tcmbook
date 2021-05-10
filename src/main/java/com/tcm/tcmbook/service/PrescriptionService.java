package com.tcm.tcmbook.service;

import com.tcm.tcmbook.pojo.Prescription;

public interface PrescriptionService {
    Prescription getById(Integer id);
    String getNameById(Integer id);
}
