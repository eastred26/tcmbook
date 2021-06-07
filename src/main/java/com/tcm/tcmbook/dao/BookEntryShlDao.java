package com.tcm.tcmbook.dao;

import com.tcm.tcmbook.pojo.BookEntryShl;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BookEntryShlDao {
    @Select("SELECT * FROM entry_shl WHERE id=${id}")
    BookEntryShl findById(Integer id);
    @Select("SELECT * FROM entry_shl")
    List<BookEntryShl> findAll();
    @Select("SELECT * FROM entry_shl WHERE titleId=${tid}")
    List<BookEntryShl> findByTid(Integer tid);
}
