package com.tcm.tcmbook.service.impl;

import com.tcm.tcmbook.pojo.paper;
import com.tcm.tcmbook.pojo.paper_question;
import com.tcm.tcmbook.service.paper_questionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class paper_questionServiceImpl implements paper_questionService {
    @Autowired
    private com.tcm.tcmbook.dao.paper_questionDao paper_questionDao;
    @Autowired
    private com.tcm.tcmbook.dao.questionDao questionDao;
    @Autowired
    private com.tcm.tcmbook.dao.paperDao paperDao;
    @Override
    public paper_question findById(Integer id){return paper_questionDao.findById(id);}
    @Override
    public int findMaxId(){return paper_questionDao.findMaxId();}
    @Override
    public List<String> findByPid(Integer pid){
        List<String> qlist=paper_questionDao.findByPid(pid);
        return qlist;
    }
    @Override
    public void AddNew(Integer pid,String qid){
        Integer id=paper_questionDao.findMaxId();
        if(id==null)id=1;
        else id=id+1;
        paper p=paperDao.findById(pid);
        int numa=p.getNuma();
        int numb=p.getNumb();
        int numc=p.getNumc();
        int numd=p.getNumd();
        String type=qid.charAt(1)+"";
        if(type.equals("a"))paperDao.EditPaperNum(pid,numa+1,numb,numc,numd);
        else if(type.equals("b"))paperDao.EditPaperNum(pid,numa,numb+1,numc,numd);
        else if(type.equals("c"))paperDao.EditPaperNum(pid,numa,numb,numc+1,numd);
        else if(type.equals("d"))paperDao.EditPaperNum(pid,numa,numb,numc,numd+1);
        paper_questionDao.AddPq(id,pid,qid);
    }
    @Override
    public void DeletePq(Integer pid){
        paper_questionDao.DeletePq(pid);
    }
    @Override
    public void DeletePqOne(Integer pid,String qid){
        paper p=paperDao.findById(pid);
        int numa=p.getNuma();
        int numb=p.getNumb();
        int numc=p.getNumc();
        int numd=p.getNumd();
        String type=qid.charAt(1)+"";
        if(type.equals("a"))paperDao.EditPaperNum(pid,numa-1,numb,numc,numd);
        else if(type.equals("b"))paperDao.EditPaperNum(pid,numa,numb-1,numc,numd);
        else if(type.equals("c"))paperDao.EditPaperNum(pid,numa,numb,numc-1,numd);
        else if(type.equals("d"))paperDao.EditPaperNum(pid,numa,numb,numc,numd-1);
        paper_questionDao.DeletePqOne(pid,qid);
    }
}
