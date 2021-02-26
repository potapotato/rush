package top.waxijiang.rush.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import top.waxijiang.rush.entity.Course;
import top.waxijiang.rush.service.CourseService;

import java.util.List;

/**
 * @author waxijiang
 */
@Controller
@RequestMapping("admin")
@RequiresRoles("admin")
public class AdminController {
    @Autowired
    private CourseService courseService;

    @GetMapping("/")
    public String index(){
        return "admin";
    }

    @GetMapping("/welcome")
    public String welcome(){
        return "admin_welcome";
    }

    @GetMapping("/addCourse")
    public String addCourse(){
        return "admin_course_add";
    }

    @GetMapping("/listCourse")
    public String listCourse(Model model){
        List<Course> courses = courseService.getAllCourse();
        model.addAttribute("courses", courses);
        return "admin_course_list";
    }
}
