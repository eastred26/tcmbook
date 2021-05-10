package com.tcm.tcmbook;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.tcm.tcmbook.dao")
public class TcmbookApplication {

    public static void main(String[] args) {
        SpringApplication.run(TcmbookApplication.class, args);
    }

}
