package com.upc.fpbackstage.service;

import com.github.pagehelper.PageInfo;
import com.upc.fpbackstage.dto.replyDto;
import com.upc.fpbackstage.po.Reply;

import java.util.List;

public interface ForumFindReplyService {
    List<Reply> findReply( replyDto dto);
}
