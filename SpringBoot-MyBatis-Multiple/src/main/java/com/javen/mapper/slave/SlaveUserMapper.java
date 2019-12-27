package com.javen.mapper.slave;

import com.javen.enums.SexEnum;
import com.javen.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SlaveUserMapper {
    @Select("select * from users")
    @Results({
            @Result(property = "userSex",column = "user_sex",javaType = SexEnum.class),
    })
    List<User> getAll();

    @Select("select * from users where id=#{id}")
    @Results({
            @Result(property = "userSex",column = "user_sex",javaType = SexEnum.class),
    })
    User getById(Long id);
}