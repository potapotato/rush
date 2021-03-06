package top.waxijiang.rush.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.waxijiang.rush.dao.QuestionDao;
import top.waxijiang.rush.entity.Question;
import top.waxijiang.rush.service.QuestionService;

import java.util.List;

/**
 * @author waxijiang
 */
@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    QuestionDao questionDao;


    @Override
    public List<Question> findAllQuestion() {
        return questionDao.selectAll();
    }

    @Override
    public boolean addQuestion(String courseId, String questionText, String questionTrueImageUrl, String answerText, String answerTrueImageUrl, String score, String typeId, String userId) {
        Question question = new Question();
        question.setCourseId(Integer.valueOf(courseId));
        question.setQuestionUrl(questionTrueImageUrl);
        question.setQuestionText(questionText);
        question.setAnswerText(answerText);
        question.setAnswerUrl(answerTrueImageUrl);
        question.setScore(Integer.valueOf(score));
        question.setQuestionTypeId(Integer.valueOf(typeId));
        question.setSectionId(1);
        question.setCreateUserId(Integer.valueOf(userId));
        return questionDao.insertSelective(question) > 0;
    }

    @Override
    public boolean deleteById(String id) {
        return questionDao.deleteByPrimaryKey(Integer.valueOf(id)) > 0;
    }

    @Override
    public Question findQuestionById(String id) {
        return questionDao.selectByPrimaryKey(Integer.valueOf(id));
    }

    @Override
    public boolean updateQuestion(String id, String courseId, String questionText, String questionTrueImageUrl, String answerText, String answerTrueImageUrl, String score, String typeId, String userId, String enabled) {
        Question question = new Question();
        question.setId(Integer.valueOf(id));
        question.setCourseId(Integer.valueOf(courseId));
        question.setQuestionText(questionText);
        question.setQuestionUrl(questionTrueImageUrl);
        question.setAnswerText(answerText);
        question.setAnswerUrl(answerTrueImageUrl);
        question.setScore(Integer.valueOf(score));
        question.setQuestionTypeId(Integer.valueOf(typeId));
        question.setCreateUserId(Integer.valueOf(userId));
        question.setEnabled(Boolean.valueOf(enabled));
        return questionDao.updateByPrimaryKeySelective(question) > 0;
    }
}
