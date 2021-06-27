package com.tcm.tcmbook.dao;

import com.tcm.tcmbook.pojo.question;
import org.apache.ibatis.annotations.Insert;
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

    @Select("SELECT max(id) FROM questions_a")
    Integer findMaxIdsa();
    @Select("SELECT max(id) FROM questions_b")
    Integer findMaxIdsb();
    @Select("SELECT max(id) FROM questions_c")
    Integer findMaxIdsc();
    @Select("SELECT max(id) FROM questions_d")
    Integer findMaxIdsd();
    @Select("SELECT max(id) FROM questionj_a")
    Integer findMaxIdja();
    @Select("SELECT max(id) FROM questionj_b")
    Integer findMaxIdjb();
    @Select("SELECT max(id) FROM questionj_c")
    Integer findMaxIdjc();
    @Select("SELECT max(id) FROM questionj_d")
    Integer findMaxIdjd();


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

    @Insert("INSERT INTO questions_a VALUES(${id},#{type},#{name},#{answerA},#{answerB},#{answerC},#{answerD},#{answerE},#{ans},#{source},${eid},${num_right},${num_false})")
    void AddQuestionsa(Integer id, String type, String name, String answerA, String answerB, String answerC, String answerD, String answerE, String ans,String source,Integer eid,Integer num_right,Integer num_false);
    @Insert("INSERT INTO questions_b VALUES(${id},#{type},#{name},#{answerA},#{answerB},#{answerC},#{answerD},#{answerE},#{ans},#{source},${eid},${num_right},${num_false})")
    void AddQuestionsb(Integer id, String type, String name, String answerA, String answerB, String answerC, String answerD, String answerE, String ans,String source,Integer eid,Integer num_right,Integer num_false);
    @Insert("INSERT INTO questions_c VALUES(${id},#{type},#{name},#{ans},#{source},${eid},${num_right},${num_false})")
    void AddQuestionsc(Integer id, String type, String name, String ans,String source,Integer eid,Integer num_right,Integer num_false);
    @Insert("INSERT INTO questions_d VALUES(${id},#{type},#{name},#{ans},#{source},${eid},${num_right},${num_false})")
    void AddQuestionsd(Integer id, String type, String name, String ans,String source,Integer eid,Integer num_right,Integer num_false);

    @Insert("INSERT INTO questionj_a VALUES(${id},#{type},#{name},#{answerA},#{answerB},#{answerC},#{answerD},#{answerE},#{ans},#{source},${eid},${num_right},${num_false})")
    void AddQuestionja(Integer id, String type, String name, String answerA, String answerB, String answerC, String answerD, String answerE, String ans,String source,Integer eid,Integer num_right,Integer num_false);
    @Insert("INSERT INTO questionj_b VALUES(${id},#{type},#{name},#{answerA},#{answerB},#{answerC},#{answerD},#{answerE},#{ans},#{source},${eid},${num_right},${num_false})")
    void AddQuestionjb(Integer id, String type, String name, String answerA, String answerB, String answerC, String answerD, String answerE, String ans,String source,Integer eid,Integer num_right,Integer num_false);
    @Insert("INSERT INTO questionj_c VALUES(${id},#{type},#{name},#{ans},#{source},${eid},${num_right},${num_false})")
    void AddQuestionjc(Integer id, String type, String name, String ans,String source,Integer eid,Integer num_right,Integer num_false);
    @Insert("INSERT INTO questionj_d VALUES(${id},#{type},#{name},#{ans},#{source},${eid},${num_right},${num_false})")
    void AddQuestionjd(Integer id, String type, String name, String ans,String source,Integer eid,Integer num_right,Integer num_false);

    @Insert("INSERT INTO user_question VALUES(${id},${uid},${qid})")
    void AddUserQuestion(Integer id, Integer uid,Integer qid);

    @Select("SELECT qid FROM user_question WHERE uid=${uid}")
    List<Integer> FindUserQuestionByUid(Integer uid);

    @Select("SELECT uid FROM user_question WHERE qid=${qid}")
    Integer FindUserQuestionByQid(Integer qid);

    @Select("SELECT max(id) FROM user_question")
    Integer FindUQMaxId();
}