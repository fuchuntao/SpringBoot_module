package com.javen.controller;

import com.javen.mapper.master.MasterUserMapper;
import com.javen.mapper.slave.SlaveUserMapper;
import com.javen.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/")
public class UserController {

    /**
     * 如果使用的是 Idea，这块的注解经常会报“could not autowire”，
     * Eclipse 却没有问题，其实代码是正确的，这是 Idea 的误报。
     * 可以选择降低 Autowired 检测的级别，不要提示就好。
     *
     * 在 File | Settings | Editor | Inspections
     * 选项中使用搜索功能找到 Autowiring for Bean Class，
     * 将 Severity 的级别由之前的 error 改成 warning 即可。
     */
    @Autowired
    private SlaveUserMapper slaveUserMapper;

    @Autowired
    private MasterUserMapper masterUserMapper;

    @RequestMapping("/")
    public String index(Long id) {
       return "SpringBoot 整合 MyBatis 多数据元";
    }


    @RequestMapping("/getUsers")
    public List<User> getUsers() {
        List<User> users=masterUserMapper.getAll();
        return users;
    }

    @RequestMapping("/getUser")
    public User getUser(Long id) {
        User user=slaveUserMapper.getById(id);
        return user;
    }
}