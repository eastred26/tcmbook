package com.tcm.tcmbook.dao;

import com.tcm.tcmbook.pojo.question;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface questionDao {
    @Select("SELECT * FROM questions_a")
    List<question> findAllsa();
    @Select("SELECT * FROM questions_b")
    List<question> findAllsb();
    @Select("SELECT * FROM questions_c")
    List<question> findAllsc();
    @Select("SELECT * FROM questions_d")
    List<question> findAllsd();
    @Select("SELECT * FROM questionj_a")
    List<question> findAllja();
    @Select("SELECT * FROM questionj_b")
    List<question> findAlljb();
    @Select("SELECT * FROM questionj_c")
    List<question> findAlljc();
    @Select("SELECT * FROM questionj_d")
    List<question> findAlljd();

    @Select("SELECT * FROM questions_a WHERE id=${id}")
    question findByIdsa(Integer id);
    @Select("SELECT * FROM questions_b WHERE id=${id}")
    question findByIdsb(Integer id);
    @Select("SELECT * FROM questions_c WHERE id=${id}")
    question findByIdsc(Integer id);
    @Select("SELECT * FROM questions_d WHERE id=${id}")
    question findByIdsd(Integer id);
    @Select("SELECT * FROM questionj_a WHERE id=${id}")
    question findByIdja(Integer id);
    @Select("SELECT * FROM questionj_b WHERE id=${id}")
    question findByIdjb(Integer id);
    @Select("SELECT * FROM questionj_c WHERE id=${id}")
    question findByIdjc(Integer id);
    @Select("SELECT * FROM questionj_d WHERE id=${id}")
    question findByIdjd(Integer id);


    @Update("UPDATE questions_a set num_right=num_right+1 WHERE id=${id}")
    void AddRightsa(Integer id);
    @Update("UPDATE questions_b set num_right=num_right+1 WHERE id=${id}")
    void AddRightsb(Integer id);
    @Update("UPDATE questions_c set num_right=num_right+1 WHERE id=${id}")
    void AddRightsc(Integer id);
    @Update("UPDATE questions_d set num_right=num_right+1 WHERE id=${id}")
    void AddRightsd(Integer id);
    @Update("UPDATE questionj_a set num_right=num_right+1 WHERE id=${id}")
    void AddRightja(Integer id);
    @Update("UPDATE questionj_b set num_right=num_right+1 WHERE id=${id}")
    void AddRightjb(Integer id);
    @Update("UPDATE questionj_c set num_right=num_right+1 WHERE id=${id}")
    void AddRightjc(Integer id);
    @Update("UPDATE questionj_d set num_right=num_right+1 WHERE id=${id}")
    void AddRightjd(Integer id);

    @Update("UPDATE questions_a set num_false=num_false+1 WHERE id=${id}")
    void AddFalsesa(Integer id);
    @Update("UPDATE questions_b set num_false=num_false+1 WHERE id=${id}")
    void AddFalsesb(Integer id);
    @Update("UPDATE questions_c set num_false=num_false+1 WHERE id=${id}")
    void AddFalsesc(Integer id);
    @Update("UPDATE questions_d set num_false=num_false+1 WHERE id=${id}")
    void AddFalsesd(Integer id);
    @Update("UPDATE questionj_a set num_false=num_false+1 WHERE id=${id}")
    void AddFalseja(Integer id);
    @Update("UPDATE questionj_b set num_false=num_false+1 WHERE id=${id}")
    void AddFalsejb(Integer id);
    @Update("UPDATE questionj_c set num_false=num_false+1 WHERE id=${id}")
    void AddFalsejc(Integer id);
    @Update("UPDATE questionj_d set num_false=num_false+1 WHERE id=${id}")
    void AddFalsejd(Integer id);

    @Select("SELECT * FROM questions_a WHERE num_false!=0  ORDER BY num_false DESC")
    List<question> findAllOrderByFalseDescsa();
    @Select("SELECT * FROM questions_b WHERE num_false!=0  ORDER BY num_false DESC")
    List<question> findAllOrderByFalseDescsb();
    @Select("SELECT * FROM questions_c WHERE num_false!=0  ORDER BY num_false DESC")
    List<question> findAllOrderByFalseDescsc();
    @Select("SELECT * FROM questions_d WHERE num_false!=0  ORDER BY num_false DESC")
    List<question> findAllOrderByFalseDescsd();
    @Select("SELECT * FROM questionj_a WHERE num_false!=0  ORDER BY num_false DESC")
    List<question> findAllOrderByFalseDescja();
    @Select("SELECT * FROM questionj_b WHERE num_false!=0  ORDER BY num_false DESC")
    List<question> findAllOrderByFalseDescjb();
    @Select("SELECT * FROM questionj_c WHERE num_false!=0  ORDER BY num_false DESC")
    List<question> findAllOrderByFalseDescjc();
    @Select("SELECT * FROM questionj_d WHERE num_false!=0  ORDER BY num_false DESC")
    List<question> findAllOrderByFalseDescjd();
}