package com.upc.fpbackstage.controller;

import com.github.pagehelper.PageInfo;
import com.upc.fpbackstage.dto.articleInfoDto;
import com.upc.fpbackstage.dto.markedDto;
import com.upc.fpbackstage.po.Articleinfo;
import com.upc.fpbackstage.service.articleInfoService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/post")
public class articleInfoController {
    @Resource
    private articleInfoService articleInfoService;

    @RequestMapping("/findByParam")
    public PageInfo<Articleinfo> findByParam(int pageNum, int pageSize, articleInfoDto dto)
    {
        PageInfo<Articleinfo> page = null;
        page = articleInfoService.findPostsByParam(dto,pageNum,pageSize);
        return page;
    }

    @RequestMapping("/findAllPosts")
    public PageInfo<Articleinfo> findAllPosts(int pageNum, int pageSize){
        PageInfo<Articleinfo> page = null;
        page = articleInfoService.findAllPosts(pageNum,pageSize);
        return page;
    }

    @RequestMapping("/changeLable")
    public int changeLable(String articleId, int changeType)
    {
        int row = articleInfoService.updateLableId(articleId,changeType);
        return row;
    }

    @RequestMapping("/delete")
    public int delete(String articleId)
    {
        int row = articleInfoService.changePosts(articleId);
        return row;
    }

    @RequestMapping("/findAllBlogs")
    public PageInfo<Articleinfo> findAllBlogs(int pageNum,int pageSize){
        PageInfo<Articleinfo> page = null;
        page = articleInfoService.findAllBlogs(pageNum,pageSize);
        return page;
    }

    @RequestMapping("/findBlogsByParam")
    public PageInfo<Articleinfo> findBlogsByParam(int pageNum,int pageSize,articleInfoDto dto){
        PageInfo<Articleinfo> page = null;
        page = articleInfoService.findBlogsByParam(pageNum,pageSize,dto);
        return page;
    }

    @RequestMapping("BlogCount")
    int BlogCount(){
        return articleInfoService.BlogCount();
    }

    @RequestMapping("/PostCount")
    int PostCount(){
        return articleInfoService.PostCount();
    }

    @RequestMapping("/findtitle")
    public PageInfo<markedDto> findtitle(int pageNum, int pageSize, String userId){
        return articleInfoService.findtitle(pageNum,pageSize,userId);
    }

    @RequestMapping("/focusTrend")
    public PageInfo<Articleinfo> focusTrend(articleInfoDto dto,int pageNum,int pageSize){
        PageInfo<Articleinfo> page = null;
        page = articleInfoService.focusTrend(dto,pageNum,pageSize);
        return page;
    }


    @RequestMapping("/markTo")
    public int markTo(String userId, String articleId)
    {
        return articleInfoService.markTo(userId, articleId);
    }

    @RequestMapping("/cancleMarked")
    public int cancleMarked(String userId, String articleId){
        return articleInfoService.cancleMarked(userId, articleId);
    }

    @RequestMapping("markChange")
    public int markChange(String userId, int changeType)
    {
        return articleInfoService.markCountChange(userId, changeType);
    }

    @RequestMapping("checkMarked")
    public int checkMarked(String userId, String articleId)
    {
        return articleInfoService.checkIfMarked(userId, articleId);
    }




    @RequestMapping("/likeTo")
    public int likeTo(String userId, String articleId)
    {
        return articleInfoService.likeTo(userId, articleId);
    }

    @RequestMapping("/cancleLiked")
    public int cancleLiked(String userId, String articleId){
        return articleInfoService.cancleLiked(userId, articleId);
    }

    @RequestMapping("likeChange")
    public int likeChange(String userId, int changeType)
    {
        return articleInfoService.likCountChange(userId, changeType);
    }

    @RequestMapping("checkLiked")
    public int checkLiked(String userId, String articleId)
    {
        return articleInfoService.checkIfLiked(userId, articleId);
    }



    @RequestMapping("/publishBlog")
    public int publishBlog(articleInfoDto dto){
        return articleInfoService.publishBlog(dto);
    }

    @RequestMapping("/findBlogById")
    public articleInfoDto findBlogById(String articleId){
        return articleInfoService.findBlogById(articleId);
    }


    @RequestMapping("/commentCount")
    public int commentCount(String articleId) {
        return articleInfoService.commentCount(articleId);
    }

    @RequestMapping("/changeCommentCount")
    public int changeCommentCount(String articleId, int changeType) {
        return articleInfoService.changeCommentCount(articleId, changeType);
    }

    @RequestMapping("/changeViewCount")
    public int changeViewCount(String articleId)
    {
        return articleInfoService.changeViewCount(articleId);
    }
}
