package top.waxijiang.rush.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.waxijiang.rush.entity.Chapter;

/**
 * @author waxijiang
 */
@Repository
@Mapper
public interface ChapterDao extends BaseMapper<Chapter> {
}