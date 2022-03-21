package com.upc.fpbackstage.service;


import com.github.pagehelper.PageInfo;
import com.upc.fpbackstage.dto.articleInfoDto;
import com.upc.fpbackstage.dto.markedDto;
import com.upc.fpbackstage.po.Articleinfo;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface articleInfoService {
    PageInfo<Articleinfo> findAllPosts(int pageNum, int pageSize);

    PageInfo<Articleinfo> findPostsByParam(articleInfoDto dto,int pageNum, int pageSize);

    int updateLableId(String articleId, int changeType);

    int changePosts(String articleId);

    PageInfo<Articleinfo> focusTrend(articleInfoDto dto,int pageNum,int pageSize);


    int markCountChange(String articleId, int changeType);

    int checkIfMarked(String userId, String articleId);

    int cancleMarked(String userId, String articleId);

    int markTo(String userId, String articleId);


    int likCountChange(String articleId, int changeType);

    int checkIfLiked(String userId, String articleId);

    int cancleLiked(String userId, String articleId);

    int likeTo(String userId, String articleId);



    PageInfo<Articleinfo> findAllBlogs(int pageNum,int pageSize);

    PageInfo<Articleinfo> findBlogsByParam(int pageNum,int pageSize,articleInfoDto dto);

    int BlogCount();

    int PostCount();

    PageInfo<markedDto> findtitle(int pageNum, int pageSize, String userId);


    int publishBlog(articleInfoDto dto);

    articleInfoDto findBlogById(String articleId);

    int commentCount(String articleId);

    int changeCommentCount(String articleId, int changeType);

    int changeViewCount(String articleId);

}
