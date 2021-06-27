package com.tcm.tcmbook.pojo;

import lombok.Data;

@Data
public class GNode {
    private String id;
    private String label;
    private String label2;
    private String group;
    private boolean isFold;
    public GNode(String id,String label,String label2,String group,boolean isFold){
        this.id=id;
        this.label=label;
        this.label2=label2;
        this.group=group;
        this.isFold=isFold;
    }
}
