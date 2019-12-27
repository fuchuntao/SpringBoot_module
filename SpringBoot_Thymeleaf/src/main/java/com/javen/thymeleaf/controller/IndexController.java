package com.javen.thymeleaf.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Controller
@Slf4j
public class IndexController {
    @RequestMapping()
    public String index(ModelMap map) {
        map.addAttribute("msssage", "欢迎使用IJPay -By Javen");
        log.info("IJPay...");
        return "index";
    }
}
