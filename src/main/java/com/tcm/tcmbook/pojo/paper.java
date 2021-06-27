package com.tcm.tcmbook.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class paper {
    private int id;
    private String paperName;
    private String paperIntro;
    private String type;//s j k
    private int creatorId;
    private int numa;
    private int numb;
    private int numc;
    private int numd;
    private int score_all;
    private Date createTime;
    private String createName;
    private int publish;
    private String score_aveString;
}
