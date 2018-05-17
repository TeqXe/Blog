package top.yuyg.blog.modules.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ThymeleafController {

    @RequestMapping("admin/write")
    public String write(){
        return "/admin/writeblog";
    }
}
