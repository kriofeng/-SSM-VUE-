package com.upc.fpbackstage.mapper;

import com.upc.fpbackstage.dto.commentDto;
import com.upc.fpbackstage.po.Comments;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface forumCommentUpdateMapper {
    @Update("insert into comments (commentId, articleId, content, userId, date) " +
            "VALUE (#{commentId},#{articleId},#{content},#{userId},#{date})")
    int forumCommentUpdate(commentDto dto);
    List<Comments> findComment(commentDto dto);
}
