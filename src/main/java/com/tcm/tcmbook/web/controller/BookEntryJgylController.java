package com.tcm.tcmbook.web.controller;


import com.tcm.tcmbook.pojo.*;
import com.tcm.tcmbook.service.HerbService;
import com.tcm.tcmbook.service.PrescriptionService;
import com.tcm.tcmbook.utils.stringclear;
import com.tcm.tcmbook.service.BookEntryJgylService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
@RequestMapping("/bookEntryJ")
public class BookEntryJgylController {
    @Autowired
    private BookEntryJgylService bookEntryService;
    @Autowired
    private PrescriptionService prescriptionService;
    @Autowired
    private HerbService herbService;

    @RequestMapping("/{id}")
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
    @RequestMapping("/yuanwen/{id}")
    String yuanwen(Model model, @PathVariable int id) {
        BookEntryJgyl bookEntry = bookEntryService.getById(id);
        String content=bookEntry.getYuanwen();
        content=stringclear.clear(content);
        model.addAttribute("title","原文");
        model.addAttribute("content",content);
        return "contentj";
    }
    @RequestMapping("/ciyuzhujie/{id}")
    String ciyuzhujie(Model model, @PathVariable int id) {
        BookEntryJgyl bookEntry = bookEntryService.getById(id);
        String content=bookEntry.getCiyuzhujie();
        content=stringclear.clear(content);
        model.addAttribute("title","词语注解");
        model.addAttribute("content",content);
        return "contentj";
    }
    @RequestMapping("/jingyichanshi/{id}")
    String jingyichanshi(Model model, @PathVariable int id) {
        BookEntryJgyl bookEntry = bookEntryService.getById(id);
        String content=bookEntry.getJingyichanshi();
        content=stringclear.clear(content);
        model.addAttribute("title","经义阐释");
        model.addAttribute("content",content);
        return "contentj";
    }
    @RequestMapping("/fangyaopingxi/{id}")
    String fangyaopingxi(Model model, @PathVariable int id) {
        BookEntryJgyl bookEntry = bookEntryService.getById(id);
        String content=bookEntry.getFangyaopingxi();
        content=stringclear.clear(content);
        model.addAttribute("title","方药评析");
        model.addAttribute("content",content);
        return "contentj";
    }
    @RequestMapping("/fangyaolink/{id}")
    String fangyaolink(Model model, @PathVariable int id) {
        BookEntryJgyl bookEntry = bookEntryService.getById(id);
        Map<String,String> fangyao_ids=new LinkedHashMap<>();
        String fangyao_id_s=bookEntry.getFangyao_id();
        model.addAttribute("title","方剂链接");
        if(fangyao_id_s!=null&&!fangyao_id_s.isEmpty()){
            String[] ss=fangyao_id_s.split("\\;");
            for(String s:ss){
                int num=Integer.parseInt(s);
                fangyao_ids.put("../../prescription/"+num,prescriptionService.getNameById(num));
            }
            model.addAttribute("fangyao_ids",fangyao_ids);
        }
        return "fangyaolinkj";
    }
    @RequestMapping("/wenxianxuanlu/{id}")
    String wenxianxuanlu(Model model, @PathVariable int id) {
        BookEntryJgyl bookEntry = bookEntryService.getById(id);
        String content=bookEntry.getWenxianxuanlu();
        content=stringclear.clear(content);
        model.addAttribute("title","文献选录");
        model.addAttribute("content",content);
        return "contentj";
    }
    @RequestMapping("/linchuangyingyong/{id}")
    String linchuangyingyong(Model model, @PathVariable int id) {
        BookEntryJgyl bookEntry = bookEntryService.getById(id);
        String content=bookEntry.getLinchuangyingyong();
        content=stringclear.clear(content);
        model.addAttribute("title","临床应用");
        model.addAttribute("content",content);
        return "contentj";
    }
    @RequestMapping("/xiandaiyanjiu/{id}")
    String xiandaiyanjiu(Model model, @PathVariable int id) {
        BookEntryJgyl bookEntry = bookEntryService.getById(id);
        String content=bookEntry.getXiandaiyanjiu();
        content=stringclear.clear(content);
        model.addAttribute("title","现代研究");
        model.addAttribute("content",content);
        return "contentj";
    }
    @RequestMapping(value = "/graph/{id}")
    public String graph(@PathVariable Integer id, Model model){
        model.addAttribute("eid",id);
        model.addAttribute("type","J");
        return "entryGraph";
    }

    //graph是请求节点的子项，并添加进map中
    //graph_init 初始化图。除了请求子项，还有初始点的设置。
    @ResponseBody
    @RequestMapping(value = "/graph_init",method = RequestMethod.GET)
    public HashMap<String, Object> GraphInit(int id){
        HashMap<String, Object> map = new HashMap<String, Object>();
        ArrayList<GNode> NodeList = new ArrayList<GNode>();
        ArrayList<GEdge> EdgeList = new ArrayList<GEdge>();
        BookEntryJgyl entry=bookEntryService.getById(id);
        String name=entry.getYuanwen();
        name=stringclear.clear3(name);
        char[] cs=name.toCharArray();
        int num=0;
        int pos=0;
        for(char c:cs){
            if(c=='，'||c=='。')num++;
            pos++;
            if(num==2)break;
        }
        name=name.substring(0,pos);

        String ids="j"+id;
        GNode gNode = new GNode(ids, name,"j",false);
        NodeList.add(gNode);
        String fangyaoss=entry.getFangyao_id();
        if(fangyaoss.isEmpty())return map;
        String[] fangyaos=fangyaoss.split(";");
        for(String s:fangyaos){
            Prescription p=prescriptionService.getById(Integer.parseInt(s));
            String pids="p"+p.getId();
            String pname=p.getFangMing();
            String[] herbs=p.getHerbs().split(";");

            GNode pNode = new GNode(pids, pname,"p",false);
            GEdge gLink1 = new GEdge("jp"+pids+ids, ids,pids);
            for(String herb:herbs){
                int hid=Integer.parseInt(herb);
                String hname=herbService.getNameById(hid);
                String hids="h"+hid+"_"+pids;
                GNode hNode = new GNode(hids, hname,"h",false);
                GEdge gLink2 = new GEdge("ph"+hids+pids, pids,hids);
                NodeList.add(hNode);
                EdgeList.add(gLink2);
            }
            NodeList.add(pNode);
            EdgeList.add(gLink1);
        }


        map.put("nodes", NodeList);
        map.put("edges", EdgeList);
        return map;
    }
}