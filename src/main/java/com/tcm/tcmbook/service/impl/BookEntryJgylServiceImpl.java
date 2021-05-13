package com.tcm.tcmbook.service.impl;

import com.tcm.tcmbook.dao.BookEntryJgylDao;
import com.tcm.tcmbook.pojo.BookEntryJgyl;
import com.tcm.tcmbook.service.BookEntryJgylService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookEntryJgylServiceImpl implements BookEntryJgylService {
    @Autowired
    private BookEntryJgylDao bookEntryDao;

    @Override
    public BookEntryJgyl getById(Integer id){
        return bookEntryDao.findById(id);
    }

}