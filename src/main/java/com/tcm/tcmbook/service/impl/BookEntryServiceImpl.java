package com.tcm.tcmbook.service.impl;

import com.tcm.tcmbook.dao.BookEntryDao;
import com.tcm.tcmbook.pojo.BookEntry;
import com.tcm.tcmbook.service.BookEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookEntryServiceImpl implements BookEntryService {
    @Autowired
    private  BookEntryDao bookEntryDao;

    @Override
    public BookEntry getById(Integer id){
        return bookEntryDao.findById(id);
    }

}
