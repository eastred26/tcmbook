package com.tcm.tcmbook.pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Prescription {
    private int id;
    private String FangMing;
    private String herbs;
    private String weights;
    private String texts;
}