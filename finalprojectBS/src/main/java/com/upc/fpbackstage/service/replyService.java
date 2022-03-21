package com.upc.fpbackstage.service;

import com.upc.fpbackstage.dto.replyDto;

import java.util.List;

public interface replyService {

    List<replyDto> findAllReply(String commentId);

    int addReply(replyDto dto);
}
