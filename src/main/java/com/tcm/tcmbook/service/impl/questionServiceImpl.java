package com.tcm.tcmbook.service.impl;

import com.tcm.tcmbook.pojo.question;
import com.tcm.tcmbook.service.questionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class questionServiceImpl implements questionService {
    @Autowired
    private com.tcm.tcmbook.dao.questionDao questionDao;
    @Override
    public question findById(String qid){
        String type=qid.substring(0,2);
        Integer id=Integer.parseInt(qid.substring(2));
        if(type.equals("sa"))return questionDao.findByIdsa(id);
        else if(type.equals("sb"))return questionDao.findByIdsb(id);
        else if(type.equals("sc"))return questionDao.findByIdsc(id);
        else if(type.equals("sd"))return questionDao.findByIdsd(id);
        else if(type.equals("ja"))return questionDao.findByIdja(id);
        else if(type.equals("jb"))return questionDao.findByIdjb(id);
        else if(type.equals("jc"))return questionDao.findByIdjc(id);
        else return questionDao.findByIdjd(id);
    }
    @Override
    public void AddRight(String qid){
        String type=qid.substring(0,2);
        Integer id=Integer.parseInt(qid.substring(2));
        if(type.equals("sa"))questionDao.AddRightsa(id);
        else if(type.equals("sb"))questionDao.AddRightsb(id);
        else if(type.equals("sc"))questionDao.AddRightsc(id);
        else if(type.equals("sd"))questionDao.AddRightsd(id);
        else if(type.equals("ja"))questionDao.AddRightja(id);
        else if(type.equals("jb"))questionDao.AddRightjb(id);
        else if(type.equals("jc"))questionDao.AddRightjc(id);
        else questionDao.AddRightjd(id);
    }
    @Override
    public void AddFalse(String qid){
        String type=qid.substring(0,2);
        Integer id=Integer.parseInt(qid.substring(2));
        if(type.equals("sa"))questionDao.AddFalsesa(id);
        else if(type.equals("sb"))questionDao.AddFalsesb(id);
        else if(type.equals("sc"))questionDao.AddFalsesc(id);
        else if(type.equals("sd"))questionDao.AddFalsesd(id);
        else if(type.equals("ja"))questionDao.AddFalseja(id);
        else if(type.equals("jb"))questionDao.AddFalsejb(id);
        else if(type.equals("jc"))questionDao.AddFalsejc(id);
        else questionDao.AddFalsejd(id);
    }
    @Override
    public List<question> findByBookType(String type){
        if(type.equals("sa"))return questionDao.findAllsa();
        else if(type.equals("sb"))return questionDao.findAllsb();
        else if(type.equals("sc"))return questionDao.findAllsc();
        else if(type.equals("sd"))return questionDao.findAllsd();
        else if(type.equals("ja"))return questionDao.findAllja();
        else if(type.equals("jb"))return questionDao.findAlljb();
        else if(type.equals("jc"))return questionDao.findAlljc();
        else return questionDao.findAlljd();
    }
    @Override
    public List<question> findAllOrderByFalseDesc(String type){
        if(type.equals("sa"))return questionDao.findAllOrderByFalseDescsa();
        else if(type.equals("sb"))return questionDao.findAllOrderByFalseDescsb();
        else if(type.equals("sc"))return questionDao.findAllOrderByFalseDescsc();
        else if(type.equals("sd"))return questionDao.findAllOrderByFalseDescsd();
        else if(type.equals("ja"))return questionDao.findAllOrderByFalseDescja();
        else if(type.equals("jb"))return questionDao.findAllOrderByFalseDescjb();
        else if(type.equals("jc"))return questionDao.findAllOrderByFalseDescjc();
        else return questionDao.findAllOrderByFalseDescjd();
    }
}
