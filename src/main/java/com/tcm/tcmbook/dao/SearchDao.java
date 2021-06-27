package com.tcm.tcmbook.dao;

import com.tcm.tcmbook.pojo.user;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SearchDao {
    @Select("SELECT * FROM entry_shl WHERE yuanwen LIKE #{kw}")
    List<Integer> findEIDByS(String kw);
    @Select("SELECT * FROM entry_jgyl WHERE yuanwen LIKE #{kw}")
    List<Integer> findJIDByS(String kw);
    @Select("SELECT * FROM tcm_prescription WHERE FangMing LIKE #{kw}")
    List<Integer> findPIDByS(String kw);
    @Select("SELECT * FROM tcm_herb WHERE name LIKE #{kw}")
    List<Integer> findHIDByS(String kw);
}
