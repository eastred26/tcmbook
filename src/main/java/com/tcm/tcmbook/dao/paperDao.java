package com.tcm.tcmbook.dao;

import com.tcm.tcmbook.pojo.paper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public interface paperDao {
    @Select("SELECT * FROM paper WHERE id=${id}")
    paper findById(Integer id);
    @Select("SELECT max(id) FROM paper")
    Integer findMaxId();
    @Select("SELECT count(*) FROM paper")
    Integer findNum();
    @Select("SELECT * FROM paper")
    List<paper> findAll();
    @Select("SELECT * FROM paper WHERE publish=1")
    List<paper> findPublish();
    @Select("SELECT * FROM paper WHERE publish=0 AND creatorId=${uid}")
    List<paper> findNotpublishByUid(Integer uid);
    @Update("UPDATE paper SET paperName=#{paperName} , paperIntro=#{paperIntro},type=#{type} WHERE id=${pid}")
    void EditPaper(Integer pid, String paperName, String paperIntro, String type);
    @Update("UPDATE paper SET numa=${numa}, numb=${numb} ,numc=${numc},numd=${numd},score_all=${numa}+${numb}+${numc}+${numd} WHERE id=${pid}")
    void EditPaperNum(Integer pid, Integer numa, Integer numb, Integer numc, Integer numd);
    @Update("UPDATE paper SET publish=1 WHERE id=${pid}")
    void PublishPaper(Integer pid);
    @Insert("INSERT INTO paper VALUES (${id},#{paperName},#{paperIntro},#{type},${creatorId},${numa},${numb},${numc},${numd},${score_all},#{createTime},#{createName},${publish})")
    void AddPaper(Integer id, String paperName, String paperIntro, String type, Integer creatorId, Integer numa, Integer numb, Integer numc, Integer numd, Integer score_all, Date createTime, String createName, Integer publish);
    @Delete("DELETE FROM paper WHERE id=${pid}")
    void DeletePaper(Integer pid);
}