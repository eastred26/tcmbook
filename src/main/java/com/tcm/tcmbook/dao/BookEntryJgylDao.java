package com.tcm.tcmbook.dao;

import com.tcm.tcmbook.pojo.BookEntryJgyl;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BookEntryJgylDao {
    @Select("SELECT * FROM entry_jgyl WHERE id=${id}")
    BookEntryJgyl findById(Integer id);
}
