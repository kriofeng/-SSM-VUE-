package com.upc.fpbackstage.controller;

import com.github.pagehelper.PageInfo;
import com.upc.fpbackstage.dto.articleInfoDto;
import com.upc.fpbackstage.po.Articleinfo;
import com.upc.fpbackstage.service.blogFindPostService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/FindBlogPost")
public class blogFindPostController {
    @Resource
    private blogFindPostService blogfindPostService;
    @RequestMapping("/findPost")
    PageInfo<Articleinfo> findPost(int pageNum, int pageSize, articleInfoDto dto){
        return blogfindPostService.findPost(pageNum,pageSize,dto);
    }
    @RequestMapping("/findHotUser")
    List<Articleinfo> findHotUser(){
        return blogfindPostService.findHotUser();
    }
    @RequestMapping("/findHotPost")
    List<Articleinfo> findHotPost(){
        return blogfindPostService.findHotPost();
    }
    @RequestMapping("/findMyBlog")
    PageInfo<Articleinfo> findMyBlog(int pageNum2,int pageSize2,articleInfoDto dto){
        return blogfindPostService.findMyBlog(pageNum2,pageSize2,dto);
    }
}
