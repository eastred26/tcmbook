package com.tcm.tcmbook.web.controller;


import com.tcm.tcmbook.pojo.*;
import com.tcm.tcmbook.service.PrescriptionService;
import com.tcm.tcmbook.service.HerbService;
import com.tcm.tcmbook.utils.stringclear;
import com.tcm.tcmbook.service.BookEntryShlService;
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
@RequestMapping("/bookEntryS")
public class BookEntryShlController {
    @Autowired
    private BookEntryShlService bookEntryService;
    @Autowired
    private PrescriptionService prescriptionService;
    @Autowired
    private HerbService herbService;

    @RequestMapping("/{id}")
    String home(Model model, @PathVariable int id) {
        BookEntryShl bookEntry = bookEntryService.getById(id);
        model.addAttribute("id",id);
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
        model.addAttribute("fanglunxuan",stringclear.clear(bookEntry.getFanglunxuan()));
        model.addAttribute("dianping",stringclear.clear(bookEntry.getDianping()));
        model.addAttribute("linchuangyingyong",stringclear.clear(bookEntry.getLinchuangyingyong()));
        model.addAttribute("anyu",stringclear.clear(bookEntry.getAnyu()));
        model.addAttribute("xiandaiyanjiu",stringclear.clear(bookEntry.getXiandaiyanjiu()));
        model.addAttribute("xiaojie",stringclear.clear(bookEntry.getXiaojie()));
        return "BookEntry_shl";
    }
    @RequestMapping("/yuanwen/{id}")
    String yuanwen(Model model, @PathVariable int id) {
        BookEntryShl bookEntry = bookEntryService.getById(id);
        String content=bookEntry.getYuanwen();
        content=stringclear.clear(content);
        model.addAttribute("title","原文");
        model.addAttribute("content",content);
        return "contents";
    }
    @RequestMapping("/cijie/{id}")
    String cijie(Model model, @PathVariable int id) {
        BookEntryShl bookEntry = bookEntryService.getById(id);
        String content=bookEntry.getCijie();
        content=stringclear.clear(content);
        model.addAttribute("title","词解");
        model.addAttribute("content",content);
        return "contents";
    }
    @RequestMapping("/tiyao/{id}")
    String tiyao(Model model, @PathVariable int id) {
        BookEntryShl bookEntry = bookEntryService.getById(id);
        String content=bookEntry.getTiyao();
        content=stringclear.clear(content);
        model.addAttribute("title","提要");
        model.addAttribute("content",content);
        return "contents";
    }
    @RequestMapping("/shiyi/{id}")
    String shiyi(Model model, @PathVariable int id) {
        BookEntryShl bookEntry = bookEntryService.getById(id);
        String content=bookEntry.getShiyi();
        content=stringclear.clear(content);
        model.addAttribute("title","释义");
        model.addAttribute("content",content);
        return "contents";
    }
    @RequestMapping("/xuanzhu/{id}")
    String xuanzhu(Model model, @PathVariable int id) {
        BookEntryShl bookEntry = bookEntryService.getById(id);
        String content=bookEntry.getXuanzhu();
        content=stringclear.clear(content);
        model.addAttribute("title","选注");
        model.addAttribute("content",content);
        return "contents";
    }
    @RequestMapping("/pingshu/{id}")
    String pingshu(Model model, @PathVariable int id) {
        BookEntryShl bookEntry = bookEntryService.getById(id);
        String content=bookEntry.getPingshu();
        content=stringclear.clear(content);
        model.addAttribute("title","评述");
        model.addAttribute("content",content);
        return "contents";
    }
    @RequestMapping("/zhifa/{id}")
    String zhifa(Model model, @PathVariable int id) {
        BookEntryShl bookEntry = bookEntryService.getById(id);
        String content=bookEntry.getZhifa();
        content=stringclear.clear(content);
        model.addAttribute("title","制法");
        model.addAttribute("content",content);
        model.addAttribute("type","s");
        return "contents";
    }
    @RequestMapping("/fangyaolink/{id}")
    String fangyaolink(Model model, @PathVariable int id) {
        BookEntryShl bookEntry = bookEntryService.getById(id);
        Map<String,String> fangyao_ids=new LinkedHashMap<>();
        String fangyao_id_s=bookEntry.getFangyao_id();
        if(fangyao_id_s!=null&&!fangyao_id_s.isEmpty()){
            String[] ss=fangyao_id_s.split("\\;");
            for(String s:ss){
                int num=Integer.parseInt(s);
                fangyao_ids.put("../../prescription/"+num,prescriptionService.getNameById(num));
            }
            model.addAttribute("fangyao_ids",fangyao_ids);
        }
        model.addAttribute("title","方剂链接");
        return "fangyaolinks";
    }
    @RequestMapping("/fangyi/{id}")
    String fangyi(Model model, @PathVariable int id) {
        BookEntryShl bookEntry = bookEntryService.getById(id);
        String content=bookEntry.getFangyao();
        content=stringclear.clear(content);
        model.addAttribute("title","方药方义");
        model.addAttribute("content",content);
        return "contents";
    }
    @RequestMapping("/fanglunxuan/{id}")
    String fanglunxuan(Model model, @PathVariable int id) {
        BookEntryShl bookEntry = bookEntryService.getById(id);
        String content=bookEntry.getFanglunxuan();
        content=stringclear.clear(content);
        model.addAttribute("title","方论选");
        model.addAttribute("content",content);
        return "contents";
    }
    @RequestMapping("/dianping/{id}")
    String dianping(Model model, @PathVariable int id) {
        BookEntryShl bookEntry = bookEntryService.getById(id);
        String content=bookEntry.getDianping();
        content=stringclear.clear(content);
        model.addAttribute("title","点评");
        model.addAttribute("content",content);
        return "contents";
    }
    @RequestMapping("/linchuangyingyong/{id}")
    String linchuangyingyong(Model model, @PathVariable int id) {
        BookEntryShl bookEntry = bookEntryService.getById(id);
        String content=bookEntry.getLinchuangyingyong();
        content=stringclear.clear(content);
        model.addAttribute("title","临床应用");
        model.addAttribute("content",content);
        return "contents";
    }
    @RequestMapping("/anyu/{id}")
    String anyu(Model model, @PathVariable int id) {
        BookEntryShl bookEntry = bookEntryService.getById(id);
        String content=bookEntry.getAnyu();
        content=stringclear.clear(content);
        model.addAttribute("title","按语");
        model.addAttribute("content",content);
        return "contents";
    }
    @RequestMapping("/xiandaiyanjiu/{id}")
    String xiandaiyanjiu(Model model, @PathVariable int id) {
        BookEntryShl bookEntry = bookEntryService.getById(id);
        String content=bookEntry.getXiandaiyanjiu();
        content=stringclear.clear(content);
        model.addAttribute("title","现代研究");
        model.addAttribute("content",content);
        return "contents";
    }
    @RequestMapping("/xiaojie/{id}")
    String xiaojie(Model model, @PathVariable int id) {
        BookEntryShl bookEntry = bookEntryService.getById(id);
        String content=bookEntry.getXiaojie();
        content=stringclear.clear(content);
        model.addAttribute("title","小结");
        model.addAttribute("content",content);
        return "contents";
    }

    @RequestMapping(value = "/graph/{id}")
    public String graph(@PathVariable Integer id, Model model){
        model.addAttribute("eid",id);
        model.addAttribute("type","S");
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
        BookEntryShl entry=bookEntryService.getById(id);
        String name=entry.getYuanwen();
        name=stringclear.clear4(name);

        String ids="s"+id;
        GNode gNode = new GNode(ids, name,"s",false);
        NodeList.add(gNode);
        String fangyaoss=entry.getFangyao_id();
        if(fangyaoss==null||fangyaoss.isEmpty())return map;
        String[] fangyaos=fangyaoss.split(";");
        for(String s:fangyaos){
            Prescription p=prescriptionService.getById(Integer.parseInt(s));
            String pids="p"+p.getId();
            String pname=p.getFangMing();
            String[] herbs=p.getHerbs().split(";");

            GNode pNode = new GNode(pids, pname,"p",false);
            GEdge gLink1 = new GEdge("sp"+pids+ids, ids,pids);
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