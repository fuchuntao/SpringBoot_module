package com.javen.service;

import com.javen.model.User;
import com.javen.vo.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "email-provider")
public interface EmailFeignService {

    @RequestMapping(value = "/email/test", method = RequestMethod.GET)
    public String test();

    @RequestMapping(value = "/user/add", method = RequestMethod.GET)
    public String addUser(User user);

    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    public String updateUser(@RequestBody User user);

    @RequestMapping(value = "/email/textEmail", method = RequestMethod.GET)
    public JsonResult textEmail(@RequestParam String to,@RequestParam String subject,@RequestParam String content);

    @RequestMapping(value = "/email/htmlEmail", method = RequestMethod.GET)
    public JsonResult htmlEmail(@RequestParam String to, @RequestParam String subject,@RequestParam String content);

    @RequestMapping(value = "/email/attachmentsMail", method = RequestMethod.GET)
    public JsonResult attachmentsMail(@RequestParam String to,@RequestParam String subject,@RequestParam String content,@RequestParam String filePath);

    @RequestMapping(value = "/email/resourceMail", method = RequestMethod.GET)
    public JsonResult resourceMail(@RequestParam String to,@RequestParam String subject,@RequestParam String content,@RequestParam String imgPath,@RequestParam String rscId);

}