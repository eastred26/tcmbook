package com.tcm.tcmbook.pojo;

import lombok.Data;

@Data
public class question {
    private int id;
    private String type;
    private String questionName;
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;
    private String answerE;
    private String answer;
    private String book;
    private int num_right;//做对的记录数
    private int num_false;

    public static int numsa=6550;
    public static int numsb=776;
    public static int numsc=254;
    public static int numsd=958;

    public static int numja=7105;
    public static int numjb=378;
    public static int numjc=211;
    public static int numjd=1216;
    public String getAllName(){
        if(type.charAt(1)=='a'||type.charAt(1)=='b'){
            String res=questionName+" A."+answerA+" B."+answerB+" C."+answerC+" D."+answerD;
            if(answerE!=null)return res+" E."+answerE;
            return res;
        }
        return questionName;
    }
}
