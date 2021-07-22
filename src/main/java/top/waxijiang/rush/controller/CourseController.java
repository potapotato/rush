package top.waxijiang.rush.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import top.waxijiang.rush.entity.Chapter;
import top.waxijiang.rush.entity.Question;
import top.waxijiang.rush.entity.Section;
import top.waxijiang.rush.service.ChapterService;
import top.waxijiang.rush.service.CourseService;
import top.waxijiang.rush.service.QuestionService;
import top.waxijiang.rush.service.SectionService;
import top.waxijiang.rush.vo.ChapterVo;
import top.waxijiang.rush.vo.CourseVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author waxijiang
 */
@Controller
@RequestMapping("/course")
public class CourseController {
    final
    CourseService courseService;

    final
    ChapterService chapterService;

    final
    SectionService sectionService;

    final
    QuestionService questionService;

    public CourseController(CourseService courseService, ChapterService chapterService, SectionService sectionService, QuestionService questionService) {
        this.courseService = courseService;
        this.chapterService = chapterService;
        this.sectionService = sectionService;
        this.questionService = questionService;
    }

    @RequestMapping("/{id}")
    public String toCourseDetail(@PathVariable("id") String cid, Model model) {
        CourseVo courseVo = new CourseVo();
        courseVo.setCourse(courseService.getById(cid));
        List<ChapterVo> chapterVos = new ArrayList<>();
        List<Chapter> chapters = chapterService.list(new QueryWrapper<Chapter>().eq("course_id", cid));
        for (Chapter c : chapters) {
            ChapterVo chapterVo = new ChapterVo();
            chapterVo.setChapter(c);
            chapterVo.setSections(sectionService.list(new QueryWrapper<Section>().eq("chapter_id", c.getId()).eq("course_id", cid)));
            chapterVos.add(chapterVo);
        }
        courseVo.setChapters(chapterVos);
        courseVo.setQuestionNum(questionService.count(new QueryWrapper<Question>().eq("course_id", cid)));
        model.addAttribute("courseWrapper", courseVo);
        return "course_detail";
    }
}
