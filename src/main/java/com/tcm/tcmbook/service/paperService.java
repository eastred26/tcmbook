package com.tcm.tcmbook.service;

import com.tcm.tcmbook.pojo.paper;

import java.util.List;


public interface paperService {
    paper findById(Integer id);
    Integer findMaxId();
    List<paper> findAll();
    void EditPaper(Integer pid, String paperName, String paperIntro, String type);
    void EditPaperNum(Integer pid, Integer numa, Integer numb, Integer numc, Integer numd);
    int AddPaper(paper paper, Integer publish);
    void DeletePaper(Integer pid);
    Integer findNum();
    List<paper> findPublish();
    List<paper> findNotpublishByUid(Integer uid);
    void PublishPaper(Integer pid);
}
