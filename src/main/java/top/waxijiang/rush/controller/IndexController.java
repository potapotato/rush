package top.waxijiang.rush.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import top.waxijiang.rush.entity.User;
import top.waxijiang.rush.service.UserService;

import java.util.Date;

/**
 * @author waxijiang
 */
@Controller
@RequestMapping("")
public class IndexController {
    Logger logger = LoggerFactory.getLogger(IndexController.class);
    final UserService userService;

    public IndexController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("")
    public String toIndex() {
        return "index";
    }


    @PostMapping("login")
    public ModelAndView login(String username, String password) {
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(username, password));
            return new ModelAndView("index");
        } catch (UnknownAccountException e) {
            System.out.println("username error");
            return new ModelAndView("login", "msg", "用户名不存在");
        } catch (IncorrectCredentialsException e) {
            System.out.println("password error");
            return new ModelAndView("login", "msg", "密码错误");
        }
    }

    @PostMapping("register")
    public ModelAndView register(String username, String password, String repeatPassword, String nickname, String email, String iconUrl) {
        if (password.equals(repeatPassword)){
            User user = new User(username, password, nickname, iconUrl, new Date(), email, true);
            if (userService.register(user)){
                return new ModelAndView("redirect:/index");
            }else {
                return new ModelAndView("/register", "msg", "注册失败");
            }
        }else {
            return new ModelAndView("/register", "msg", "两次输入的密码不同");
        }
    }

    @GetMapping("register")
    public String toRegister() {
        return "register";
    }

    @GetMapping("login")
    public String toLogin() {
        return "login";
    }
}
