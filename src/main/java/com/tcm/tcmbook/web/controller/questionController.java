package com.tcm.tcmbook.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tcm.tcmbook.pojo.question;
import com.tcm.tcmbook.pojo.user;
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
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;

@Controller
@RequestMapping("/question")
public class questionController {
    @Autowired
    private com.tcm.tcmbook.service.userService userService;
    @Autowired
    private com.tcm.tcmbook.service.paperService paperService;
    @Autowired
    private com.tcm.tcmbook.service.recordService recordService;
    @Autowired
    private com.tcm.tcmbook.service.questionService questionService;
    @Autowired
    private com.tcm.tcmbook.dao.questionDao questionDao;

    @RequestMapping("/toAddQuestion")
    public String toAddQuestion(){
        return "question/addQuestion";
    }

    @RequestMapping("/AddQuestion")
    public String AddQuestion(File file, HttpServletRequest request) throws IOException {
        String path=file.getAbsolutePath();
        user user = (com.tcm.tcmbook.pojo.user)request.getSession().getAttribute("loger");
        int uid=user.getId();
        BufferedReader in = new BufferedReader(new FileReader(path));
        try {
            //String encoding = "GBK";
            File excel = new File(path);
            if (excel.isFile() && excel.exists()) {   //判断文件是否存在

                String[] split = excel.getName().split("\\.");  //.是特殊字符，需要转义！！！！！
                Workbook wb = null;
                //根据文件后缀（xls/xlsx）进行判断
                if ( "xls".equals(split[1])){
                    FileInputStream fis = new FileInputStream(excel);   //文件流对象
                    wb = new HSSFWorkbook(fis);
                }else if ("xlsx".equals(split[1])){
                    wb = new XSSFWorkbook(excel);
                }else {
                    System.out.println("文件类型错误!");
                }

                //开始解析
                Sheet sheet = wb.getSheetAt(0);     //读取sheet 0

                int firstRowIndex = sheet.getFirstRowNum();
                int lastRowIndex = sheet.getLastRowNum();
                System.out.println("firstRowIndex: "+firstRowIndex);
                System.out.println("lastRowIndex: "+lastRowIndex);
                String type;

                for(int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++) {   //遍历行
                    System.out.println("rIndex: " + rIndex);
                    Row row = sheet.getRow(rIndex);
                    if (row != null) {
                        Cell cell = row.getCell(0);
                        type=cell.toString();
                        if(type.equals("ja")){
                            int id=questionDao.findMaxIdja()+1;
                            String name=row.getCell(1).toString();
                            String a=row.getCell(2).toString();
                            String b=row.getCell(3).toString();
                            String c=row.getCell(4).toString();
                            String d=row.getCell(5).toString();
                            String e=row.getCell(6).toString();
                            String ans=row.getCell(7).toString();
                            String source=row.getCell(8).toString();
                            questionDao.AddQuestionja(id,type,name,a,b,c,d,e,ans,source,0,0,0);
                            questionService.AddUserQuestion(uid,id);
                        }
                        else if(type.equals("jb")){
                            int id=questionDao.findMaxIdjb()+1;
                            String name=row.getCell(1).toString();
                            String a=row.getCell(2).toString();
                            String b=row.getCell(3).toString();
                            String c=row.getCell(4).toString();
                            String d=row.getCell(5).toString();
                            String e=row.getCell(6).toString();
                            String ans=row.getCell(7).toString();
                            String source=row.getCell(8).toString();
                            questionDao.AddQuestionjb(id,type,name,a,b,c,d,e,ans,source,0,0,0);
                            questionService.AddUserQuestion(uid,id);
                        }
                        else if(type.equals("sa")){
                            int id=questionDao.findMaxIdsa()+1;
                            String name=row.getCell(1).toString();
                            String a=row.getCell(2).toString();
                            String b=row.getCell(3).toString();
                            String c=row.getCell(4).toString();
                            String d=row.getCell(5).toString();
                            String e=row.getCell(6).toString();
                            String ans=row.getCell(7).toString();
                            String source=row.getCell(8).toString();
                            questionDao.AddQuestionsa(id,type,name,a,b,c,d,e,ans,source,0,0,0);
                            questionService.AddUserQuestion(uid,id);
                        }
                        else if(type.equals("sb")){
                            int id=questionDao.findMaxIdsb()+1;
                            String name=row.getCell(1).toString();
                            String a=row.getCell(2).toString();
                            String b=row.getCell(3).toString();
                            String c=row.getCell(4).toString();
                            String d=row.getCell(5).toString();
                            String e=row.getCell(6).toString();
                            String ans=row.getCell(7).toString();
                            String source=row.getCell(8).toString();
                            questionDao.AddQuestionsb(id,type,name,a,b,c,d,e,ans,source,0,0,0);
                            questionService.AddUserQuestion(uid,id);
                        }
                        else if(type.equals("jc")){
                            int id=questionDao.findMaxIdjc()+1;
                            String name=row.getCell(1).toString();
                            String ans=row.getCell(2).toString();
                            String source=row.getCell(3).toString();
                            questionDao.AddQuestionjc(id,type,name,ans,source,0,0,0);
                            questionService.AddUserQuestion(uid,id);
                        }
                        else if(type.equals("jd")){
                            int id=questionDao.findMaxIdjd()+1;
                            String name=row.getCell(1).toString();
                            String ans=row.getCell(2).toString();
                            String source=row.getCell(3).toString();
                            questionDao.AddQuestionjd(id,type,name,ans,source,0,0,0);
                            questionService.AddUserQuestion(uid,id);
                        }
                        else if(type.equals("sc")){
                            int id=questionDao.findMaxIdsc()+1;
                            String name=row.getCell(1).toString();
                            String ans=row.getCell(2).toString();
                            String source=row.getCell(3).toString();
                            questionDao.AddQuestionsc(id,type,name,ans,source,0,0,0);
                            questionService.AddUserQuestion(uid,id);
                        }
                        else if(type.equals("sd")){
                            int id=questionDao.findMaxIdsd()+1;
                            String name=row.getCell(1).toString();
                            String ans=row.getCell(2).toString();
                            String source=row.getCell(3).toString();
                            questionDao.AddQuestionsd(id,type,name,ans,source,0,0,0);
                            questionService.AddUserQuestion(uid,id);
                        }
                    }
                }
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return "redirect:/question/toAddQuestion";
    }

    @RequestMapping("/manageQuestion")
    public String getAllQuestion(String book, String  type,@RequestParam(defaultValue = "1") int pageNum,
                                 @RequestParam(defaultValue = "8") int pageSize,
                                 Model model){
        //若是第一次查询则用上次提交的表单中的类型、课程，若是第二次查询则延用上次类型
        String questionTypeBef=type;
        String bookBef=book;
        String questionTypesRes="";
        String bookRes="a";
        String sourceRes="a";
        String type0;
        PageHelper.startPage(pageNum,pageSize);//这行是重点，表示从pageNum页开始，每页pageSize条数据
        if(bookBef==null){
            bookRes="s";
        }
        else{
            bookRes=bookBef;
        }
        if(questionTypeBef==null||questionTypeBef.equals("a")){
            questionTypesRes="a";
            type0=bookRes+questionTypesRes;
            List<question> questions=questionService.findByBookType(type0);
            for(question q:questions){
                Integer num=questionDao.FindUserQuestionByQid(q.getId());
                if(num!=null){
                    q.setUid(""+num);
                }
                else {
                    q.setUid("-");
                }
            }
            PageInfo<question> pageInfo = new PageInfo<question>(questions);
            model.addAttribute("pageInfo",pageInfo);
        }
        else{
            questionTypesRes=questionTypeBef;
            type0=bookRes+questionTypesRes;
            List<question> questions=questionService.findByBookType(type0);
            for(question q:questions){
                Integer num=questionDao.FindUserQuestionByQid(q.getId());
                if(num!=null){
                    q.setUid(""+num);
                }
                else q.setUid("-");
            }
            PageInfo<question> pageInfo = new PageInfo<question>(questions);
            model.addAttribute("pageInfo",pageInfo);
        }

        model.addAttribute("questionTypesRes",questionTypesRes);
        model.addAttribute("bookRes",bookRes);
        return "question/manageQuestion";
    }

}

