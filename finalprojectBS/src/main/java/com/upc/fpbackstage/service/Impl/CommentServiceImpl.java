package com.upc.fpbackstage.service.Impl;


import com.upc.fpbackstage.dto.commentDto;
import com.upc.fpbackstage.mapper.CommentsMapper;
import com.upc.fpbackstage.po.Comments;
import com.upc.fpbackstage.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class CommentServiceImpl implements CommentService{
    @Resource
    private CommentsMapper mp;

    @Override
    public List<commentDto> findAllComments(String articleId) {
        List<commentDto> list = null;
        list = mp.findAllComments(articleId);
        return list;
    }

    @Override
    public int addComment(commentDto dto) {
        return mp.addComment(dto);
    }

    @Override
    public int replyCount(String commentId) {
        return mp.replyCount(commentId);
    }
}
