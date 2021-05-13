package com.tcm.tcmbook.service.impl;

import com.tcm.tcmbook.dao.BookEntryShlDao;
import com.tcm.tcmbook.dao.BookEntryShlDao;
import com.tcm.tcmbook.pojo.BookEntryShl;
import com.tcm.tcmbook.service.BookEntryShlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookEntryShlServiceImpl implements BookEntryShlService {
    @Autowired
    private BookEntryShlDao bookEntryDao;

    @Override
    public BookEntryShl getById(Integer id){
        return bookEntryDao.findById(id);
    }

}
