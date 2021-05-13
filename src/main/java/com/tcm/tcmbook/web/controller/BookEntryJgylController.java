package com.tcm.tcmbook.web.controller;


import com.tcm.tcmbook.pojo.BookEntryJgyl;
import com.tcm.tcmbook.service.PrescriptionService;
import com.tcm.tcmbook.utils.stringclear;
import com.tcm.tcmbook.service.BookEntryJgylService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class BookEntryJgylController {
    @Autowired
    private BookEntryJgylService bookEntryService;
    @Autowired
    private PrescriptionService prescriptionService;

    @RequestMapping("/bookEntryJ/{id}")
    String home(Model model, @PathVariable int id) {
        BookEntryJgyl bookEntry = bookEntryService.getById(id);
        model.addAttribute("yuanwen",stringclear.clear3(bookEntry.getYuanwen()));
        model.addAttribute("ciyuzhujie",stringclear.clear(bookEntry.getCiyuzhujie()));
        model.addAttribute("jingyichanshi",stringclear.clear(bookEntry.getJingyichanshi()));
        model.addAttribute("fangyaopingxi",stringclear.clear(bookEntry.getFangyaopingxi()));

        model.addAttribute("fangyao_id_flag","0");
        Map<String,String> fangyao_ids=new LinkedHashMap<>();
        String fangyao_id_s=bookEntry.getFangyao_id();
        if(fangyao_id_s!=null&&!fangyao_id_s.isEmpty()){
            String[] ss=fangyao_id_s.split("\\;");
            for(String s:ss){
                int num=Integer.parseInt(s);
                fangyao_ids.put("../prescription/"+num,prescriptionService.getNameById(num));
            }
            model.addAttribute("fangyao_ids",fangyao_ids);
            model.addAttribute("fangyao_id_flag","1");
        }

        model.addAttribute("wenxianxuanlu",stringclear.clear(bookEntry.getWenxianxuanlu()));
        model.addAttribute("linchuangyingyong",stringclear.clear(bookEntry.getLinchuangyingyong()));
        model.addAttribute("xiandaiyanjiu",stringclear.clear(bookEntry.getXiandaiyanjiu()));

        return "BookEntry_jgyl";
    }
}