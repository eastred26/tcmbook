package com.tcm.tcmbook.pojo;

import lombok.Builder;
import lombok.Data;

@Data
public class Prescription {
    private int id;
    private String FangMing;
    private String herbs;
    private String weights;
    private int titleId;
    private String texts;
}