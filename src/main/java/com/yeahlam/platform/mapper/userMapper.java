package com.yeahlam.platform.mapper;

import com.yeahlam.platform.pojo.user;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface userMapper {
    @Insert("insert into user (account_id,name,token,gmt_create,gm_modified)" +
            " values (#{account_id},#{name},#{token},#{gmt_create},#{gm_modified})")
    void insert(user user);

    @Select("select * from user where token = #{token}")
    user getUserByToken(String token);
}
