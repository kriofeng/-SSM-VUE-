package com.upc.fpbackstage.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.upc.fpbackstage.dto.commentDto;
import com.upc.fpbackstage.po.Comments;
import com.upc.fpbackstage.service.forumCommentUpdateService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@CrossOrigin
@RestController
@RequestMapping("/forumComment")
public class forumCommentUpdateController {
    @Resource
    private forumCommentUpdateService forumcommentUpdateService;
    @RequestMapping("/commentUpdate")
    int forumCommentUpdateService (commentDto dto){
        return forumcommentUpdateService.forumCommentUpdate(dto);
    }
    @RequestMapping("/findComment")
    PageInfo<Comments> findComment(int pageNum,int pageSize,commentDto dto){
        return forumcommentUpdateService.findComment(pageNum,pageSize,dto);
    }
}
