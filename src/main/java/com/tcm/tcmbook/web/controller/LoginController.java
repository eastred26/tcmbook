package com.tcm.tcmbook.web.controller;

import com.tcm.tcmbook.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class LoginController {
    @Autowired
    private com.tcm.tcmbook.service.userService userService;
    @Autowired
    private com.tcm.tcmbook.service.examService examService;
    @Autowired
    private com.tcm.tcmbook.service.paperService paperService;
    @Autowired
    private com.tcm.tcmbook.service.recordService recordService;
    @Autowired
    private com.tcm.tcmbook.service.questionService questionService;
    @RequestMapping("/login")
    public String view(Model model){
        return "stage/prexam";
    }
    @RequestMapping("/foreLogin")
    public String foreLogin(){
        return "stage/login";
    }
    @RequestMapping("/backLogin")
    public String backLogin(){
        return "stage/backlogin";
    }
    @ResponseBody
    @RequestMapping("/foreCheck/check")
    public Object foreCheck(user user, HttpServletRequest request){
        AjaxResult result=new AjaxResult();
        HttpSession session=request.getSession();
        com.tcm.tcmbook.pojo.user u1=userService.check(user.getAccount(),user.getPwd());
        if(u1!=null&&u1.getPower()==0){
            session.setAttribute("loger",u1);
            result.setSuccess(true);
        }else {
            result.setSuccess(false);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/backCheck/check")
    public Object backCheck(user user, HttpServletRequest request){
        AjaxResult result=new AjaxResult();
        HttpSession session=request.getSession();
        com.tcm.tcmbook.pojo.user u1=userService.check(user.getAccount(),user.getPwd());
        if(u1!=null&&u1.getPower()==1){
            session.setAttribute("loger",u1);
            result.setSuccess(true);
        }else {
            result.setSuccess(false);
        }
        return result;
    }

    //前台登录到展示页面
    @RequestMapping("/index")
    public String indexprexam(Model model){
        int recordNum=recordService.findNum();
        int papNum=paperService.findNum();
        model.addAttribute("recordNum",recordNum);
        model.addAttribute("queNum",4614+12834);
        model.addAttribute("papNum",papNum);

        List<exam> exams=examService.findAllOrderByRateDesc();
        model.addAttribute("exams",exams);

        List<record> rs=recordService.findFalse();
        Map<String, Integer> map = new HashMap<String, Integer>();
        Map<String, String> falseAns = new HashMap<String, String>();
        for(record r:rs){
            String qid=r.getQid();
            if(map.keySet().contains(qid)){
                int num=map.get(qid)+1;
                map.put(qid,num);
            }
            else {
                falseAns.put(qid,r.getYourAnswer());
                map.put(qid,1);
            }
        }
        List<question> questions=new LinkedList<>();
        List<Integer> nums=new LinkedList<>();
        List<String> anss=new LinkedList<>();
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
        Collections.sort(list, (o1, o2) -> (o2.getValue() - o1.getValue()));
        int num=0;
        for(Map.Entry<String, Integer> t:list){
            questions.add(questionService.findById(t.getKey()));
            nums.add(t.getValue());
            anss.add(falseAns.get(t.getKey()));
            num++;
            if(num>4)break;
        }
        model.addAttribute("questions",questions);
        model.addAttribute("nums",nums);
        model.addAttribute("anss",anss);
        return "stage/prexamed";
    }

    //退出系统
    @RequestMapping(value = {"*/logout","/logout"})
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
    //学生注册
    //去添加页面
    @RequestMapping("/prexam/toAddStudent")
    public String toAddStudent(Model model){
        return "stage/studentAdd";
    }
    //添加具体操作
    @RequestMapping("/prexam/AddStudent")
    public String AddStudent(user u1){
        int uid=userService.getMaxId()+1;
        System.out.println(u1);
        userService.AddUser(uid,u1.getName(),u1.getAccount(),u1.getPwd());
        return "redirect:/foreLogin";
    }

    @RequestMapping("/backindex")
    public String index(Model model){
        //查询所有用户
        int recordNum=recordService.findNum();
        int papNum=paperService.findNum();
        model.addAttribute("recordNum",recordNum);
        model.addAttribute("queNum",4614+12834);
        model.addAttribute("papNum",papNum);

        List<exam> exams=examService.findAllOrderByRateDesc();
        model.addAttribute("exams",exams);

        List<record> rs=recordService.findFalse();
        Map<String, Integer> map = new HashMap<String, Integer>();
        Map<String, String> falseAns = new HashMap<String, String>();
        for(record r:rs){
            String qid=r.getQid();
            if(map.keySet().contains(qid)){
                int num=map.get(qid)+1;
                map.put(qid,num);
            }
            else {
                falseAns.put(qid,r.getYourAnswer());
                map.put(qid,1);
            }
        }
        List<question> questions=new LinkedList<>();
        List<Integer> nums=new LinkedList<>();
        List<String> anss=new LinkedList<>();
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
        Collections.sort(list, (o1, o2) -> (o2.getValue() - o1.getValue()));
        int num=0;
        for(Map.Entry<String, Integer> t:list){
            questions.add(questionService.findById(t.getKey()));
            nums.add(t.getValue());
            anss.add(falseAns.get(t.getKey()));
            num++;
            if(num>4)break;
        }
        model.addAttribute("questions",questions);
        model.addAttribute("nums",nums);
        model.addAttribute("anss",anss);

        return "stage/index";
    }

}
