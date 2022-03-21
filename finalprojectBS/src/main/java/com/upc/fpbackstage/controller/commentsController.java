package com.upc.fpbackstage.controller;

import com.upc.fpbackstage.dto.commentDto;
import com.upc.fpbackstage.po.Comments;
import com.upc.fpbackstage.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/comments")
public class commentsController {
    @Resource
    private CommentService sv;

    @RequestMapping("/findAllComments")
    List<commentDto> findAllComments(String articleId)
    {
        List<commentDto> list = null;
        list = sv.findAllComments(articleId);
        return list;
    }

    @RequestMapping("/addComments")
    int addComments(commentDto dto)
    {
        return sv.addComment(dto);
    }

    @RequestMapping("/replyCount")
    int replyCount(String commentId)
    {
        return sv.replyCount(commentId);
    }
}
