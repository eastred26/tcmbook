package com.tcm.tcmbook.service.impl;

import com.tcm.tcmbook.pojo.paper;
import com.tcm.tcmbook.pojo.question;
import com.tcm.tcmbook.service.paperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class paperServiceImpl implements paperService {
    @Autowired
    private com.tcm.tcmbook.dao.paperDao paperDao;
    @Autowired
    private com.tcm.tcmbook.service.paper_questionService paper_questionService;
    @Autowired
    private com.tcm.tcmbook.dao.questionDao questionDao;
    @Autowired
    private com.tcm.tcmbook.dao.userDao userDao;
    @Override
    public paper findById(Integer id){return paperDao.findById(id);}
    @Override
    public Integer findMaxId(){return paperDao.findMaxId();}
    @Override
    public List<paper> findAll(){return paperDao.findAll();}
    @Override
    public int AddPaper(paper paper, Integer publish){
        Random random=new Random();
        Integer pid=paperDao.findMaxId();
        if(pid==null)pid=1;
        else pid=pid+1;
        String ptype=paper.getType();
        int a=paper.getNuma();
        int b=paper.getNumb();
        int c=paper.getNumc();
        int d=paper.getNumd();
        int uid=paper.getCreatorId();
        String createName=userDao.findById(uid).getName();
        paperDao.AddPaper(pid,paper.getPaperName(),"",ptype,uid,0,0,0,0,0,new Date(),createName,publish);
        Set<String> setq=new HashSet<>();
        List<String> listq=new LinkedList<>();
        for(int i=1;i<=a;i++){
            int r;
            String qid;
            do{
                String type="ja";
                int typeid=random.nextInt(2);
                if(typeid==0){
                    type="sa";
                    r=random.nextInt(question.numsa)+1;
                }
                else{
                    r=random.nextInt(question.numja)+1;
                }
                qid=type+r;
            } while (setq.contains(qid));
            setq.add(qid);
            listq.add(qid);
        }
        for(int i=1;i<=b;i++){
            int r;
            String qid;
            do{
                String type="jb";
                int typeid=random.nextInt(2);
                if(typeid==0){
                    type="sb";
                    r=random.nextInt(question.numsb)+1;
                }
                else{
                    r=random.nextInt(question.numjb)+1;
                }
                qid=type+r;
            } while (setq.contains(qid));
            setq.add(qid);
            listq.add(qid);
        }
        for(int i=1;i<=c;i++){
            int r;
            String qid;
            do{
                String type="jc";
                int typeid=random.nextInt(2);
                if(typeid==0){
                    type="sc";
                    r=random.nextInt(question.numsc)+1;
                }
                else{
                    r=random.nextInt(question.numjc)+1;
                }
                qid=type+r;
            } while (setq.contains(qid));
            setq.add(qid);
            listq.add(qid);
        }
        for(int i=1;i<=d;i++){
            int r;
            String qid;
            do{
                String type="jd";
                int typeid=random.nextInt(2);
                if(typeid==0){
                    type="sd";
                    r=random.nextInt(question.numsd)+1;
                }
                else{
                    r=random.nextInt(question.numjd)+1;
                }
                qid=type+r;
            } while (setq.contains(qid));
            setq.add(qid);
            listq.add(qid);
        }
        for(String qid:listq){
            paper_questionService.AddNew(pid,qid);
        }
        return pid;
    }
    @Override
    public void EditPaper(Integer pid,String paperName,String paperIntro,String type){paperDao.EditPaper(pid,paperName,paperIntro,type);}
    @Override
    public void DeletePaper(Integer pid){paperDao.DeletePaper(pid);}
    @Override
    public void EditPaperNum(Integer pid,Integer numa,Integer numb,Integer numc,Integer numd){
        paperDao.EditPaperNum(pid,numa,numb,numc,numd);
    }
    @Override
    public Integer findNum(){
        return paperDao.findNum();
    }
    @Override
    public void PublishPaper(Integer pid){
        paperDao.PublishPaper(pid);
    }
    @Override
    public List<paper> findPublish(){
        return paperDao.findPublish();
    }
    @Override
    public List<paper> findNotpublishByUid(Integer uid){
        return paperDao.findNotpublishByUid(uid);
    }

}
