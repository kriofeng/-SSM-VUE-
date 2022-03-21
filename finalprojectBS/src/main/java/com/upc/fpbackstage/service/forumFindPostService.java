package com.upc.fpbackstage.service;

import com.github.pagehelper.PageInfo;
import com.upc.fpbackstage.dto.articleInfoDto;
import com.upc.fpbackstage.po.Articleinfo;

public interface forumFindPostService {
    PageInfo<Articleinfo> findPost(int pageNum, int pageSize, articleInfoDto dto);
    PageInfo<Articleinfo> findPostByHeat(int pageNum, int pageSize, articleInfoDto dto);
    PageInfo<Articleinfo> findPostByGood(int pageNum, int pageSize, articleInfoDto dto);
    Articleinfo findPostById( articleInfoDto dto);
}
