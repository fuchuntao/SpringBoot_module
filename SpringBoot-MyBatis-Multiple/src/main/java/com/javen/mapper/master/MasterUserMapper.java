package com.javen.mapper.master;

import com.javen.enums.SexEnum;
import com.javen.model.User;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MasterUserMapper {
    @Select("select * from users")
    @Results({
            @Result(property = "userSex",column = "user_sex",javaType = SexEnum.class),
    })
    List<User> getAll();
}