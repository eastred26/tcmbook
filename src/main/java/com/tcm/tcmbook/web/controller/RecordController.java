package com.tcm.tcmbook.web.controller;

import com.tcm.tcmbook.pojo.question;
import com.tcm.tcmbook.pojo.record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/record")
public class RecordController {
    @Autowired
    private com.tcm.tcmbook.service.examService examService;
    @Autowired
    private com.tcm.tcmbook.service.paperService paperService;
    @Autowired
    private com.tcm.tcmbook.service.recordService recordService;
    @Autowired
    private com.tcm.tcmbook.service.questionService questionService;

    //根据记录id获取试卷详情
    @RequestMapping("/toShowExamHist/{id}")
    public String toShowExamHist(@PathVariable ("id")Integer id, Model model, Integer eid){
        //通过记录id查找试卷和答题记录
        List<record> records=recordService.getByUidEid(id,eid);
        List<question> questions=new LinkedList<>();
        for(record r:records){
            question q=questionService.findById(r.getQid());
            questions.add(q);
        }
        model.addAttribute("records",records);
        model.addAttribute("questions",questions);
        return "record/showExamHist2";
    }

}
