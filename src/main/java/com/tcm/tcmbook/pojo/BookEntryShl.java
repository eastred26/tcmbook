package com.tcm.tcmbook.pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookEntryShl {
    private int id;
    private String title;
    private String yuanwen;
    private String cijie;
    private String tiyao;
    private String shiyi;
    private String xuanzhu;
    private String pingshu;
    private String zhifa;
    private String fangyao;
    private String fangyao_id;
    private String fanglunxuan;
    private String dianping;
    private String linchuangyingyong;
    private String anyu;
    private String xiandaiyanjiu;
    private String xiaojie;
}