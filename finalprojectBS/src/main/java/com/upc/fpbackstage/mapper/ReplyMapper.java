package com.upc.fpbackstage.mapper;

import com.upc.fpbackstage.dto.replyDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ReplyMapper {


    @Select("SELECT a.*,b.userName,b.headPortrait,c.userName as replyName\n" +
            "from reply a, userbaseinfo b,userbaseinfo c\n" +
            "where a.userId = b.userId and a.replyTo = c.userId AND a.parentId = #{commentId}")
    List<replyDto> findAllReply(String commentId);


    @Insert("insert into reply(ReplyId,parentId,content,replyTo,date,userId) " +
            "values(#{replyId},#{parentId},#{content},#{replyTo},#{date},#{userId})")
    int addReply(replyDto dto);
}
