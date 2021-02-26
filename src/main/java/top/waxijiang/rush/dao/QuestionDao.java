package top.waxijiang.rush.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.waxijiang.rush.entity.Question;

import java.util.List;

@Repository
@Mapper
public interface QuestionDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Question record);

    int insertSelective(Question record);

    Question selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Question record);

    int updateByPrimaryKey(Question record);

    List<Question> selectAll();
}