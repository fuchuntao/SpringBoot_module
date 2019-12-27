package com.javen.mapper;

import com.javen.param.UserParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.SQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

@Slf4j
public class UserSql {

    public String getList(UserParam userParam) {
        StringBuffer sql = new StringBuffer("select id, name, age, pwd, user_sex as userSex, info");
        sql.append(" from users where 1=1 ");
        if (userParam != null) {
            if (!StringUtils.isEmpty(userParam.getName())) {
                sql.append(" and name = #{name}");
            }
            if (!StringUtils.isEmpty(userParam.getUserSex())) {
                sql.append(" and user_sex = #{userSex}");
            }
        }
        sql.append(" order by id asc");
        sql.append(" limit " + userParam.getStartLine() + "," + userParam.getPageSize());
        log.info("getList sql is :" +sql.toString());
        return sql.toString();
    }

    public String getCount(UserParam userParam) {
        String sql= new SQL(){{
            SELECT("count(1)");
            FROM("users");
            if (!StringUtils.isEmpty(userParam.getName())) {
                WHERE("name = #{name}");
            }
            if (!StringUtils.isEmpty(userParam.getUserSex())) {
                WHERE("user_sex = #{userSex}");
            }
            //从这个toString可以看出，其内部使用高效的StringBuilder实现SQL拼接
        }}.toString();

        log.info("getCount sql is :" +sql);
        return sql;
    }
}