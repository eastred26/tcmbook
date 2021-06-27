package com.tcm.tcmbook.web.controller;


import com.tcm.tcmbook.pojo.Herb;
import com.tcm.tcmbook.pojo.Prescription;
import com.tcm.tcmbook.service.HerbService;
import com.tcm.tcmbook.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class HerbController {
    @Autowired
    private PrescriptionService prescriptionService;
    @Autowired
    private HerbService herbService;

    @RequestMapping("/herb/{id}")
    String home(Model model, @PathVariable int id) {
        Herb herb = herbService.findById(id);
        model.addAttribute("name",herb.getName());

        Map<String,String> preMap=new LinkedHashMap<>();
        char[] cs=herb.getTexts().toCharArray();
        boolean flag=true;
        String key="",value="";
        for(char c:cs){
            if(c=='\n')continue;
            else if(c=='【'){
                if(!key.isEmpty()&&value!=""){
                    preMap.put(key,value);
                }
                flag=true;
                key="【";value="";
            }
            else if(c=='】'){
                flag=false;
                key+='】';
            }
            else if(flag)key+=c;
            else value+=c;
        }
        if(!key.isEmpty()&&value!="")preMap.put(key,value);
        model.addAttribute("preMap",preMap);
        return "herb";
    }
}