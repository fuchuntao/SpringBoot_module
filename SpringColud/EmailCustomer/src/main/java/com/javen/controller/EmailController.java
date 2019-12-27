package com.javen.controller;

import com.javen.model.User;
import com.javen.service.EmailFeignService;
import com.javen.vo.JsonResult;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
@Slf4j
public class EmailController {

    @Autowired
    private EmailFeignService emailFeignService;

    @Value("${server.port}")
    private String port;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(){
        log.info(" Customer 访问的端口号为：{}",port);
        return emailFeignService.test();
    }

    /**
     * Feign 的 Get 请求多参数传递
     *
     * @param user 用户对象
     * @return 添加后返回的信息
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(@RequestBody @ApiParam(name = "添加用户", value = "传入json格式", required = true) User user) {
        return emailFeignService.addUser(user);
    }

    /**
     * Feign 的 Post 请求多参数传递
     *
     * @param user 用户对象
     * @return 更新后返回的信息
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateUser(@RequestBody @ApiParam(name = "更新用户", value = "传入json格式", required = true) User user) {
        return emailFeignService.updateUser(user);
    }

    @RequestMapping(value = "/textEmail", method = RequestMethod.POST)
    public JsonResult textEmail(@RequestParam("to") @ApiParam(name = "to", value = "收件人", defaultValue = "javendev@126.com", required = true) String to,
                              @RequestParam("subject") @ApiParam(name = "subject", value = "邮件标题", defaultValue = "这是一封普通的邮件", required = true) String subject,
                              @RequestParam("content") @ApiParam(name = "content", value = "邮件文本内容", defaultValue = "这是一封普通的 Spring Boot 测试邮件", required = true) String content) {
        return emailFeignService.textEmail(to, subject, content);
    }

    @RequestMapping(value = "/htmlEmail", method = RequestMethod.POST)
    public JsonResult htmlEmail(@RequestParam("to") @ApiParam(name = "to", value = "收件人", defaultValue = "javendev@126.com", required = true) String to,
                              @RequestParam("subject") @ApiParam(name = "subject", value = "邮件标题", defaultValue = "这是一HTML的邮件", required = true) String subject,
                              @RequestParam("content") @ApiParam(name = "content", value = "邮件文本内容", defaultValue = "<h2>IJPay 让支付触手可及<h2>", required = true) String content) {
        return emailFeignService.htmlEmail(to, subject, content);
    }

}