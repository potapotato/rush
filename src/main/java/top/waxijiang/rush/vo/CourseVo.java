package top.waxijiang.rush.vo;

import top.waxijiang.rush.entity.Chapter;
import top.waxijiang.rush.entity.Course;

import java.io.Serializable;
import java.util.List;

/**
 * @author waxijiang
 */
public class CourseVo implements Serializable {
    private Course course;
    private List<ChapterVo> chapters;
    private Integer questionNum;

    public Integer getQuestionNum() {
        return questionNum;
    }

    public void setQuestionNum(Integer questionNum) {
        this.questionNum = questionNum;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<ChapterVo> getChapters() {
        return chapters;
    }

    public void setChapters(List<ChapterVo> chapters) {
        this.chapters = chapters;
    }
}
