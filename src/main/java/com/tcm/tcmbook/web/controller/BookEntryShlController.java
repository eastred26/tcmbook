package com.tcm.tcmbook.web.controller;


import com.tcm.tcmbook.pojo.*;
import com.tcm.tcmbook.service.EntryFangyaoService;
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

import java.util.*;

@Controller
@RequestMapping("/bookEntryS")
public class BookEntryShlController {
    @Autowired
    private BookEntryShlService bookEntryService;
    @Autowired
    private PrescriptionService prescriptionService;
    @Autowired
    private HerbService herbService;
    @Autowired
    private EntryFangyaoService entryFangyaoService;

    @RequestMapping("/{id}")
    String home(Model model, @PathVariable int id) {
        BookEntryShl bookEntry = bookEntryService.getById(id);
        model.addAttribute("id",id);
        model.addAttribute("title",stringclear.clear3(bookEntry.getTitle()));
        model.addAttribute("tiyao",stringclear.clear(bookEntry.getTiyao()));
        model.addAttribute("yuanwen",stringclear.clear(bookEntry.getYuanwen()));
        model.addAttribute("cijie",stringclear.clear(bookEntry.getCijie()));
        model.addAttribute("shiyi",stringclear.clear(bookEntry.getShiyi()));
        model.addAttribute("xuanzhu",stringclear.clear(bookEntry.getXuanzhu()));
        model.addAttribute("pingshu",stringclear.clear(bookEntry.getPingshu()));
        model.addAttribute("zhifa",stringclear.clear(bookEntry.getZhifa()));

        Map<String,String> fangyao_ids=new LinkedHashMap<>();
        Map<EntryFangyao,String> fangyao_ids2=new LinkedHashMap<>();

        String fangyao_id_s=bookEntry.getFangyao_id();
        if(fangyao_id_s!=null&&!fangyao_id_s.isEmpty()){
            String[] ss=fangyao_id_s.split("\\;");
            if(ss.length>1){
                for(String s:ss){
                    int num=Integer.parseInt(s);
                    EntryFangyao ef=entryFangyaoService.findByEidPidS(id,num);
                    String fangyi=stringclear.clear(ef.getFangyi());
                    String fanglunxuan=stringclear.clear(ef.getFanglunxuan());
                    String linchuangyingyong=stringclear.clear(ef.getLinchuangyingyong());
                    String yian=stringclear.clear(ef.getYian());
                    ef.setFanglunxuan(fanglunxuan);
                    ef.setFangyi(fangyi);
                    ef.setLinchuangyingyong(linchuangyingyong);
                    ef.setYian(yian);
                    fangyao_ids2.put(ef,prescriptionService.getNameById(num));
                }
                model.addAttribute("fangyao_ids2",fangyao_ids2);
                model.addAttribute("fangyao_id_flag",2);
            }
            else{
                int num=Integer.parseInt(fangyao_id_s);
                model.addAttribute("fangyao",stringclear.clearfang(bookEntry.getFangyao()));
                model.addAttribute("fanglunxuan",stringclear.clear(bookEntry.getFanglunxuan()));
                model.addAttribute("linchuangyingyong",stringclear.clear(bookEntry.getLinchuangyingyong()));
                model.addAttribute("fangyao_link","../prescription/"+num);
                model.addAttribute("fangyao_name",prescriptionService.getNameById(num));
                model.addAttribute("fangyao_id_flag",1);
            }
        }
        else{
            model.addAttribute("fangyao_id_flag",0);
        }
        model.addAttribute("dianping",stringclear.clear(bookEntry.getDianping()));
        model.addAttribute("anyu",stringclear.clear(bookEntry.getAnyu()));
        model.addAttribute("xiandaiyanjiu",stringclear.clear(bookEntry.getXiandaiyanjiu()));
        model.addAttribute("xiaojie",stringclear.clear(bookEntry.getXiaojie()));
        model.addAttribute("link1","./"+(id-1));
        model.addAttribute("link2","./"+(id+1));
        return "BookEntry_shl";
    }
    @RequestMapping("/yuanwen/{id}")
    String yuanwen(Model model, @PathVariable int id) {
        BookEntryShl bookEntry = bookEntryService.getById(id);
        model.addAttribute("title","原文");
        model.addAttribute("content","");
        String yuanwen=stringclear.clear3(bookEntry.getYuanwen());
        model.addAttribute("yuanwen",yuanwen);
        model.addAttribute("link1","./"+(id-1));
        model.addAttribute("link2","./"+(id+1));
        model.addAttribute("sidebar","/bookEntryS/yuanwen");
        return "contents";
    }
    @RequestMapping("/cijie/{id}")
    String cijie(Model model, @PathVariable int id) {
        BookEntryShl bookEntry = bookEntryService.getById(id);
        String content=bookEntry.getCijie();
        content=stringclear.clear(content);
        model.addAttribute("title","词解");
        model.addAttribute("content",content);
        String yuanwen=stringclear.clear(bookEntry.getYuanwen());
        model.addAttribute("yuanwen",yuanwen);
        model.addAttribute("link1","./"+(id-1));
        model.addAttribute("link2","./"+(id+1));
        model.addAttribute("sidebar","/bookEntryS/cijie");
        return "contents";
    }
    @RequestMapping("/tiyao/{id}")
    String tiyao(Model model, @PathVariable int id) {
        BookEntryShl bookEntry = bookEntryService.getById(id);
        String content=bookEntry.getTiyao();
        content=stringclear.clear(content);
        model.addAttribute("title","提要");
        model.addAttribute("content",content);
        String yuanwen=stringclear.clear3(bookEntry.getYuanwen());
        model.addAttribute("yuanwen",yuanwen);
        model.addAttribute("link1","./"+(id-1));
        model.addAttribute("link2","./"+(id+1));
        model.addAttribute("sidebar","/bookEntryS/tiyao");
        return "contents";
    }
    @RequestMapping("/shiyi/{id}")
    String shiyi(Model model, @PathVariable int id) {
        BookEntryShl bookEntry = bookEntryService.getById(id);
        String content=bookEntry.getShiyi();
        content=stringclear.clear(content);
        model.addAttribute("title","释义");
        model.addAttribute("content",content);
        String yuanwen=stringclear.clear3(bookEntry.getYuanwen());
        model.addAttribute("yuanwen",yuanwen);
        model.addAttribute("link1","./"+(id-1));
        model.addAttribute("link2","./"+(id+1));
        model.addAttribute("sidebar","/bookEntryS/shiyi");
        return "contents";
    }
    @RequestMapping("/xuanzhu/{id}")
    String xuanzhu(Model model, @PathVariable int id) {
        BookEntryShl bookEntry = bookEntryService.getById(id);
        String content=bookEntry.getXuanzhu()+bookEntry.getPingshu();
        content=stringclear.clear(content);
        model.addAttribute("title","选注");
        model.addAttribute("content",content);
        String yuanwen=stringclear.clear3(bookEntry.getYuanwen());
        model.addAttribute("yuanwen",yuanwen);
        model.addAttribute("link1","./"+(id-1));
        model.addAttribute("link2","./"+(id+1));
        model.addAttribute("sidebar","/bookEntryS/xuanzhu");
        return "contents";
    }
    @RequestMapping("/pingshu/{id}")
    String pingshu(Model model, @PathVariable int id) {
        BookEntryShl bookEntry = bookEntryService.getById(id);
        String content=bookEntry.getPingshu();
        content=stringclear.clear(content);
        model.addAttribute("title","评述");
        model.addAttribute("content",content);
        String yuanwen=stringclear.clear3(bookEntry.getYuanwen());
        model.addAttribute("yuanwen",yuanwen);
        model.addAttribute("link1","./"+(id-1));
        model.addAttribute("link2","./"+(id+1));
        model.addAttribute("sidebar","/bookEntryS/pingshu");
        return "contents";
    }
    @RequestMapping("/zhifa/{id}")
    String zhifa(Model model, @PathVariable int id) {
        BookEntryShl bookEntry = bookEntryService.getById(id);
        String content=bookEntry.getZhifa();
        content=stringclear.clear(content);
        model.addAttribute("title","治法");
        model.addAttribute("content",content);
        String yuanwen=stringclear.clear3(bookEntry.getYuanwen());
        model.addAttribute("yuanwen",yuanwen);
        model.addAttribute("type","s");
        model.addAttribute("link1","./"+(id-1));
        model.addAttribute("link2","./"+(id+1));
        model.addAttribute("sidebar","/bookEntryS/zhifa");
        return "contents";
    }
    @RequestMapping("/fangyaolink/{id}")
    String fangyaolink(Model model, @PathVariable int id) {
        BookEntryShl bookEntry = bookEntryService.getById(id);
        String fangyao_id_s=bookEntry.getFangyao_id();
        Map<EntryFangyao,String> fangyao_ids2=new LinkedHashMap<>();
        if(fangyao_id_s!=null&&!fangyao_id_s.isEmpty()){
            model.addAttribute("dianping",stringclear.clear(bookEntry.getDianping()));
            model.addAttribute("anyu",stringclear.clear(bookEntry.getAnyu()));
            model.addAttribute("xiandaiyanjiu",stringclear.clear(bookEntry.getXiandaiyanjiu()));
            String[] ss=fangyao_id_s.split("\\;");
            if(ss.length>1){
                for(String s:ss){
                    int num=Integer.parseInt(s);
                    EntryFangyao ef=entryFangyaoService.findByEidPidS(id,num);
                    String fangyi=stringclear.clear(ef.getFangyi());
                    String fanglunxuan=stringclear.clear(ef.getFanglunxuan());
                    String linchuangyingyong=stringclear.clear(ef.getLinchuangyingyong());
                    String yian=stringclear.clear(ef.getYian());
                    ef.setFanglunxuan(fanglunxuan);
                    ef.setFangyi(fangyi);
                    ef.setLinchuangyingyong(linchuangyingyong);
                    ef.setYian(yian);
                    fangyao_ids2.put(ef,prescriptionService.getNameById(num));
                }
                model.addAttribute("fangyao_ids2",fangyao_ids2);
                model.addAttribute("fangyao_id_flag",2);
            }
            else{
                int num=Integer.parseInt(fangyao_id_s);
                model.addAttribute("fangyao",stringclear.clearfang(bookEntry.getFangyao()));
                model.addAttribute("fanglunxuan",stringclear.clear(bookEntry.getFanglunxuan()));
                model.addAttribute("linchuangyingyong",stringclear.clear(bookEntry.getLinchuangyingyong()));
                model.addAttribute("fangyao_link","../../prescription/"+num);
                model.addAttribute("fangyao_name",prescriptionService.getNameById(num));
                model.addAttribute("fangyao_id_flag",1);
            }
        }
        else{
            model.addAttribute("dianping","");
            model.addAttribute("anyu","");
            model.addAttribute("xiandaiyanjiu","");
            model.addAttribute("fangyao_id_flag",0);
        }
        model.addAttribute("title","方剂");
        model.addAttribute("link1","./"+(id-1));
        model.addAttribute("link2","./"+(id+1));
        return "fangyaolinks";
    }
    @RequestMapping("/fangyi/{id}")
    String fangyi(Model model, @PathVariable int id) {
        BookEntryShl bookEntry = bookEntryService.getById(id);
        String content=bookEntry.getFangyao();
        content=stringclear.clear(content);
        model.addAttribute("title","方药方义");
        model.addAttribute("content",content);
        String yuanwen=stringclear.clear3(bookEntry.getYuanwen());
        model.addAttribute("yuanwen",yuanwen);
        model.addAttribute("sidebar","/bookEntryS/fangyi");
        model.addAttribute("link1","./"+(id-1));
        model.addAttribute("link2","./"+(id+1));
        return "contents";
    }
    @RequestMapping("/fanglunxuan/{id}")
    String fanglunxuan(Model model, @PathVariable int id) {
        BookEntryShl bookEntry = bookEntryService.getById(id);
        String content=bookEntry.getFanglunxuan();
        content=stringclear.clear(content);
        model.addAttribute("title","方论选");
        model.addAttribute("content",content);
        String yuanwen=stringclear.clear3(bookEntry.getYuanwen());
        model.addAttribute("yuanwen",yuanwen);
        model.addAttribute("sidebar","/bookEntryS/fanglunxuan");
        model.addAttribute("link1","./"+(id-1));
        model.addAttribute("link2","./"+(id+1));
        return "contents";
    }
    @RequestMapping("/dianping/{id}")
    String dianping(Model model, @PathVariable int id) {
        BookEntryShl bookEntry = bookEntryService.getById(id);
        String content=bookEntry.getDianping();
        content=stringclear.clear(content);
        model.addAttribute("title","点评");
        model.addAttribute("content",content);
        String yuanwen=stringclear.clear3(bookEntry.getYuanwen());
        model.addAttribute("yuanwen",yuanwen);
        model.addAttribute("sidebar","/bookEntryS/dianping");
        model.addAttribute("link1","./"+(id-1));
        model.addAttribute("link2","./"+(id+1));
        return "contents";
    }
    @RequestMapping("/linchuangyingyong/{id}")
    String linchuangyingyong(Model model, @PathVariable int id) {
        BookEntryShl bookEntry = bookEntryService.getById(id);
        String content=bookEntry.getLinchuangyingyong();
        content=stringclear.clear(content);
        model.addAttribute("title","临床应用");
        model.addAttribute("content",content);
        String yuanwen=stringclear.clear3(bookEntry.getYuanwen());
        model.addAttribute("yuanwen",yuanwen);
        model.addAttribute("sidebar","/bookEntryS/linchuangyingyong");
        model.addAttribute("link1","./"+(id-1));
        model.addAttribute("link2","./"+(id+1));
        return "contents";
    }
    @RequestMapping("/anyu/{id}")
    String anyu(Model model, @PathVariable int id) {
        BookEntryShl bookEntry = bookEntryService.getById(id);
        String content=bookEntry.getAnyu();
        content=stringclear.clear(content);
        model.addAttribute("title","按语");
        model.addAttribute("content",content);
        String yuanwen=stringclear.clear3(bookEntry.getYuanwen());
        model.addAttribute("yuanwen",yuanwen);
        model.addAttribute("sidebar","/bookEntryS/anyu");
        model.addAttribute("link1","./"+(id-1));
        model.addAttribute("link2","./"+(id+1));
        return "contents";
    }
    @RequestMapping("/xiandaiyanjiu/{id}")
    String xiandaiyanjiu(Model model, @PathVariable int id) {
        BookEntryShl bookEntry = bookEntryService.getById(id);
        String content=bookEntry.getXiandaiyanjiu();
        content=stringclear.clear(content);
        model.addAttribute("title","现代研究");
        model.addAttribute("content",content);
        String yuanwen=stringclear.clear3(bookEntry.getYuanwen());
        model.addAttribute("yuanwen",yuanwen);
        model.addAttribute("sidebar","/bookEntryS/xiandaiyanjiu");
        model.addAttribute("link1","./"+(id-1));
        model.addAttribute("link2","./"+(id+1));
        return "contents";
    }
    @RequestMapping("/xiaojie/{id}")
    String xiaojie(Model model, @PathVariable int id) {
        BookEntryShl bookEntry = bookEntryService.getById(id);
        String content=bookEntry.getXiaojie();
        content=stringclear.clear(content);
        model.addAttribute("title","小结");
        model.addAttribute("content",content);
        String yuanwen=stringclear.clear3(bookEntry.getYuanwen());
        model.addAttribute("yuanwen",yuanwen);
        model.addAttribute("sidebar","/bookEntryS/xiaojie");
        model.addAttribute("link1","./"+(id-1));
        model.addAttribute("link2","./"+(id+1));
        return "contents";
    }

