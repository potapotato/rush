package top.waxijiang.rush.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import top.waxijiang.rush.service.CourseService;

/**
 * @author waxijiang
 */
@Controller
@RequestMapping("/course")
public class CourseController {
    final
    CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @RequestMapping("/{id}")
    public String toCourseDetail(@PathVariable("id") String cid, Model model) {
        model.addAttribute("course", courseService.getById(cid));
        return "course_detail";
    }
}
