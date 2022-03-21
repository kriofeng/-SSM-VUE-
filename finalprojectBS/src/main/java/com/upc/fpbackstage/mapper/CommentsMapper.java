package com.upc.fpbackstage.mapper;

import com.upc.fpbackstage.dto.commentDto;
import com.upc.fpbackstage.po.Comments;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentsMapper {

    @Select("select a.*,b.userName,b.headPortrait \n" +
            "from comments a,userbaseinfo b\n" +
            "where articleId = #{articleId} and a.userId = b.userId")
    List<commentDto> findAllComments(String articleId);


    @Insert("insert into comments(commentId, articleId,content,userId,date) " +
            "values(#{commentId},#{articleId},#{content},#{userId},#{date})")
    int addComment(commentDto dto);


    @Select("select count(*) from reply where parentId = #{commentId}")
    int replyCount(String commentId);

}
