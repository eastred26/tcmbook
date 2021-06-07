package com.tcm.tcmbook.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tcm.tcmbook.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/exam")
public class ExamController {
    @Autowired
    private com.tcm.tcmbook.service.examService examService;
    @Autowired
    private com.tcm.tcmbook.service.paperService paperService;
    @Autowired
    private com.tcm.tcmbook.service.questionService questionService;
    @Autowired
    private com.tcm.tcmbook.service.paper_questionService paper_questionService;
    @Autowired
    private com.tcm.tcmbook.service.recordService recordService;
    @Autowired
    private com.tcm.tcmbook.service.userService userService;

    @RequestMapping("/toHist/{id}")
    public String toHist(@PathVariable ("id") Integer id, Model model, String type, @RequestParam(defaultValue = "1") int pageNum,
                         @RequestParam(defaultValue = "8") int pageSize){
        user u=userService.getById(id);
        model.addAttribute("uid",id);
        List<exam> exams=examService.findByUid(id);
        List<String> questionNames=new LinkedList<>();
        String questionTypesRes;
        if(type==null)questionTypesRes="a";
        else questionTypesRes=type;
        List<record> records=recordService.findByUidType(id,questionTypesRes);
        List<record> newRs=new LinkedList<>();
        model.addAttribute("questionTypesRes",questionTypesRes);
        int i=0;
        Set<String> set=new HashSet<>();
        for(record r:records){
            String qid0=r.getQid();
            if(set.contains(qid0))continue;
            set.add(qid0);
            newRs.add(r);
            questionNames.add(questionService.findById(r.getQid()).getAllName());
        }
        model.addAttribute("exams",exams);
        model.addAttribute("records",newRs);
        model.addAttribute("questionNames",questionNames);
        return "exam/history";
    }

    //从其他页面跳转到home
    @RequestMapping("/toHome")
    public String tohome(){
        return "redirect:/index";
    }

    @RequestMapping("/getAllExam")
    public String getAllExam(Model model,@RequestParam(defaultValue = "1") int pageNum,
                             @RequestParam(defaultValue = "8") int pageSize){
        List<exam> exams=examService.findAll();
        PageHelper.startPage(pageNum,pageSize);
        PageInfo<exam> pageInfo = new PageInfo<>(exams);
        model.addAttribute("pageInfo",pageInfo);
        List<String> uNames=new LinkedList<>();

        for(exam e:exams){
            uNames.add(userService.getById(e.getUid()).getName());
        }

        model.addAttribute("uNames",uNames);
        return "exam/getAllExam";
    }

    @RequestMapping("/getAllQuestion")
    public String getAllQuestion(Model model,@RequestParam(defaultValue = "1") int pageNum,
                             @RequestParam(defaultValue = "8") int pageSize,String book,String  type){
        String bookRes="s";
        if(book==null){
            bookRes="s";
        }
        else{
            bookRes=book;
        }
        String typeRes="a";
        String type0;
        PageHelper.startPage(pageNum,pageSize);
        if(type==null||type.equals("a")){
            typeRes="a";
            type0=bookRes+typeRes;
            List<question> questions=questionService.findAllOrderByFalseDesc(type0);
            PageInfo<question> pageInfo = new PageInfo<question>(questions);
            model.addAttribute("pageInfo",pageInfo);
        }
        else{
            typeRes=type;
            type0=bookRes+typeRes;
            List<question> questions=questionService.findAllOrderByFalseDesc(type0);
            PageInfo<question> pageInfo = new PageInfo<question>(questions);
            model.addAttribute("pageInfo",pageInfo);
        }
        model.addAttribute("questionTypesRes",typeRes);
        model.addAttribute("bookRes",bookRes);
        return "exam/getAllQuestion";
    }

    //前台跳转
    @RequestMapping("/toExam")
    public String toExam(Model model,HttpServletRequest request){
        user user = (com.tcm.tcmbook.pojo.user)request.getSession().getAttribute("loger");
        int uid=user.getId();
        System.out.println(uid);
        List<paper> papers1 = paperService.findNotpublishByUid(uid);
        List<paper> papers2 = paperService.findPublish();
        model.addAttribute("papers1",papers1);
        model.addAttribute("papers2",papers2);
        return "paper/paperList";
    }
    //来到对应考试页面
    @RequestMapping("/toDoExam/{id}")
    public String toDoExam(@PathVariable ("id") Integer id,Model model){
        paper paper=paperService.findById(id);
        List<String> questionids = paper_questionService.findByPid(id);
        List<question> questionPapers=new LinkedList<>();
        for(String qid:questionids){
            question q=questionService.findById(qid);
            questionPapers.add(q);
        }
        model.addAttribute("questionPapers",questionPapers);
        model.addAttribute("paper",paper);
        return "exam/doExam";
    }
    //提交试卷
    @RequestMapping("/submitExam")
    public String submitExam(Integer paperId, Integer userId, HttpServletRequest request){
        List<String> questionids = paper_questionService.findByPid(paperId);
        paper paper=paperService.findById(paperId);
        List<String> ans=new ArrayList<>();
        List<String> RightAns=new ArrayList<>();
        for (String qid:questionids){
            String rans=questionService.findById(qid).getAnswer();
            rans=rans.replaceAll("；"," ");
            RightAns.add(rans);
            String parameter="";
            String []parameters;
            if(qid.charAt(1)=='b'){
                parameters= request.getParameterValues(qid);
                for(String s:parameters){
                    parameter+=s;
                }
            }else {
                parameter = request.getParameter(qid);
            }
            ans.add(parameter);
        }
        //核对答案
        int k=0;
        int score_get=0;    //得分
        Integer eid=examService.findMaxId();
        if(eid==null)eid=1;
        else eid=eid+1;
        for (String qid:questionids){
            if(ans.get(k).equals(RightAns.get(k))){
                score_get++;
                recordService.AddRecord(userId,paperId,eid,qid,ans.get(k),1);
                questionService.AddRight(qid);
            }
            else {
                recordService.AddRecord(userId,paperId,eid,qid,ans.get(k),0);
                questionService.AddFalse(qid);
            }
            k++;
        }
        examService.AddExam(eid,userId,paperId,paper.getPaperName(),paper.getScore_all(),score_get);
        return "redirect:/record/toShowExamHist/"+userId+"?eid="+eid;
    }
}
