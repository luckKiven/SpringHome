package cn.tedu.kiven.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class mainController {
    @GetMapping("/")
    public String root(){
        return "redirect:/sg/index";
    }
    @GetMapping("/index")
    public String index(){
        return "redirect:/sg/index";
    }
}
