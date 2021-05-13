package com.tcm.tcmbook.pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookEntryJgyl {
    private int id;
    private String yuanwen;
    private String ciyuzhujie;
    private String jingyichanshi;
    private String fangyaopingxi;
    private String fangyao_id;
    private String wenxianxuanlu;
    private String linchuangyingyong;
    private String xiandaiyanjiu;
}