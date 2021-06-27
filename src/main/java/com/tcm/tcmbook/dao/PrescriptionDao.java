package com.tcm.tcmbook.dao;

import com.tcm.tcmbook.pojo.Prescription;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PrescriptionDao {
    @Select("SELECT * FROM tcm_prescription WHERE id=${id}")
    Prescription findById(Integer id);
    @Select("SELECT FangMing FROM tcm_prescription WHERE id=${id}")
    String findNameById(Integer id);
    @Select("SELECT * FROM tcm_prescription")
    List<Prescription> findAll();
    @Update("UPDATE tcm_prescription SET titleId=${tid} WHERE id=${id}")
    void EditP(Integer id,Integer tid);
    @Select("SELECT * FROM tcm_prescription WHERE titleId=${tid}")
    List<Prescription> findByTid(Integer tid);
}
