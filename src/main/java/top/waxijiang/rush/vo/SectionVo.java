package top.waxijiang.rush.vo;

import top.waxijiang.rush.entity.Question;
import top.waxijiang.rush.entity.Section;

import java.io.Serializable;
import java.util.List;

/**
 * @author waxijiang
 */
public class SectionVo implements Serializable {
    private Section section;
    private List<Question> questions;

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
