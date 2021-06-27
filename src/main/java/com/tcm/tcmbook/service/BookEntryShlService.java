package com.tcm.tcmbook.service;

import com.tcm.tcmbook.pojo.BookEntryShl;
import com.tcm.tcmbook.pojo.EntrySLabel;

import java.util.List;

public interface BookEntryShlService {
    BookEntryShl getById(Integer id);
    List<BookEntryShl> findAll();
    List<BookEntryShl> findByTid(Integer tid);
    EntrySLabel findLabelByid(Integer id);
}
