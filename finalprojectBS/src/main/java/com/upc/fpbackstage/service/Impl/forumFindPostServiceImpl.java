package com.upc.fpbackstage.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.upc.fpbackstage.dto.articleInfoDto;
import com.upc.fpbackstage.mapper.forumFindPost;
import com.upc.fpbackstage.po.Articleinfo;
import com.upc.fpbackstage.service.forumFindPostService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class forumFindPostServiceImpl implements forumFindPostService {
    @Resource
    private forumFindPost forumfindPost;
    @Override
    public PageInfo<Articleinfo> findPost(int pageNum,int pageSize,articleInfoDto dto) {
        PageInfo<Articleinfo> page=null;
        PageHelper.startPage(pageNum, pageSize);
        List<Articleinfo> posts=forumfindPost.findPost(dto);
        page=new PageInfo<>(posts);
        return page;
    }
    @Override
    public PageInfo<Articleinfo> findPostByHeat(int pageNum,int pageSize,articleInfoDto dto) {
        PageInfo<Articleinfo> page=null;
        PageHelper.startPage(pageNum, pageSize);
        List<Articleinfo> posts=forumfindPost.findPostByHeat(dto);
        page=new PageInfo<>(posts);
        return page;
    }
    @Override
    public PageInfo<Articleinfo> findPostByGood(int pageNum,int pageSize,articleInfoDto dto) {
        PageInfo<Articleinfo> page=null;
        PageHelper.startPage(pageNum, pageSize);
        List<Articleinfo> posts=forumfindPost.findPostByGood(dto);
        page=new PageInfo<>(posts);
        return page;
    }
    @Override
    public Articleinfo findPostById(articleInfoDto dto) {

        return forumfindPost.findPostById(dto);

    }
}
