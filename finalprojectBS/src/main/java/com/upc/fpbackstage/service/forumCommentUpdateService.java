package com.upc.fpbackstage.service;

import com.github.pagehelper.PageInfo;
import com.upc.fpbackstage.dto.commentDto;
import com.upc.fpbackstage.po.Comments;

public interface forumCommentUpdateService {
    int forumCommentUpdate(commentDto dto);
    PageInfo<Comments> findComment(int pageSize,int pageNum,commentDto dto);
}
