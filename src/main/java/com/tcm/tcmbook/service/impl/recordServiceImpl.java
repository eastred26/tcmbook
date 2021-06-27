package com.tcm.tcmbook.service.impl;

import com.tcm.tcmbook.pojo.record;
import com.tcm.tcmbook.service.recordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class recordServiceImpl implements recordService {
    @Autowired
    private com.tcm.tcmbook.dao.recordDao recordDao;
    @Autowired
    private com.tcm.tcmbook.dao.questionDao questionDao;
    @Autowired
    private com.tcm.tcmbook.service.questionService questionService;
    @Override
    public List<record> getByUid(Integer uid){return recordDao.findByUid(uid);}
    @Override
    public List<record> getByUidEid(Integer uid, Integer eid){return recordDao.findByUidEid(uid,eid);}
    @Override
    public int getMaxId(){return recordDao.findMaxId();}
    @Override
    public void AddRecord(Integer uid,Integer eid,Integer pid,String qid,String ans,Integer isRight){
        Integer id=recordDao.findMaxId();
        if(id==null)id=1;
        else id=id+1;
        String rightans=questionService.findById(qid).getAnswer();
        String qtype=qid.charAt(1)+"";
        List<record> rs=recordDao.findByUidQid(uid,qid);
        recordDao.AddRecord(id,uid,eid,pid,qid,qtype,ans,rightans,isRight);
    }
    @Override
    public List<record> findAll(){
        return recordDao.findAll();
    }
    @Override
    public List<record> findByUidType(Integer uid, String type){
        if(type.equals("k"))return recordDao.findByUid(uid);
        return  recordDao.findByUidType(uid,type);
    }
    @Override
    public List<record> findFalse(){
        List<record> rs=recordDao.findFalse();
        return rs;
    }
    @Override
    public int findNumByUid(Integer uid){
        return recordDao.findNumByUid(uid);
    }
    @Override
    public int findNumFalseByUid(Integer uid){
        return  recordDao.findNumFalseByUid(uid);
    }
    @Override
    public int findNum(){
        return recordDao.findNum();
    }
}
