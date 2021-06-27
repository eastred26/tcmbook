package com.tcm.tcmbook.dao;

import com.tcm.tcmbook.pojo.record;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface recordDao {
    @Select("SELECT * FROM record WHERE id=${id}")
    record findById(Integer id);
    @Select("SELECT * FROM record")
    List<record> findAll();
    @Select("SELECT count(*) FROM record")
    Integer findNum();
    @Select("SELECT max(id) FROM record")
    Integer findMaxId();
    @Select("SELECT * FROM record WHERE isRight=0")
    List<record> findFalse();
    @Select("SELECT * FROM record WHERE uid=${uid} and isRight=0")
    List<record> findByUid(Integer uid);
    @Select("SELECT count(*) FROM record WHERE uid=${uid}")
    Integer findNumByUid(Integer uid);
    @Select("SELECT count(*) FROM record WHERE uid=${uid} and isRight=0")
    Integer findNumFalseByUid(Integer uid);
    @Select("SELECT * FROM record WHERE uid=${uid} and qid=#{qid}")
    List<record> findByUidQid(Integer uid,String qid);
    @Select("SELECT * FROM record WHERE uid=${uid} AND qtype=#{type} and isRight=0")
    List<record> findByUidType(Integer uid, String type);
    @Select("SELECT * FROM record WHERE uid=${uid} and eid=${eid}")
    List<record> findByUidEid(Integer uid, Integer eid);
    @Insert("INSERT INTO record VALUES(${id},${uid},${pid},${eid},#{qid},#{qtype},#{yourans},#{rightans},${isRight})")
    void AddRecord(Integer id, Integer uid, Integer pid, Integer eid, String qid, String qtype, String yourans, String rightans, Integer isRight);
}