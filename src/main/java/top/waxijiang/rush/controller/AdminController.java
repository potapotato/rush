package top.waxijiang.rush.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import top.waxijiang.rush.entity.Course;
import top.waxijiang.rush.entity.Question;
import top.waxijiang.rush.entity.QuestionType;
import top.waxijiang.rush.entity.User;
import top.waxijiang.rush.service.CourseService;
import top.waxijiang.rush.service.QuestionService;
import top.waxijiang.rush.service.QuestionTypeService;
import top.waxijiang.rush.service.UserService;
import top.waxijiang.rush.vo.QuestionVo;

import java.util.ArrayList;
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

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionTypeService questionTypeService;

    @GetMapping("/")
    public String index() {
        return "admin";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "admin_welcome";
    }

    @GetMapping("/addCourse")
    public String toAddCourse(Model model) {
        List<User> users = userService.findAllUser();
        model.addAttribute("users", users);
        return "admin_course_add";
    }

    @PostMapping("/addCourse")
    public String addCourse(String name, String desc, String createUserId, String imgUrl, Model model) {
        boolean b = courseService.addCourse(name, desc, createUserId, imgUrl);
        if (b) {
            model.addAttribute("success", 1);
            model.addAttribute("msg", "添加成功!");
        } else {
            model.addAttribute("success", 0);
            model.addAttribute("msg", "添加失败，内部错误!");
        }
        List<Course> courses = courseService.getAllCourse();
        model.addAttribute("courses", courses);
        return "admin_course_list";
    }

    @GetMapping("/listCourse")
    public String listCourse(Model model) {
        List<Course> courses = courseService.getAllCourse();
        model.addAttribute("courses", courses);
        return "admin_course_list";
    }

    @GetMapping("/deleteCourse")
    public String deleteCourse(String id, Model model) {
        boolean b = courseService.deleteCourseById(id);
        if (b) {
            model.addAttribute("success", 1);
            model.addAttribute("msg", "删除成功!");
        } else {
            model.addAttribute("success", 0);
            model.addAttribute("msg", "删除失败,内部错误!");
        }
        List<Course> courses = courseService.getAllCourse();
        model.addAttribute("courses", courses);
        return "admin_course_list";
    }

    @GetMapping("/updateCourse")
    public String toUpdateCourse(String id, Model model) {
        Course course = courseService.findCourseById(id);
        Integer userId = course.getCreateUserId();
        User user = userService.findUserById(userId);
        model.addAttribute("username", user.getUsername());
        model.addAttribute("course", course);
        return "admin_course_modify";
    }

    @PostMapping("/updateCourse")
    public String updateCourse(String name, String desc, String imgUrl, String id, String enabled, Model model) {
        boolean b = courseService.updateCourse(name, desc, imgUrl, enabled, id);
        if (b) {
            model.addAttribute("success", 1);
            model.addAttribute("msg", "修改成功!");
        } else {
            model.addAttribute("success", 0);
            model.addAttribute("msg", "修改失败,内部错误!");
        }
        List<Course> courses = courseService.getAllCourse();
        model.addAttribute("courses", courses);
        return "admin_course_list";
    }

    @GetMapping("/listQuestion")
    public String toListQuestion(Model model) {
        List<Question> questions = questionService.findAllQuestion();
        List<QuestionVo> questionVos = new ArrayList<>();
        for (Question question : questions) {
            QuestionVo questionVo = new QuestionVo();
            questionVo.setQuestion(question);
            questionVo.setCourseName(courseService.findCourseById(String.valueOf(question.getCourseId())).getName());
            questionVos.add(questionVo);
        }
        model.addAttribute("questionVos", questionVos);
        return "admin_question_list";
    }

    @GetMapping("/addQuestion")
    public String toAddQuestion(Model model) {
        List<Course> courses = courseService.findAllCourse();
        model.addAttribute("courses", courses);
        List<QuestionType> types = questionTypeService.findAllType();
        model.addAttribute("types", types);
        List<User> users = userService.findAllUser();
        model.addAttribute("users", users);

        return "admin_question_add";
    }

    @PostMapping("/addQuestion")
    public String addQuestion(Model model, String courseId, String questionText, String questionTrueImageUrl, String answerText, String answerTrueImageUrl, String score, String typeId, String userId) {
        boolean b = questionService.addQuestion(courseId, questionText, questionTrueImageUrl, answerText, answerTrueImageUrl, score, typeId, userId);
        if (b) {
            model.addAttribute("success", 1);
            model.addAttribute("msg", "添加成功!");
        } else {
            model.addAttribute("success", 0);
            model.addAttribute("msg", "添加失败,内部错误!");
        }
        List<Question> questions = questionService.findAllQuestion();
        List<QuestionVo> questionVos = new ArrayList<>();
        for (Question question : questions) {
            QuestionVo questionVo = new QuestionVo();
            questionVo.setQuestion(question);
            questionVo.setCourseName(courseService.findCourseById(String.valueOf(question.getCourseId())).getName());
            questionVos.add(questionVo);
        }
        model.addAttribute("questionVos", questionVos);
        return "admin_question_list";
    }

    @GetMapping("deleteQuestion")
    public String deleteQuestion(String id, Model model) {
        boolean b = questionService.deleteById(id);
        if (b) {
            model.addAttribute("success", 1);
            model.addAttribute("msg", "删除成功!");
        } else {
            model.addAttribute("success", 0);
            model.addAttribute("msg", "删除失败,内部错误!");
        }
        List<Question> questions = questionService.findAllQuestion();
        List<QuestionVo> questionVos = new ArrayList<>();
        for (Question question : questions) {
            QuestionVo questionVo = new QuestionVo();
            questionVo.setQuestion(question);
            questionVo.setCourseName(courseService.findCourseById(String.valueOf(question.getCourseId())).getName());
            questionVos.add(questionVo);
        }
        model.addAttribute("questionVos", questionVos);
        return "admin_question_list";
    }
}
