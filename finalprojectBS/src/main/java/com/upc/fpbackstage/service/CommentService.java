package com.upc.fpbackstage.service;

import com.upc.fpbackstage.dto.commentDto;
import com.upc.fpbackstage.po.Comments;

import java.util.List;

public interface CommentService {
    List<commentDto> findAllComments(String articleId);

    int addComment(commentDto dto);

    int replyCount(String commentId);

}
