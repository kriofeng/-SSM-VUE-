package com.upc.fpbackstage.mapper;

import com.upc.fpbackstage.dto.articleInfoDto;
import com.upc.fpbackstage.po.Articleinfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface blogFindPost {
    List<Articleinfo> findBlogPost(articleInfoDto dto);
    List<Articleinfo> findHotUser();
    List<Articleinfo> findHotPost();
    List<Articleinfo> findMyBlog(articleInfoDto dto);
}
