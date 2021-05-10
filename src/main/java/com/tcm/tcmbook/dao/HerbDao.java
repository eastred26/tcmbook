package com.tcm.tcmbook.dao;

import com.tcm.tcmbook.pojo.Herb;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface HerbDao {
    @Select("SELECT name FROM tcm_herb WHERE id=${id}")
    String findNameById(Integer id);
}
