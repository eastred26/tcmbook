package com.tcm.tcmbook.pojo;

import lombok.Data;

@Data
public class record {
    private int id;
    private int uid;
    private int eid;
    private int pid;
    private String qid;
    private String qtype;
    private String yourAnswer;
    private String rightAnswer;
    private int isRight;
}
