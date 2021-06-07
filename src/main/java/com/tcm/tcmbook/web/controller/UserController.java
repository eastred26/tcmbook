package com.tcm.tcmbook.web.controller;

import com.tcm.tcmbook.pojo.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private com.tcm.tcmbook.service.userService userService;
    @Autowired
    private com.tcm.tcmbook.service.paperService paperService;
    @Autowired
    private com.tcm.tcmbook.service.recordService recordService;
    @Autowired
    private com.tcm.tcmbook.service.questionService questionService;

    @RequestMapping("/userEdit")
    public String toShowExamHist(Model model){
        List<user> users=userService.findAll();
        model.addAttribute("users",users);
        return"user/userEdit";
    }
    //试卷删除操作
    @RequestMapping("/deleteUser/{id}")
    public String deletePaperById(@PathVariable("id") Integer id,Model model){
        userService.DeleteUser(id);
        return "redirect:/user/userEdit";
    }

}
