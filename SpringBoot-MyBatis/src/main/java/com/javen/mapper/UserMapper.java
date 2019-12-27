package com.javen.mapper;

import com.javen.enums.SexEnum;
import com.javen.model.User;
import com.javen.param.UserParam;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    @Select("select * from users")
    @Results({
            @Result(property = "userSex",column = "user_sex",javaType = SexEnum.class),
    })
    List<User> getAll();


    @SelectProvider(type = UserSql.class, method = "getList")
    List<User> getList(UserParam userParam);

    @Select("SELECT * FROM users WHERE user_sex = #{user_sex}")
    List<User> getListByUserSex(@Param("user_sex") String userSex);

    @Select("SELECT * FROM users WHERE name=#{name} AND user_sex = #{user_sex}")
    List<User> getListByNameAndSex(Map<String, Object> map);

    @SelectProvider(type = UserSql.class, method = "getCount")
    int getCount(UserParam userParam);

    @Select("select * from users where id=#{id}")
    @Results({
            @Result(property = "userSex",column = "user_sex",javaType = SexEnum.class),
    })
    User getById(Long id);

    @Insert({"insert into users(name,age,user_sex,pwd，info) values (#{name},#{age},#{userSex},#{pwd},#{info})"})
    void insert(User user);

    @Update({"update users set name=#{name} where id=#{id}"})
    void update(User user);

    @Update({"<script> ",
            "update users " ,
            "<set>" ,
            " <if test='name != null'>name=#{name},</if>" ,
            " <if test='pwd != null'>pwd=#{pwd},</if>" ,
            " <if test='info != null'>pwd=#{info},</if>" ,
            " <if test='userSex != null'>user_sex=#{userSex},</if>" ,
            " <if test='age != null'>age=#{age},</if>" ,
            " </set> ",
            "where id=#{id} " ,
            "</script>"})
    void Update(User user);

    @Delete("delete from users where id=#{id}")
    void delete(Long id);
}