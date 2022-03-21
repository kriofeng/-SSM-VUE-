package com.upc.fpbackstage.service;

import com.github.pagehelper.PageInfo;
import com.upc.fpbackstage.dto.articleInfoDto;
import com.upc.fpbackstage.po.Articleinfo;

import java.util.List;

public interface blogFindPostService {
    PageInfo<Articleinfo> findPost(int pageNum, int pageSize, articleInfoDto dto);
    List<Articleinfo> findHotUser();
    List<Articleinfo> findHotPost();
    PageInfo<Articleinfo> findMyBlog(int pageNum2,int pageSize2,articleInfoDto dto);
}
