package top.waxijiang.rush.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author waxijiang
 */
@Controller
@RequestMapping("")
public class IndexController {
    Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping("")
    public String toIndex() {
        return "index";
    }


    @PostMapping("login")
    public String login(String username, String password) {
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(username, password));
        }catch (UnknownAccountException e){
            System.out.println("username error");
        }catch (IncorrectCredentialsException e){
            System.out.println("password error");
        }
        return "login";
    }

    @GetMapping("login")
    public String toLogin(){
        return "login";
    }
}
