package com.tcm.tcmbook.pojo;

import lombok.Builder;
import lombok.Data;

@Data
public class Herb {
    private int id;
    private int titleId;
    private String name;
    private String texts;
}
