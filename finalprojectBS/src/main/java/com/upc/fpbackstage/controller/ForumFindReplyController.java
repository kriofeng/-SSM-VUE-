package com.upc.fpbackstage.controller;

import com.github.pagehelper.PageInfo;
import com.upc.fpbackstage.dto.replyDto;
import com.upc.fpbackstage.po.Reply;
import com.upc.fpbackstage.service.ForumFindReplyService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/forumReply")
public class ForumFindReplyController {
    @Resource
    private ForumFindReplyService forumFindReplyService;
    @RequestMapping("/findReply")
    List<Reply> findReply( replyDto dto){
        return forumFindReplyService.findReply(dto);
    }
}
