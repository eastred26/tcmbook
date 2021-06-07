package com.tcm.tcmbook.web.controller;

import com.tcm.tcmbook.pojo.question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.*;

@Controller
@RequestMapping("/question")
public class questionController {
    @Autowired
    private com.tcm.tcmbook.service.userService userService;
    @Autowired
    private com.tcm.tcmbook.service.paperService paperService;
    @Autowired
    private com.tcm.tcmbook.service.recordService recordService;
    @Autowired
    private com.tcm.tcmbook.service.questionService questionService;

    @RequestMapping("/toAddQuestion")
    public String toAddQuestion(){
        return "question/addQuestion";
    }

    @RequestMapping("/AddQuestion")
    public String AddQuestion(File file) throws IOException {
        String path=file.getCanonicalPath();
        BufferedReader in = new BufferedReader(new FileReader(path));
        String str;
        while ((str = in.readLine()) != null) {
            System.out.println(str);
        }

        return "redirect:/question/toAddQuestion";
    }

}

