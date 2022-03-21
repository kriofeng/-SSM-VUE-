package com.upc.fpbackstage.service.Impl;

import com.upc.fpbackstage.dto.replyDto;
import com.upc.fpbackstage.mapper.ReplyMapper;
import com.upc.fpbackstage.service.replyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class replyServiceImpl implements replyService {
    @Resource
    ReplyMapper mp;

    @Override
    public List<replyDto> findAllReply(String commentId) {
        return mp.findAllReply(commentId);
    }

    @Override
    public int addReply(replyDto dto) {
        return mp.addReply(dto);
    }
}
