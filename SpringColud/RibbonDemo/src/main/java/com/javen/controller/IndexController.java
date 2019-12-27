package com.javen.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@RestController
public class IndexController {

    @Autowired
    private RestTemplate restTemplate;
    
    @GetMapping("/test")
    public String test(){
         String result = restTemplate.getForObject("http://email-provider/email/test",String.class);
         log.info("Ribbon LoadBalancer 获取到的结果>>{}",result);
         return result;
    }
}
