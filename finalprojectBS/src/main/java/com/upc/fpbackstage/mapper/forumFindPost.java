package com.upc.fpbackstage.mapper;

import com.upc.fpbackstage.dto.articleInfoDto;
import com.upc.fpbackstage.dto.commentDto;
import com.upc.fpbackstage.po.Articleinfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface forumFindPost {
    List<Articleinfo> findPost(articleInfoDto dto);
    List<Articleinfo> findPostByHeat(articleInfoDto dto);
    List<Articleinfo> findPostByGood(articleInfoDto dto);
    Articleinfo findPostById(articleInfoDto dto);


}
