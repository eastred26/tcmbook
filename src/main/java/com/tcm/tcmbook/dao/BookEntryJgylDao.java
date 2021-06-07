package com.tcm.tcmbook.dao;

import com.tcm.tcmbook.pojo.BookEntryJgyl;
import com.tcm.tcmbook.pojo.BookEntryShl;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BookEntryJgylDao {
    @Select("SELECT * FROM entry_jgyl WHERE id=${id}")
    BookEntryJgyl findById(Integer id);
    @Select("SELECT * FROM entry_jgyl")
    List<BookEntryJgyl> findAll();
    @Select("SELECT * FROM entry_jgyl WHERE titleId=${tid}")
    List<BookEntryJgyl> findByTid(Integer tid);
}
