package top.waxijiang.rush.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.waxijiang.rush.entity.Question;
import top.waxijiang.rush.entity.QuestionType;

import java.util.List;

/**
 * @author waxijiang
 */
@Repository
@Mapper
public interface QuestionTypeDao extends BaseMapper<QuestionType> {
}