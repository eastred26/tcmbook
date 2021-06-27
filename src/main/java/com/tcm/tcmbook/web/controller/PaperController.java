package com.tcm.tcmbook.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tcm.tcmbook.pojo.paper;
import com.tcm.tcmbook.pojo.question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/paper")
public class PaperController {
    @Autowired
    private com.tcm.tcmbook.service.questionService questionService;
    @Autowired
    private com.tcm.tcmbook.service.paperService paperService;
    @Autowired
    private com.tcm.tcmbook.service.examService examService;
    @Autowired
    private com.tcm.tcmbook.service.recordService recordService;
    @Autowired
    private com.tcm.tcmbook.service.paper_questionService paper_questionService;

    //试卷添加或者修改操作，先去添加页面
    @RequestMapping("/toAddPaper")
    public String toAddPaper(Model model){
        return "paper/paperAdd";
    }

    @RequestMapping("/toAddPaper1")
    public String toAddPaper1(Model model,String name,Integer a,Integer b,Integer c){
        model.addAttribute("name",name);
        model.addAttribute("a",a);
        model.addAttribute("b",b);
        model.addAttribute("c",c);
        return "paper/paperAdd1";
    }

    //试卷去修改页面
    @RequestMapping("/toEditPaper/{id}")
    public String toEditPaper(@PathVariable("id") Integer id,Model model){
        paper paper=paperService.findById(id);
        model.addAttribute("paper",paper);
        return "/paper/paperEdit";
    }
    //试卷修改
    @RequestMapping("/EditPaper")
    public String toEditPaper(Integer pid,String paperName,String paperIntro,String type){
        paperService.EditPaper(pid,paperName,paperIntro,type);
        return "redirect:/exam/toExam";
    }
    //添加具体操作
    @RequestMapping("/addPaper")
    public String addPaper(paper paper){
        int pid=paperService.AddPaper(paper,0);
        return "redirect:/paper/toManagerQuestion/"+pid;
    }
    //试卷删除操作
    @RequestMapping("/deletePaper/{id}")
    public String deletePaperById(@PathVariable("id") Integer id,Model model){
        paperService.DeletePaper(id);
        paper_questionService.DeletePq(id);
        return "redirect:/exam/toExam";
    }
    //去往试题管理页面
    @RequestMapping("/toManagerQuestion/{id}")
    public String toManagerQuestion(@PathVariable("id") Integer id,Model model){
        List<String> qidList=paper_questionService.findByPid(id);
        model.addAttribute("qids",qidList);
        List<question> questionList=new LinkedList<>();
        for(String qid:qidList){
            questionList.add(questionService.findById(qid));
        }
        model.addAttribute("papid",id);
        paper paperName=paperService.findById(id);
        model.addAttribute("paperName",paperName.getPaperName());
        model.addAttribute("paperIntro",paperName.getPaperIntro());
        model.addAttribute("type",paperName.getType());
        model.addAttribute("questionPapers",questionList);
        model.addAttribute("a",paperName.getNuma());
        model.addAttribute("b",paperName.getNumb());
        model.addAttribute("c",paperName.getNumc());
        return "paper/ManagerQuestion";
    }
    //去往试题管理页面-管理员
    @RequestMapping("/toManagerQuestion2/{id}")
    public String toManagerQuestion2(@PathVariable("id") Integer id,Model model){
        List<String> qidList=paper_questionService.findByPid(id);
        model.addAttribute("qids",qidList);
        List<question> questionList=new LinkedList<>();
        for(String qid:qidList){
            questionList.add(questionService.findById(qid));
        }
        model.addAttribute("papid",id);
        paper paperName=paperService.findById(id);
        model.addAttribute("paperName",paperName.getPaperName());
        model.addAttribute("paperIntro",paperName.getPaperIntro());
        model.addAttribute("type",paperName.getType());
        model.addAttribute("questionPapers",questionList);
        model.addAttribute("a",paperName.getNuma());
        model.addAttribute("b",paperName.getNumb());
        model.addAttribute("c",paperName.getNumc());
        return "paper/ManagerQuestion2";
    }
    @RequestMapping("/deleteAllQues/{id}")
    public String deleteAllQues(@PathVariable ("id") Integer papid,String []ques){
        for(String num:ques){
            paper_questionService.DeletePqOne(papid,num);
        }
        return "redirect:/paper/toManagerQuestion/"+papid;
    }
    //从试卷中移除试题
    @RequestMapping("/detachQuestion")
    public String detachQuestion(String qpId,Integer paperId){
        paper_questionService.DeletePqOne(paperId,qpId);
        return "redirect:/paper/toManagerQuestion/"+paperId;
    }


    @RequestMapping("/toAddQuestion/{id}")
    public String getAllQuestion(@PathVariable("id") Integer paperId,String book,String  type,@RequestParam(defaultValue = "1") int pageNum,
                                 @RequestParam(defaultValue = "8") int pageSize,
                                 Model model){
        //若是第一次查询则用上次提交的表单中的类型、课程，若是第二次查询则延用上次类型
        String questionTypeBef=type;
        String bookBef=book;
        String questionTypesRes="";
        String bookRes="s";
        String type0;
        PageHelper.startPage(pageNum,pageSize);//这行是重点，表示从pageNum页开始，每页pageSize条数据
        if(bookBef==null){
            bookRes="s";
        }
        else{
            bookRes=bookBef;
        }
        if(questionTypeBef==null||questionTypeBef.equals("a")){
            questionTypesRes="a";
            type0=bookRes+questionTypesRes;
            List<question> questions=questionService.findByBookType(type0);
            PageInfo<question> pageInfo = new PageInfo<question>(questions);
            model.addAttribute("pageInfo",pageInfo);
        }
        else{
            questionTypesRes=questionTypeBef;
            type0=bookRes+questionTypesRes;
            List<question> questions=questionService.findByBookType(type0);
            PageInfo<question> pageInfo = new PageInfo<question>(questions);
            model.addAttribute("pageInfo",pageInfo);
        }
        List<String> questionids=paper_questionService.findByPid(paperId);
        model.addAttribute("quesds",questionids);
        String paperName=paperService.findById(paperId).getPaperName();
        model.addAttribute("paperName",paperName);
        model.addAttribute("questionTypesRes",questionTypesRes);
        model.addAttribute("paperId",paperId);
        model.addAttribute("bookRes",bookRes);
        return "paper/AddQuestion";
    }

    //添加具体操作
    @RequestMapping("/AddQuestion")
    public String addQuestion(Integer pid,String qid,String questionTypesRes){
        paper_questionService.AddNew(pid,qid);
        return "redirect:/paper/toAddQuestion/"+pid+"?type="+questionTypesRes;
    }

    @RequestMapping("/getAllPaper")
    public String toExam(Model model){
        List<paper> papers = paperService.findAll();
        for(paper p:papers){
            int pid=p.getId();
            List<Integer> l=examService.findScoreByPid(pid);
            int sum=0;
            for(Integer num:l){
                sum+=num;
            }
            double score_ave=1.0*sum/l.size();
            java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");
            p.setScore_aveString(""+df.format(score_ave));
        }
        model.addAttribute("papers",papers);
        return "paper/paperList1";
    }
    @RequestMapping("/publish/{id}")
    public String publish(Model model,@PathVariable("id") Integer paperId){
        paperService.PublishPaper(paperId);
        return "redirect:/exam/toExam";
    }
}
