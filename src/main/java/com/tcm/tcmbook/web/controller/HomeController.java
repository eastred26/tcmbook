package com.tcm.tcmbook.web.controller;

import com.tcm.tcmbook.dao.TitleDao;
import com.tcm.tcmbook.pojo.BookEntryJgyl;
import com.tcm.tcmbook.pojo.BookEntryShl;
import com.tcm.tcmbook.pojo.Herb;
import com.tcm.tcmbook.pojo.Prescription;
import com.tcm.tcmbook.service.*;
import com.tcm.tcmbook.utils.stringclear;
import net.sourceforge.pinyin4j.PinyinHelper;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.FileInputStream;
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
    private HerbService herbService;
    @Autowired
    private TitleDao titleDao;
    @Autowired
    private SearchService searchService;

    @RequestMapping("/")
    String root() {
        return "redirect:/home";
    }
    @RequestMapping("/navigation")
    String navigation(Model model){
        Map<String, Map<Integer, String>> shlMap = new LinkedHashMap<>();
        Map<String, Map<Integer, String>> jgylMap = new LinkedHashMap<>();
        Map<String, Map<Integer, String>> preMap = new LinkedHashMap<>();
        Map<String, Map<Integer, String>> herbMap = new LinkedHashMap<>();

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
        for(int i=1;i<=26;i++){
            Map<Integer, String> preNameMap=new HashMap<>();
            List<Prescription> preList1=prescriptionService.findByTid(i);
            for(Prescription b:preList1){
                preNameMap.put(b.getId(),b.getFangMing());
            }
            char c=(char)('a'-1+i);
            String s=""+c;
            if(!preNameMap.isEmpty())preMap.put(s,preNameMap);
        }
        for(int i=1;i<=26;i++){
            Map<Integer, String> herbNameMap=new HashMap<>();
            List<Herb> herbList1=herbService.findByTid(i);
            for(Herb b:herbList1){
                herbNameMap.put(b.getId(),b.getName());
            }
            char c=(char)('a'-1+i);
            String s=""+c;
            if(!herbNameMap.isEmpty())herbMap.put(s,herbNameMap);
        }

        model.addAttribute("shlMap",shlMap);
        model.addAttribute("jgylMap",jgylMap);
        model.addAttribute("preMap",preMap);
        model.addAttribute("herbMap",herbMap);
        return "navigation";
    }
    @RequestMapping("/home")
    String home(Model model){
        return "home";
    }

    //用来测试java代码用的
    @RequestMapping("/solve")
    void solve(Model model){
        String excelPath = "D:\\test.xlsx";

        try {
            //String encoding = "GBK";
            File excel = new File(excelPath);
            if (excel.isFile() && excel.exists()) {   //判断文件是否存在

                String[] split = excel.getName().split("\\.");  //.是特殊字符，需要转义！！！！！
                Workbook wb;
                //根据文件后缀（xls/xlsx）进行判断
                if ( "xls".equals(split[1])){
                    FileInputStream fis = new FileInputStream(excel);   //文件流对象
                    wb = new HSSFWorkbook(fis);
                }else if ("xlsx".equals(split[1])){
                    wb = new XSSFWorkbook(excel);
                }else {
                    System.out.println("文件类型错误!");
                    return;
                }

                //开始解析
                Sheet sheet = wb.getSheetAt(0);     //读取sheet 0

                int firstRowIndex = sheet.getFirstRowNum()+1;   //第一行是列名，所以不读
                int lastRowIndex = sheet.getLastRowNum();
                System.out.println("firstRowIndex: "+firstRowIndex);
                System.out.println("lastRowIndex: "+lastRowIndex);

                for(int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++) {   //遍历行
                    System.out.println("rIndex: " + rIndex);
                    Row row = sheet.getRow(rIndex);
                    if (row != null) {
                        int firstCellIndex = row.getFirstCellNum();
                        int lastCellIndex = row.getLastCellNum();
                        for (int cIndex = firstCellIndex; cIndex < lastCellIndex; cIndex++) {   //遍历列
                            Cell cell = row.getCell(cIndex);
                            if (cell != null) {
                                System.out.println(cell.toString());
                            }
                        }
                    }
                }
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/search")
    String search(Model model,String kw,String type){
        List<Integer> ids=new LinkedList<>();
        Map<Integer,String> map=new HashMap<>();
        kw="%"+kw+"%";
        if(type.equals("shl")){
            ids=searchService.findEIDByS(kw);
            for(Integer num:ids){
                map.put(num,stringclear.clear3(bookEntryServiceS.getById(num).getYuanwen()));
            }
        }
        else if(type.equals("jgyl")){
            ids=searchService.findJIDByS(kw);
            for(Integer num:ids){
                map.put(num,stringclear.clear3(bookEntryServiceJ.getById(num).getYuanwen()));
            }
        }
        else if(type.equals("prescription")){
            ids=searchService.findPIDByS(kw);
            for(Integer num:ids){
                map.put(num,prescriptionService.getNameById(num));
            }
        }
        else if(type.equals("herb")){
            ids=searchService.findHIDByS(kw);
            for(Integer num:ids){
                map.put(num,herbService.getNameById(num));
            }
        }

        model.addAttribute("num",ids.size());
        model.addAttribute("map",map);
        model.addAttribute("type0",type);
        return "/search/result";
    }
}
