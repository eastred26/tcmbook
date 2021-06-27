package com.tcm.tcmbook.service;

import com.tcm.tcmbook.pojo.record;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SearchService {
    List<Integer> findEIDByS(String kw);
    List<Integer> findJIDByS(String kw);
    List<Integer> findPIDByS(String kw);
    List<Integer> findHIDByS(String kw);
}
