package top.yuyg.blog.modules.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ThymeleafController {

    @RequestMapping("admin/write")
    public ModelAndView write(){
        ModelAndView mav = new ModelAndView("admin/writeblog");
        mav.addObject("hello", "你好啊,Thymeleaf");
        return mav;
    }
}
