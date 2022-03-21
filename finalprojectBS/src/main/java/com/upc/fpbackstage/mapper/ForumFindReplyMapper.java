package com.upc.fpbackstage.mapper;

import com.upc.fpbackstage.dto.replyDto;
import com.upc.fpbackstage.po.Reply;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ForumFindReplyMapper {
    List<Reply> findReply(replyDto dto);
}
