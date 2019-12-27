package com.javen.controller;

import com.javen.common.CustomErrorException;
import com.javen.common.ErrorEnum;
import com.javen.vo.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exception")
public class IndexController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @GetMapping("/test")
    public JsonResult test(@RequestParam("name") String name,
                           @RequestParam("age") int age) {
        logger.info("name：{}", name);
        logger.info("age：{}", age);
        return new JsonResult();
    }

    @GetMapping("/null")
    public JsonResult testNullPointException() {
        List<String> list = null;
        System.out.println(list.size());
        return new JsonResult();
    }

    @GetMapping("/customException")
    public JsonResult customException() {
        try {
            int j = 1 / 0;
        } catch (Exception exception) {
            throw new CustomErrorException(ErrorEnum.UNEXPECTED_EXCEPTION);
        }
        return new JsonResult();
    }
}
