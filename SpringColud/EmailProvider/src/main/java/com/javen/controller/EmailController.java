package com.javen.controller;

import com.javen.email.impl.IMailServiceImpl;
import com.javen.vo.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("email")
@Slf4j
public class EmailController {

    @Autowired
    private IMailServiceImpl mailService;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${spring.mail.from}")
    private String from;

    @Value("${server.port}")
    private String port;

    @RequestMapping("/test")
    public JsonResult test(HttpServletRequest request) {
        log.info("email provider port：{},{}",port,request.getServerPort());
        return new JsonResult("email provider port："+port);
    }


    @RequestMapping("/textEmail")
    public JsonResult textEmail(@RequestParam("to") String to,
                                @RequestParam("subject") String subject,
                                @RequestParam("content") String content) {
        try {
            log.info("收件人>{},邮件主题>{},邮件内容>{}", to, subject, content);
            mailService.sendSimpleMail(from, to, subject, content);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new JsonResult(-1, "邮件发送失败!!");
        }
        return new JsonResult();
    }

    @RequestMapping("/htmlEmail")
    public JsonResult htmlEmail(@RequestParam("to") String to,
                                @RequestParam("subject") String subject,
                                @RequestParam("content") String content) {
        try {
            mailService.sendHtmlMail(from, to, subject, content);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new JsonResult(-1, "邮件发送失败!!");
        }
        return new JsonResult();
    }

    @RequestMapping("/attachmentsMail")
    public JsonResult attachmentsMail(@RequestParam("to") String to,
                                      @RequestParam("subject") String subject,
                                      @RequestParam("content") String content,
                                      @RequestParam("filePath") String filePath) {
        try {
            mailService.sendAttachmentsMail(from, to, subject, content, filePath);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new JsonResult(-1, "邮件发送失败!!");
        }
        return new JsonResult();
    }

    @RequestMapping("/resourceMail")
    public JsonResult resourceMail(@RequestParam("to") String to,
                                   @RequestParam("subject") String subject,
                                   @RequestParam("content") String content,
                                   @RequestParam("imgPath") String imgPath,
                                   @RequestParam("rscId") String rscId) {
        try {
            mailService.sendResourceMail(from, to, subject, content, imgPath, rscId);

        } catch (Exception ex) {
            ex.printStackTrace();
            return new JsonResult(-1, "邮件发送失败!!");
        }
        return new JsonResult();
    }

    @RequestMapping("/templateMail")
    public JsonResult templateMail(@RequestParam("to") String to,
                                   @RequestParam("project") String project,
                                   @RequestParam("author") String author,
                                   @RequestParam("url") String url) {
        try {
            Context context = new Context();
            context.setVariable("project", project);
            context.setVariable("author", author);
            context.setVariable("url", url);
            String emailContent = templateEngine.process("emailTemp", context);

            mailService.sendHtmlMail(from, to, "这是模板邮件", emailContent);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new JsonResult(-1, "邮件发送失败!!");
        }
        return new JsonResult();
    }
}