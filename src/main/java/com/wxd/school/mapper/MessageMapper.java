package com.wxd.school.mapper;

import com.wxd.school.model.Message;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MessageMapper {
    @Insert("insert into message(title,description,gmt_create,gmt_modified,creator_id,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creatorId},#{tag})")
    void create(Message message);

    @Select("select * from message  order by gmt_create desc limit #{start}, #{size} ")
    List<Message> list(@Param(value = "start") Integer start, @Param(value = "size") Integer size);

    @Select("select count(1) from message")
    Integer count();

    @Select("select count(1) from message where creator_id=#{userId}")
    Integer countByUserId(@Param("userId") Integer userId);

    @Select("select * from message where creator_id=#{userId}  order by gmt_create desc limit #{start}, #{size}")
    List<Message> listByUserId(@Param("userId")Integer userId, @Param(value = "start") Integer start, @Param(value = "size") Integer size);

    @Select("select * from message where id = #{id}")
    Message getById(@Param("id") Integer id);

    @Update("update message set title = #{title},description = #{description}, gmt_modified = #{gmtModified}, tag = #{tag} where id = #{id}")
    void update(Message message);

    @Update("update message set view_count =view_count + #{viewCount} where id = #{id}")
    void updateView(Message message);
}
