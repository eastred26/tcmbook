package com.tcm.tcmbook.dao;

import com.tcm.tcmbook.pojo.Prescription;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface PrescriptionDao {
    @Select("SELECT * FROM tcm_prescription WHERE id=${id}")
    Prescription findById(Integer id);
    @Select("SELECT FangMing FROM tcm_prescription WHERE id=${id}")
    String findNameById(Integer id);
}
