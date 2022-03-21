package com.upc.fpbackstage.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.upc.fpbackstage.dto.articleInfoDto;
import com.upc.fpbackstage.po.Articleinfo;
import com.upc.fpbackstage.service.forumFindPostService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@CrossOrigin
@RestController
@RequestMapping("/FindPost")
public class forumFindPostController {
    @Resource
    private forumFindPostService forumfindPostService;
    @RequestMapping("/post")
    PageInfo<Articleinfo> findPost(int pageNum, int pageSize, articleInfoDto dto){
        return forumfindPostService.findPost(pageNum,pageSize,dto);
    }
    @RequestMapping("/postbyheat")
    PageInfo<Articleinfo> findPostByHeat(int pageNum, int pageSize, articleInfoDto dto){
        return forumfindPostService.findPostByHeat(pageNum,pageSize,dto);
    }
    @RequestMapping("/postbygood")
    PageInfo<Articleinfo> findPostByGood(int pageNum, int pageSize, articleInfoDto dto){
        return forumfindPostService.findPostByGood(pageNum,pageSize,dto);
    }
    @RequestMapping("/postbyid")
    Articleinfo findPostById(articleInfoDto dto){
        return forumfindPostService.findPostById(dto);
    }


}
