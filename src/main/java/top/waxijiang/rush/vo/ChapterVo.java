package top.waxijiang.rush.vo;

import top.waxijiang.rush.entity.Chapter;
import top.waxijiang.rush.entity.Section;

import java.io.Serializable;
import java.util.List;

/**
 * @author waxijiang
 */
public class ChapterVo implements Serializable {
    private Chapter chapter;
    List<Section> sections;

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }
}
