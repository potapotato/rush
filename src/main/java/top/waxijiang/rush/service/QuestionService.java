package top.waxijiang.rush.service;

import top.waxijiang.rush.entity.Question;

import java.util.List;

/**
 * @author waxijiang
 */
public interface QuestionService {
    /**
     * 查询所有question
     * @return Question List
     */
    List<Question> findAllQuestion();

    /**
     * 创建题目
     */
    boolean addQuestion(String courseId, String questionText, String questionTrueImageUrl, String answerText, String answerTrueImageUrl, String score, String typeId, String userId);

    /**
     * 删除题目
     * @param id 题目ｉｄ
     */
    boolean deleteById(String id);
}
