package com.tcm.tcmbook.service.impl;

import com.tcm.tcmbook.dao.BookEntryShlDao;
import com.tcm.tcmbook.dao.BookEntryShlDao;
import com.tcm.tcmbook.pojo.BookEntryJgyl;
import com.tcm.tcmbook.pojo.BookEntryShl;
import com.tcm.tcmbook.pojo.EntrySLabel;
import com.tcm.tcmbook.service.BookEntryShlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookEntryShlServiceImpl implements BookEntryShlService {
    @Autowired
    private BookEntryShlDao bookEntryDao;

    @Override
    public BookEntryShl getById(Integer id){
        return bookEntryDao.findById(id);
    }

    @Override
    public List<BookEntryShl> findAll(){
        return bookEntryDao.findAll();
    }
    @Override
    public List<BookEntryShl> findByTid(Integer tid){return bookEntryDao.findByTid(tid);}
    @Override
    public EntrySLabel findLabelByid(Integer id){
        return bookEntryDao.findLabelByid(id);
    }
}
