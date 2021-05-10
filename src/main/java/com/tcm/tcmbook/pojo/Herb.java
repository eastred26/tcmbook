package com.tcm.tcmbook.pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Herb {
    private int id;
    private String name;
}
