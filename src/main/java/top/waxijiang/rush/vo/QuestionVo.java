package top.waxijiang.rush.vo;

import top.waxijiang.rush.entity.Question;

/**
 * @author waxijiang
 */
public class QuestionVo {
    private String courseName;
    private Question question;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
