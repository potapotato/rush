package top.waxijiang.rush.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    private final CourseService courseService;

    private final UserService userService;

    private final QuestionService questionService;

    private final QuestionTypeService questionTypeService;

    public AdminController(CourseService courseService, UserService userService, QuestionService questionService, QuestionTypeService questionTypeService) {
        this.courseService = courseService;
        this.userService = userService;
        this.questionService = questionService;
        this.questionTypeService = questionTypeService;
    }

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
        Course course = new Course();
        course.setName(name);
        course.setDescription(desc);
        course.setCreateUserId(Integer.valueOf(createUserId));
        course.setImgUrl(imgUrl);
        boolean b = courseService.save(course);
        if (b) {
            model.addAttribute("success", 1);
            model.addAttribute("msg", "添加成功!");
        } else {
            model.addAttribute("success", 0);
            model.addAttribute("msg", "添加失败，内部错误!");
        }
        List<Course> courses = courseService.list();
        model.addAttribute("courses", courses);
        return "admin_course_list";
    }

    @GetMapping("/listCourse")
    public String listCourse(Model model) {
        List<Course> courses = courseService.list();
        model.addAttribute("courses", courses);
        return "admin_course_list";
    }

    @GetMapping("/deleteCourse")
    public String deleteCourse(String id, Model model) {
        boolean b = courseService.removeById(id);
        if (b) {
            model.addAttribute("success", 1);
            model.addAttribute("msg", "删除成功!");
        } else {
            model.addAttribute("success", 0);
            model.addAttribute("msg", "删除失败,内部错误!");
        }
        List<Course> courses = courseService.list();
        model.addAttribute("courses", courses);
        return "admin_course_list";
    }

    @GetMapping("/updateCourse")
    public String toUpdateCourse(String id, Model model) {
        Course course = courseService.getById(id);
        Integer userId = course.getCreateUserId();
        User user = userService.findUserById(userId);
        model.addAttribute("username", user.getUsername());
        model.addAttribute("course", course);
        return "admin_course_modify";
    }

    @PostMapping("/updateCourse")
    public String updateCourse(String name, String desc, String imgUrl, String id, String enabled, Model model) {
        Course course = new Course();
        course.setId(Integer.valueOf(id));
        course.setName(name);
        course.setDescription(desc);
        course.setImgUrl(imgUrl);
        course.setEnabled("on".equals(enabled));
        boolean b = courseService.updateById(course);
        if (b) {
            model.addAttribute("success", 1);
            model.addAttribute("msg", "修改成功!");
        } else {
            model.addAttribute("success", 0);
            model.addAttribute("msg", "修改失败,内部错误!");
        }
        List<Course> courses = courseService.list();
        model.addAttribute("courses", courses);
        return "admin_course_list";
    }

    @GetMapping("/listQuestion")
    public String toListQuestion(Model model) {
        List<Question> questions = questionService.list();
        List<QuestionVo> questionVos = new ArrayList<>();
        for (Question question : questions) {
            QuestionVo questionVo = new QuestionVo();
            questionVo.setQuestion(question);
            questionVo.setCourseName(courseService.getById(String.valueOf(question.getCourseId())).getName());
            questionVos.add(questionVo);
        }
        model.addAttribute("questionVos", questionVos);
        return "admin_question_list";
    }

    @GetMapping("/addQuestion")
    public String toAddQuestion(Model model) {
        List<Course> courses = courseService.list();
        model.addAttribute("courses", courses);
        List<QuestionType> types = questionTypeService.list();
        model.addAttribute("types", types);
        List<User> users = userService.findAllUser();
        model.addAttribute("users", users);

        return "admin_question_add";
    }

    @PostMapping("/addQuestion")
    public String addQuestion(Model model, String courseId, String questionText, String questionTrueImageUrl, String answerText, String answerTrueImageUrl, String score, String typeId, String userId) {
        Question tmpQuestion = new Question();
        tmpQuestion.setCourseId(Integer.valueOf(courseId));
        tmpQuestion.setQuestionText(questionText);
        tmpQuestion.setQuestionUrl(questionTrueImageUrl);
        tmpQuestion.setAnswerText(answerText);
        tmpQuestion.setAnswerUrl(answerTrueImageUrl);
        tmpQuestion.setScore(Integer.valueOf(score));
        tmpQuestion.setQuestionTypeId(Integer.valueOf(typeId));
        tmpQuestion.setCreateUserId(Integer.valueOf(userId));
        boolean b = questionService.save(tmpQuestion);
        if (b) {
            model.addAttribute("success", 1);
            model.addAttribute("msg", "添加成功!");
        } else {
            model.addAttribute("success", 0);
            model.addAttribute("msg", "添加失败,内部错误!");
        }
        List<Question> questions = questionService.list();
        List<QuestionVo> questionVos = new ArrayList<>();
        for (Question question : questions) {
            QuestionVo questionVo = new QuestionVo();
            questionVo.setQuestion(question);
            questionVo.setCourseName(courseService.getById(String.valueOf(question.getCourseId())).getName());
            questionVos.add(questionVo);
        }
        model.addAttribute("questionVos", questionVos);
        return "admin_question_list";
    }

    @GetMapping("/updateQuestion")
    public String toUpdateQuestion(String id, Model model) {
        Question question = questionService.getById(id);
        model.addAttribute("question", question);
        List<Course> courses = courseService.list();
        model.addAttribute("courses", courses);
        List<QuestionType> types = questionTypeService.list();
        model.addAttribute("types", types);
        List<User> users = userService.findAllUser();
        model.addAttribute("users", users);
        return "admin_question_modify";
    }

    @PostMapping("/updateQuestion")
    public String updateQuestion(Model model, String id, String courseId, String questionText, String questionTrueImageUrl, String answerText, String answerTrueImageUrl, String score, String typeId, String userId, String enabled) {
        Question tmpQuestion = new Question();
        tmpQuestion.setId(Integer.valueOf(id));
        tmpQuestion.setCourseId(Integer.valueOf(courseId));
        tmpQuestion.setQuestionText(questionText);
        tmpQuestion.setQuestionUrl(questionTrueImageUrl);
        tmpQuestion.setAnswerText(answerText);
        tmpQuestion.setAnswerUrl(answerTrueImageUrl);
        tmpQuestion.setScore(Integer.valueOf(score));
        tmpQuestion.setQuestionTypeId(Integer.valueOf(typeId));
        tmpQuestion.setCreateUserId(Integer.valueOf(userId));
        tmpQuestion.setEnabled("on".equals(enabled));
        boolean b = questionService.updateById(tmpQuestion);
        if (b) {
            model.addAttribute("success", 1);
            model.addAttribute("msg", "修改成功!");
        } else {
            model.addAttribute("success", 0);
            model.addAttribute("msg", "修改失败,内部错误!");
        }
        List<Question> questions = questionService.list();
        List<QuestionVo> questionVos = new ArrayList<>();
        for (Question question : questions) {
            QuestionVo questionVo = new QuestionVo();
            questionVo.setQuestion(question);
            questionVo.setCourseName(courseService.getById(String.valueOf(question.getCourseId())).getName());
            questionVos.add(questionVo);
        }
        model.addAttribute("questionVos", questionVos);
        return "admin_question_list";
    }

    @GetMapping("deleteQuestion")
    public String deleteQuestion(String id, Model model) {
        boolean b = questionService.removeById(id);
        if (b) {
            model.addAttribute("success", 1);
            model.addAttribute("msg", "删除成功!");
        } else {
            model.addAttribute("success", 0);
            model.addAttribute("msg", "删除失败,内部错误!");
        }
        List<Question> questions = questionService.list();
        List<QuestionVo> questionVos = new ArrayList<>();
        for (Question question : questions) {
            QuestionVo questionVo = new QuestionVo();
            questionVo.setQuestion(question);
            questionVo.setCourseName(courseService.getById(String.valueOf(question.getCourseId())).getName());
            questionVos.add(questionVo);
        }
        model.addAttribute("questionVos", questionVos);
        return "admin_question_list";
    }
}
