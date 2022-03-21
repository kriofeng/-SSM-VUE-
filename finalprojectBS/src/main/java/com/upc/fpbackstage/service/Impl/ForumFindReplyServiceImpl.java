package com.upc.fpbackstage.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.upc.fpbackstage.dto.replyDto;
import com.upc.fpbackstage.mapper.ForumFindReplyMapper;
import com.upc.fpbackstage.po.Comments;
import com.upc.fpbackstage.po.Reply;
import com.upc.fpbackstage.service.ForumFindReplyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class ForumFindReplyServiceImpl implements ForumFindReplyService {
    @Resource
    private ForumFindReplyMapper forumFindReplyMapper;
    @Override
    public List<Reply> findReply(replyDto dto) {

        return forumFindReplyMapper.findReply(dto);
    }
}
