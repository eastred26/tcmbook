package com.tcm.tcmbook.web.controller;


import com.tcm.tcmbook.pojo.BookEntryShl;
import com.tcm.tcmbook.service.PrescriptionService;
import com.tcm.tcmbook.utils.stringclear;
import com.tcm.tcmbook.service.BookEntryShlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class BookEntryShlController {
    @Autowired
    private BookEntryShlService bookEntryService;
    @Autowired
    private PrescriptionService prescriptionService;

    @RequestMapping("/bookEntryS/{id}")
    String home(Model model, @PathVariable int id) {
        BookEntryShl bookEntry = bookEntryService.getById(id);
        model.addAttribute("title",stringclear.clear2(bookEntry.getTitle()));
        model.addAttribute("tiyao",stringclear.clear(bookEntry.getTiyao()));
        model.addAttribute("yuanwen",stringclear.clear2(bookEntry.getYuanwen()));
        model.addAttribute("cijie",stringclear.clear(bookEntry.getCijie()));
        model.addAttribute("shiyi",stringclear.clear(bookEntry.getShiyi()));
        model.addAttribute("xuanzhu",stringclear.clear(bookEntry.getXuanzhu()));
        model.addAttribute("pingshu",stringclear.clear(bookEntry.getPingshu()));
        model.addAttribute("zhifa",stringclear.clear(bookEntry.getZhifa()));
        model.addAttribute("fangyao",stringclear.clear(bookEntry.getFangyao()));

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
        model.addAttribute("fangjie",stringclear.clear(bookEntry.getFangjie()));
        model.addAttribute("fangyi",stringclear.clear(bookEntry.getFangyi()));
        model.addAttribute("fanglunxuan",stringclear.clear(bookEntry.getFanglunxuan()));
        model.addAttribute("dianping",stringclear.clear(bookEntry.getDianping()));
        model.addAttribute("linchuangyingyong",stringclear.clear(bookEntry.getLinchuangyingyong()));
        model.addAttribute("anyu",stringclear.clear(bookEntry.getAnyu()));
        model.addAttribute("xiandaiyanjiu",stringclear.clear(bookEntry.getXiandaiyanjiu()));
        model.addAttribute("xiaojie",stringclear.clear(bookEntry.getXiaojie()));
        return "BookEntry_shl";
    }
}