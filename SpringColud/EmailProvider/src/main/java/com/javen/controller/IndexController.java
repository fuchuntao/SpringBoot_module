package com.javen.controller;

import com.javen.vo.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@RestController
public class IndexController {
    private final static Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Value("${server.port}")
    private String port;


    @GetMapping("/")
    public String emailTemp() {
        return "emailTemp";
    }

    @GetMapping("port")
    public String index() {
        return "Email 端口号：" + port;
    }

    @RequestMapping("/log")
    public String testLog() {
        String IJPay = "https://gitee.com/Javen205/IJPay";
        String JPay = "https://gitee.com/Javen205/JPay";
        logger.info("==={}让支付触手可及;{}简易而不简单的Android支付SDK", IJPay, JPay);
        logger.debug("===测试日志debug级别打印===");
        logger.info("===测试日志info级别打印===");
        logger.error("===测试日志error级别打印===");
        logger.warn("===测试日志warn级别打印===");

        return "success";
    }

    @RequestMapping("/json")
    public JsonResult<Map<String,Object>> json(){
        JsonResult<Map<String,Object>> jsonResult = new JsonResult<>();

        Map<String,Object> map = new HashMap<>();
        map.put("desc","SpringBoot send email");
        map.put("date",new Date());
        map.put("name","Javen");
        map.put("blog","https://javen.blog.csdn.net");
        map.put("url",null);
        jsonResult.setData(map);
        return jsonResult;
    }

}
