package com.tcm.tcmbook.service;

import com.tcm.tcmbook.pojo.BookEntryJgyl;
import com.tcm.tcmbook.pojo.BookEntryShl;

import java.util.List;

public interface BookEntryJgylService {
    BookEntryJgyl getById(Integer id);
    List<BookEntryJgyl> findAll();
    List<BookEntryJgyl> findByTid(Integer tid);
}
