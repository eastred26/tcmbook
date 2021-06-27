package com.tcm.tcmbook.web.controller;


import com.tcm.tcmbook.pojo.*;
import com.tcm.tcmbook.service.*;
import com.tcm.tcmbook.utils.stringclear;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class PrescriptionController {
    @Autowired
    private PrescriptionService prescriptionService;
    @Autowired
    private HerbService herbService;
    @Autowired
    private EntryFangyaoService entryFangyaoService;
    @Autowired
    private BookEntryJgylService bookEntryJgylService;
    @Autowired
    private BookEntryShlService bookEntryShlService;

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
    @ResponseBody
    @RequestMapping(value = "/graph_pe",method = RequestMethod.GET)
    public HashMap<String, Object> findEntry(String idName){
        String father_id=idName.split("_")[1];
        int pid=Integer.parseInt(idName.split("_")[0].substring(1));
        HashMap<String, Object> map = new HashMap<String, Object>();
        ArrayList<GNode> NodeList = new ArrayList<GNode>();
        ArrayList<GEdge> EdgeList = new ArrayList<GEdge>();

        List<String> eids=entryFangyaoService.findEidByPid(pid);
        if(eids.size()<=1)return map;
        for(String eid:eids){
            if(eid.equals(father_id))continue;
            int num=Integer.parseInt(eid.substring(1));
            String name0="";
            String name="";
            char c=eid.charAt(0);
            if(c=='s'){
                BookEntryShl b =bookEntryShlService.getById(num);
                name0=b.getYuanwen();
                name=stringclear.clear4(name0);
            }
            else{
                BookEntryJgyl b= bookEntryJgylService.getById(num);
                name0=b.getYuanwen();
                name=stringclear.clear4(name0);
            }
            String nodeName=eid+"_p"+pid;
            GNode gnode=new GNode(nodeName,name,stringclear.clear3(name0),""+c+"1",true);
            GEdge gLink = new GEdge("pe"+"p"+pid+nodeName, idName,nodeName);
            NodeList.add(gnode);
            EdgeList.add(gLink);
        }
        map.put("nodes", NodeList);
        map.put("edges", EdgeList);
        return map;
    }
}