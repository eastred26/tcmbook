package com.tcm.tcmbook.pojo;

import lombok.Data;

import java.text.DecimalFormat;
import java.util.Date;

@Data
public class exam {
    private int id;
    private int uid;
    private String uName;
    private int pid;
    private String pname;
    private int score_all;
    private int score_get;
    private double rate;
    private Date submitTime;
    public String getTrueRateString(){
        if(score_all==0)return "0%";
        double trueRate=1.0*score_get/score_all;
        java.text.DecimalFormat   df   =new DecimalFormat("0.00%");
        return df.format(trueRate);
    }
}
