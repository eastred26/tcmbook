package com.tcm.tcmbook.dao;

import com.tcm.tcmbook.pojo.Prescription;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TitleDao {
    @Select("SELECT titleName FROM titles WHERE id=${id}")
    String findNameByIds(Integer id);
    @Select("SELECT titleName FROM titlej WHERE id=${id}")
    String findNameByIdj(Integer id);
}
