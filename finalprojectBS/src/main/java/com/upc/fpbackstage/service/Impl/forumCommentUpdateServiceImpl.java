package com.upc.fpbackstage.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.upc.fpbackstage.dto.commentDto;
import com.upc.fpbackstage.mapper.forumCommentUpdateMapper;
import com.upc.fpbackstage.po.Articleinfo;
import com.upc.fpbackstage.po.Comments;
import com.upc.fpbackstage.service.forumCommentUpdateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class forumCommentUpdateServiceImpl implements forumCommentUpdateService {
    @Resource
    private forumCommentUpdateMapper forumcommentUpdateMapper;
    @Override
    public int forumCommentUpdate(commentDto dto) {
        return forumcommentUpdateMapper.forumCommentUpdate(dto);
    }

    @Override
    public PageInfo<Comments> findComment(int pageNum,int pageSize,  commentDto dto) {
        PageInfo<Comments> page=null;
        PageHelper.startPage(pageNum, pageSize);
        List<Comments> posts=forumcommentUpdateMapper.findComment(dto);
        page=new PageInfo<>(posts);
        return page;
    }
}
