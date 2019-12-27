package com.javen.controller;

import com.javen.enums.SexEnum;
import com.javen.mapper.UserMapper;
import com.javen.model.User;
import com.javen.param.UserParam;
import com.javen.vo.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class UserController {
    @Resource
    private UserMapper userMapper;

    @RequestMapping("/")
    public List<User> index() {

        return userMapper.getAll();
    }

    @RequestMapping("/add")
    public void save() {
        User user = new User();
        user.setName("admin");
        user.setAge(18);
        user.setPwd("123456");
        user.setUserSex(SexEnum.MAN);
        userMapper.insert(user);
    }


    @RequestMapping("/getUser")
    public User getUser(Long id) {
        User user = userMapper.getById(id);
        return user;
    }

    @RequestMapping("update")
    public void update() {
        User user = new User();
        user.setId(1);
        user.setName("Javen");
        user.setAge(16);
        user.setPwd("123456");
        user.setUserSex(SexEnum.MAN);
        user.setInfo("IJPay让支付触手可及 https://gitee.com/javen205/IJPay");
        userMapper.Update(user);
    }

    @RequestMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        userMapper.delete(id);
    }


    @RequestMapping("/getList")
    public Page<User> getList(UserParam userParam) {
        List<User> users = userMapper.getList(userParam);
        long count = userMapper.getCount(userParam);
        Page page = new Page(userParam, count, users);
        return page;
    }

    @RequestMapping("/getListByUserSex")
    public List<User> getListByUserSex( String sex) {
        return userMapper.getListByUserSex(sex);
    }

    @RequestMapping("/getListByNameAndSex")
    public List<User> getListByNameAndSex() {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("name", "Javen");
        param.put("user_sex", "MAN");
        return userMapper.getListByNameAndSex(param);
    }
}