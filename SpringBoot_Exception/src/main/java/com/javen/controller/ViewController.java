package com.javen.controller;

import com.javen.common.ErrorEnum;
import com.javen.common.ViewErrorException;
import com.javen.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class ViewController {
    @GetMapping
    public String index(Model model){
        model.addAttribute("user",
                User.builder()
                        .age(18)
                        .name("Javen")
                        .desc("测试")
                        .url("https://gitee.com/Javen205")
                        .build());
        return "index";
    }
    @GetMapping("/error")
    public String viewException(Model model){
        try{
            int a = 1/0;
        }catch (Exception ex){
            throw new ViewErrorException(ErrorEnum.UNEXPECTED_EXCEPTION);
        }
        return "index";
    }
}
