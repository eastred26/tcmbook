package com.tcm.tcmbook.dao;

import com.tcm.tcmbook.pojo.BookEntry;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BookEntryDao {
    @Select("SELECT * FROM regrex_shl WHERE id=${id}")
    BookEntry findById(Integer id);
}
