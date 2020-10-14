package top.waxijiang.rush.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author waxijiang
 */
@Controller
@RequestMapping("")
public class IndexController {
    @RequestMapping("")
    public String toIndex(){
        return "index";
    }
}
