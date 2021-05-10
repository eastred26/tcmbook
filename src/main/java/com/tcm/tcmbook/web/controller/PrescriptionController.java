package com.tcm.tcmbook.web.controller;


import com.tcm.tcmbook.pojo.Prescription;
import com.tcm.tcmbook.service.BookEntryService;
import com.tcm.tcmbook.service.HerbService;
import com.tcm.tcmbook.service.PrescriptionService;
import com.tcm.tcmbook.utils.stringclear;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class PrescriptionController {
    @Autowired
    private PrescriptionService prescriptionService;
    @Autowired
    private HerbService herbService;

    @RequestMapping("/prescription/{id}")
    String home(Model model, @PathVariable int id) {
        Prescription prescription = prescriptionService.getById(id);
        model.addAttribute("FangMing",prescription.getFangMing());
        String[] ss1=prescription.getHerbs().split("\\;");
        String[] ss2=prescription.getWeights().split("\\;");
        String res="";
        if(ss1.length!=ss2.length){
            for(String item:ss1){
                if(item.isEmpty())continue;
                res+=herbService.getNameById(Integer.parseInt(item))+";";
            }
        }
        else{
            int l=ss1.length;
            for(int i=0;i<l;i++){
                res+=herbService.getNameById(Integer.parseInt(ss1[i]))+ss2[i]+";";
            }
        }
        model.addAttribute("herbs",res);
        Map<String,String> preMap=new LinkedHashMap<>();
        char[] cs=prescription.getTexts().toCharArray();
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
        return "prescription";
    }
}