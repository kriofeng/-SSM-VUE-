package com.upc.fpbackstage.controller;

import com.upc.fpbackstage.dto.articleInfoDto;
import com.upc.fpbackstage.service.forumPublishService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@CrossOrigin
@RestController
@RequestMapping("/forumPublish")
public class forumPublishController {
    @Resource
    private forumPublishService forumpublishService;
    @RequestMapping("/publish")
    public int forumPublish(articleInfoDto dto){
        return forumpublishService.forumPublish(dto);
    }
}
