package com.upc.fpbackstage.controller;

import com.upc.fpbackstage.po.Articleinfo;
import com.upc.fpbackstage.service.forumFindIdService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@CrossOrigin
@RestController
@RequestMapping("/ForumId")
public class forumFindIdController {
    @Resource
    private forumFindIdService forumfindIdService;
    @RequestMapping("/findId")
    int findId(){
        return forumfindIdService.findId();

    }
}