    @RequestMapping(value = "/graph/{id}")
    public String graph(@PathVariable Integer id, Model model){
        BookEntryShl entry=bookEntryService.getById(id);
        while(entry==null)entry=bookEntryService.getById(++id);
        String name0=entry.getYuanwen();
        model.addAttribute("eid",id);
        model.addAttribute("type","S");
        model.addAttribute("yuanwen",stringclear.clear3(name0));
        model.addAttribute("link1","./"+(id-1));
        model.addAttribute("link2","./"+(id+1));
        return "entryGraphS";
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
        int label_id=entry.getLabel_id();
        EntrySLabel entrySLabel=bookEntryService.findLabelByid(label_id);
        String maixiang=entrySLabel.getMaixiang();
        String zhengzhuang=entrySLabel.getZhengzhuang();
        String bingming=entrySLabel.getBingming();
        String bingji=entrySLabel.getBingji();
        String zhifa=entrySLabel.getZhifa();
        String name0=entry.getYuanwen();
        String name=stringclear.clear4(name0);

        String ids="s"+id;
        GNode gNode = new GNode(ids, name,stringclear.clear3(name0),"s",false);
        NodeList.add(gNode);
        if(maixiang!=null&&!maixiang.isEmpty()){
            String pids="mx"+"_"+ids;;
            GNode pNode = new GNode(pids, maixiang,"","mx",true);
            GEdge gLink1 = new GEdge("smx"+pids+ids, ids,pids);
            NodeList.add(pNode);
            EdgeList.add(gLink1);
        }
        if(zhengzhuang!=null&&!zhengzhuang.isEmpty()){
            String pids="zz"+"_"+ids;;
            GNode pNode = new GNode(pids, zhengzhuang,"","zz",true);
            GEdge gLink1 = new GEdge("szz"+pids+ids, ids,pids);
            NodeList.add(pNode);
            EdgeList.add(gLink1);
        }
        if(bingming!=null&&!bingming.isEmpty()){
            String pids="bm"+"_"+ids;;
            GNode pNode = new GNode(pids, bingming,"","bm",true);
            GEdge gLink1 = new GEdge("sbm"+pids+ids, ids,pids);
            NodeList.add(pNode);
            EdgeList.add(gLink1);
        }
        if(zhifa!=null&&!zhifa.isEmpty()){
            String[] ss=zhifa.split("；");
            int l=ss.length;
            for(int i=0;i<l;i++){
                String pids="zf"+"_"+ids+"_"+i;
                GNode pNode = new GNode(pids, zhifa,"","zf",true);
                GEdge gLink1 = new GEdge("szf"+pids+ids, ids,pids);
                NodeList.add(pNode);
                EdgeList.add(gLink1);
            }
        }
        if(bingji!=null&&!bingji.isEmpty()){
            String[] ss=bingji.split("；");
            int l=ss.length;
            for(int i=0;i<l;i++){
                String pids="bj"+"_"+ids+"_"+i;;
                GNode pNode = new GNode(pids, ss[i],"","bj",true);
                GEdge gLink1 = new GEdge("sbj"+pids+ids, ids,pids);
                NodeList.add(pNode);
                EdgeList.add(gLink1);
            }
        }
        String fangyaoss=entry.getFangyao_id();
        if(fangyaoss==null||fangyaoss.isEmpty()){
            map.put("nodes", NodeList);
            map.put("edges", EdgeList);
            return map;
        }
        String[] fangyaos=fangyaoss.split(";");
        for(String s:fangyaos){
            Prescription p=prescriptionService.getById(Integer.parseInt(s));
            String pids="p"+p.getId()+"_"+ids;
            String pname=p.getFangMing();
            String[] herbs=p.getHerbs().split(";");

            GNode pNode = new GNode(pids, pname,"","p",true);
            GEdge gLink1 = new GEdge("sp"+pids+ids, ids,pids);
            for(String herb:herbs){
                int hid=Integer.parseInt(herb);
                String hname=herbService.getNameById(hid);
                String hids="h"+hid+"_"+pids;
                GNode hNode = new GNode(hids, hname,"","h",false);
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

    @ResponseBody
    @RequestMapping(value = "/graph_ep",method = RequestMethod.GET)
    public HashMap<String, Object> findPrescription(String idName){
        int father_id=Integer.parseInt(idName.split("_")[1].substring(1));
        String eid=idName.split("_")[0];
        HashMap<String, Object> map = new HashMap<String, Object>();
        ArrayList<GNode> NodeList = new ArrayList<GNode>();
        ArrayList<GEdge> EdgeList = new ArrayList<GEdge>();

        List<Integer> pids=entryFangyaoService.findPidByEid(eid);
        if(pids.size()<=1)return map;
        for(Integer pid:pids){
            if(pid==father_id)continue;
            String name=prescriptionService.getNameById(pid);
            String nodeName="p"+pid+"_"+eid;
            GNode gnode=new GNode(nodeName,name,"","p1",true);
            GEdge gLink = new GEdge("ep"+eid+nodeName, idName,nodeName);
            NodeList.add(gnode);
            EdgeList.add(gLink);
        }
        map.put("nodes", NodeList);
        map.put("edges", EdgeList);
        return map;
    }
}