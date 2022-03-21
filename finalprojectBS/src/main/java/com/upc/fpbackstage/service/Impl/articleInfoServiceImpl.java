package com.upc.fpbackstage.service.Impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.upc.fpbackstage.dto.articleInfoDto;
import com.upc.fpbackstage.dto.markedDto;
import com.upc.fpbackstage.mapper.articleInfoMapper;
import com.upc.fpbackstage.po.Articleinfo;
import com.upc.fpbackstage.service.articleInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class articleInfoServiceImpl implements articleInfoService {
    @Resource
    private articleInfoMapper articleInfoMapper;

    @Override
    public PageInfo<Articleinfo> findAllPosts(int pageNum, int pageSize) {
        PageInfo<Articleinfo> page = null;
        PageHelper.startPage(pageNum, pageSize);
        List<Articleinfo> pLists = articleInfoMapper.findAllPosts();
        page = new PageInfo<>(pLists);
        return page;
    }

    @Override
    public PageInfo<Articleinfo> findPostsByParam(articleInfoDto dto, int pageNum, int pageSize) {
        PageInfo<Articleinfo> page = null;
        PageHelper.startPage(pageNum, pageSize);
        List<Articleinfo> pLists = articleInfoMapper.findPostsByParam(dto);
        page = new PageInfo<>(pLists);
        return page;
    }

    @Override
    public int updateLableId(String articleId, int changeType) {
        int row = articleInfoMapper.updateLableId(articleId,changeType);
        return row;
    }

    @Override
    public int changePosts(String articleId) {
        int row = articleInfoMapper.ChangePosts(articleId);
        return row;
    }

    @Override
    public PageInfo<Articleinfo> focusTrend(articleInfoDto dto, int pageNum, int pageSize) {

        PageInfo<Articleinfo> page = null;
        PageHelper.startPage(pageNum, pageSize);
        List<Articleinfo> pLists = articleInfoMapper.focusTrend(dto);
        page = new PageInfo<>(pLists);
        return page;
    }

    @Override
    public int markCountChange(String articleId, int changeType) {
        return articleInfoMapper.markCountChange(articleId, changeType);
    }

    @Override
    public int checkIfMarked(String userId, String articleId) {
        return articleInfoMapper.checkIfMarked(userId, articleId);
    }

    @Override
    public PageInfo<Articleinfo> findAllBlogs(int pageNum, int pageSize) {
        PageInfo<Articleinfo> page = null;
        PageHelper.startPage(pageNum, pageSize);
        List<Articleinfo> pLists = articleInfoMapper.findAllBlogs();
        page = new PageInfo<>(pLists);
        return page;
    }

    @Override
    public PageInfo<Articleinfo> findBlogsByParam(int pageNum, int pageSize, articleInfoDto dto) {
        PageInfo<Articleinfo> page = null;
        PageHelper.startPage(pageNum, pageSize);
        List<Articleinfo> pLists = articleInfoMapper.findBlogsByParam(dto);
        page = new PageInfo<>(pLists);
        return page;
    }

    @Override
    public int BlogCount() {
        return articleInfoMapper.getBlogCount();
    }

    @Override
    public int PostCount() {
        return articleInfoMapper.getPostCount();
    }

    @Override
    public PageInfo<markedDto> findtitle(int pageNum, int pageSize, String userId) {
        PageInfo<markedDto> page = null;
        PageHelper.startPage(pageNum, pageSize);
        List<markedDto> titles=articleInfoMapper.findtitle(userId);
        page = new PageInfo<>(titles);
        return page;
    }

    @Override
    public int cancleMarked(String userId, String articleId) {
        return articleInfoMapper.cancelMarked(userId, articleId);
    }

    @Override
    public int markTo(String userId, String articleId) {
        return articleInfoMapper.markTo(userId, articleId);
    }

    @Override
    public int likCountChange(String articleId, int changeType) {
        return articleInfoMapper.likeCountChange(articleId, changeType);
    }

    @Override
    public int checkIfLiked(String userId, String articleId) {
        return articleInfoMapper.checkIfLiked(userId, articleId);
    }

    @Override
    public int cancleLiked(String userId, String articleId) {
        return articleInfoMapper.cancelLiked(userId, articleId);
    }

    @Override
    public int likeTo(String userId, String articleId) {
        return articleInfoMapper.likeTo(userId,articleId);
    }

    @Override
    public int publishBlog(articleInfoDto dto) {
        return articleInfoMapper.publishBlog(dto);
    }

    @Override
    public articleInfoDto findBlogById(String articleId) {
        return articleInfoMapper.findBlogById(articleId);
    }

    @Override
    public int commentCount(String articleId) {
        return articleInfoMapper.commentCount(articleId);
    }

    @Override
    public int changeCommentCount(String articleId, int changeType) {
        return articleInfoMapper.changeCommentCount(articleId, changeType);
    }

    @Override
    public int changeViewCount(String articleId) {
        return articleInfoMapper.changeViewCount(articleId);
    }
}
