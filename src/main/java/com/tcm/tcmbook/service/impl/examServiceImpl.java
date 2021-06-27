package com.tcm.tcmbook.service.impl;

import com.tcm.tcmbook.pojo.exam;
import com.tcm.tcmbook.service.examService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class examServiceImpl implements examService {
    @Autowired
    private com.tcm.tcmbook.dao.examDao examDao;
    @Autowired
    private com.tcm.tcmbook.dao.userDao userDao;
    @Override
    public exam findById(Integer id){return examDao.findById(id);}
    @Override
    public int findMaxId(){
        Integer num=examDao.findMaxId();
        if(num==null)return 1;
        return examDao.findMaxId();
    }
    @Override
    public List<exam> findByUid(Integer uid){return examDao.findByUid(uid);}
    @Override
    public void AddExam(Integer eid,Integer uid,Integer pid,String pname,Integer score_all,Integer score_get){
        String uName=userDao.findById(uid).getName();
        examDao.AddExam(eid,uid,uName,pid,pname,score_all,score_get,1.0*score_get/score_all,new Date());
    }
    @Override
    public List<exam>  findAll(){
        return examDao.findAll();
    }

    @Override
    public List<exam> findAllOrderByRateDesc(){
        List<exam> r=examDao.findAllOrderByRateDesc();
        int min=Math.min(r.size(),5);
        List<exam> res=r.subList(0,min);
        return res;
    }
    @Override
    public List<Integer> findScoreByPid(Integer pid){
        return examDao.findScoreByPid(pid);
    }
}
