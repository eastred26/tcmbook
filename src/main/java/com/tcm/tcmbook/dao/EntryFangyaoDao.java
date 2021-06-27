package com.tcm.tcmbook.dao;

import com.tcm.tcmbook.pojo.EntryFangyao;
import com.tcm.tcmbook.pojo.exam;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EntryFangyaoDao {
    @Select("SELECT * FROM entry_fangyaos WHERE eid=#{eid} AND pid=${pid}")
    EntryFangyao findByEidPidS(Integer eid,Integer pid);
    @Select("SELECT * FROM entry_fangyaoj WHERE eid=#{eid} AND pid=${pid}")
    EntryFangyao findByEidPidJ(Integer eid,Integer pid);
    @Select("SELECT eid FROM entry_pre WHERE pid=${pid}")
    List<String> findEidByPid(Integer pid);
    @Select("SELECT pid FROM entry_pre WHERE eid=#{eid}")
    List<Integer> findPidByEid(String eid);
}
