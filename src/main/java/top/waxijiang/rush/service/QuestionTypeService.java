package top.waxijiang.rush.service;

import top.waxijiang.rush.entity.QuestionType;

import java.util.List;

/**
 * @author waxijiang
 */
public interface QuestionTypeService {
    /**
     * 查询所有类别
     * @return
     */
    List<QuestionType> findAllType();
}
