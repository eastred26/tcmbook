package com.tcm.tcmbook.dao;

import com.tcm.tcmbook.pojo.BookEntryShl;
import com.tcm.tcmbook.pojo.EntrySLabel;
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
    @Select("SELECT * FROM shanghanlun_label WHERE recordId=${id}")
    EntrySLabel findLabelByid(Integer id);

}
