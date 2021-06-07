package com.tcm.tcmbook.pojo;

import lombok.Data;

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
}
