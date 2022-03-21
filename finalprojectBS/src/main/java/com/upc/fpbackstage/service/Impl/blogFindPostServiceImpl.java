package com.upc.fpbackstage.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.upc.fpbackstage.dto.articleInfoDto;
import com.upc.fpbackstage.mapper.blogFindPost;
import com.upc.fpbackstage.po.Articleinfo;
import com.upc.fpbackstage.service.blogFindPostService;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class blogFindPostServiceImpl implements blogFindPostService {
    @Resource
    private blogFindPost blogfindPost;
    @Override
    public PageInfo<Articleinfo> findPost(int pageNum, int pageSize, articleInfoDto dto) {
        PageInfo<Articleinfo> page=null;
        PageHelper.startPage(pageNum, pageSize);
        List<Articleinfo> posts=blogfindPost.findBlogPost(dto);
        page=new PageInfo<>(posts);
        return page;
    }
    @Override
    public List<Articleinfo> findHotUser() {
        return blogfindPost.findHotUser();
    }
    @Override
    public List<Articleinfo> findHotPost() {
        return blogfindPost.findHotPost();
    }

    @Override
    public PageInfo<Articleinfo> findMyBlog(int pageNum2, int pageSize2, articleInfoDto dto) {
        PageInfo<Articleinfo> page=null;
        PageHelper.startPage(pageNum2, pageSize2);
        List<Articleinfo> posts=blogfindPost.findMyBlog(dto);
        page=new PageInfo<>(posts);
        return page;

    }
}
