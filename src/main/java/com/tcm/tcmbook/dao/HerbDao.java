package com.tcm.tcmbook.dao;

import com.tcm.tcmbook.pojo.Herb;
import com.tcm.tcmbook.pojo.Prescription;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface HerbDao {
    @Select("SELECT name FROM tcm_herb WHERE id=${id}")
    String findNameById(Integer id);
    @Select("SELECT * FROM tcm_herb")
    List<Herb> findAll();
    @Update("UPDATE tcm_herb SET titleId=${tid} WHERE id=${id}")
    void EditH(Integer id,Integer tid);
    @Select("SELECT * FROM tcm_herb WHERE titleId=${tid}")
    List<Herb> findByTid(Integer tid);
    @Select("SELECT * FROM tcm_herb WHERE id=${id}")
    Herb findById(Integer id);
}
