package com.upc.fpbackstage.mapper;

import com.upc.fpbackstage.dto.articleInfoDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface forumPublishMapper {

    @Insert("insert into articleinfo ( userId, type, title, content, date, articleId,commentCount,markedCount,likedCount,views,area) " +
            "values (#{userId},1,#{title},#{content},#{date},#{articleId},0,0,0,0,#{area})")

    int forumPublish(articleInfoDto dto);
}
