package com.tcm.tcmbook.service;

import com.tcm.tcmbook.pojo.BookEntryShl;

import java.util.List;

public interface BookEntryShlService {
    BookEntryShl getById(Integer id);
    List<BookEntryShl> findAll();
    List<BookEntryShl> findByTid(Integer tid);
}
