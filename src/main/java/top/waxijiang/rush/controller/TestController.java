package top.waxijiang.rush.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author waxijiang
 */
@Controller
@RequestMapping("test")
public class TestController {
    @RequestMapping("")
     public String test(){
         return "hello";
     }
}
