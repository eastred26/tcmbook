package com.tcm.tcmbook.web.controller;

import com.tcm.tcmbook.dao.TitleDao;
import com.tcm.tcmbook.pojo.BookEntryJgyl;
import com.tcm.tcmbook.pojo.BookEntryShl;
import com.tcm.tcmbook.pojo.Prescription;
import com.tcm.tcmbook.utils.stringclear;
import com.tcm.tcmbook.service.BookEntryJgylService;
import com.tcm.tcmbook.service.BookEntryShlService;
import com.tcm.tcmbook.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
public class HomeController {

    @Autowired
    private BookEntryShlService bookEntryServiceS;

    @Autowired
    private BookEntryJgylService bookEntryServiceJ;

    @Autowired
    private PrescriptionService prescriptionService;
    @Autowired
    private TitleDao titleDao;

    @RequestMapping("/")
    String root() {
        return "redirect:/home";
    }

    @RequestMapping("/home")
    String home(Model model){
        Map<String, Map<Integer, String>> shlMap = new LinkedHashMap<>();
        Map<String, Map<Integer, String>> jgylMap = new LinkedHashMap<>();
        Map<Integer, String> preMap=new HashMap<>();

        List<Prescription> preList=prescriptionService.findAll();

        for(Prescription p:preList){
            preMap.put(p.getId(),p.getFangMing());
        }
        for(int i=1;i<=10;i++){
            Map<Integer, String> shlNameMap=new HashMap<>();
            List<BookEntryShl> shlList=bookEntryServiceS.findByTid(i);
            for(BookEntryShl b:shlList){
                shlNameMap.put(b.getId(),stringclear.clear4(b.getYuanwen()));
            }
            shlMap.put(titleDao.findNameByIds(i),shlNameMap);
        }
        for(int i=1;i<=25;i++){
            Map<Integer, String> jgylNameMap=new HashMap<>();
            List<BookEntryJgyl> shlList=bookEntryServiceJ.findByTid(i);
            for(BookEntryJgyl b:shlList){
                jgylNameMap.put(b.getId(),stringclear.clear4(b.getYuanwen()));
            }
            jgylMap.put(titleDao.findNameByIdj(i),jgylNameMap);
        }

        model.addAttribute("shlMap",shlMap);
        model.addAttribute("jgylMap",jgylMap);
        model.addAttribute("preMap",preMap);
        return "home";
    }
}
