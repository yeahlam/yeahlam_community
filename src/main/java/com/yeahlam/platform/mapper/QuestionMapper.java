package com.yeahlam.platform.mapper;

import com.yeahlam.platform.pojo.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.boot.autoconfigure.data.cassandra.CassandraDataAutoConfiguration;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface QuestionMapper {
    @Insert("insert into question (title,description,tag,creator,gmt_create,gmt_modified) values " +
            "(#{title},#{description},#{tag},#{creator},#{gmt_create},#{gmt_modified})")
    public void InsertQuestion(Question question);
}
