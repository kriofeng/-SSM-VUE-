package com.upc.fpbackstage.mapper;

import com.upc.fpbackstage.dto.UserbaseinfoDto;
import com.upc.fpbackstage.dto.articleInfoDto;
import com.upc.fpbackstage.dto.markedDto;
import com.upc.fpbackstage.po.Articleinfo;
import com.upc.fpbackstage.service.articleInfoService;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Mapper
public interface articleInfoMapper {
    List<Articleinfo> findAllPosts();
    List<Articleinfo> findPostsByParam(articleInfoDto dto);

    int updateLableId(String articleId, int changeType);

    @Update("UPDATE articleinfo set articleStatus = 0 where articleId = #{articleId}")
    int ChangePosts(String articleId);

    List<Articleinfo> findAllBlogs();

    List<Articleinfo> findBlogsByParam(articleInfoDto dto);

    List<Articleinfo> focusTrend(articleInfoDto dto);

    @Select("select COUNT(*) from articleinfo where type = 1")
    int getPostCount();

    @Select("select COUNT(*) from articleinfo where type = 2")
    int getBlogCount();

    @Select("select a.articleId,c.userName,b.title,b.type\n" +
            "from marked a, articleinfo b, userbaseinfo c \n" +
            "where a.articleId = b.articleId and b.userId = c.userId and a.userId = #{userId}")
    List<markedDto> findtitle(String userId);


    @Insert("Insert into marked(userId,articleId) VALUES(#{userId},#{articleId})")
    int markTo(String userId, String articleId);

    @Delete("Delete from marked where userId = #{userId} and articleId = #{articleId}")
    int cancelMarked(String userId, String articleId);

    @Update("UPDATE articleinfo SET markedCount = markedCount + #{changeType} " +
            "where articleId = articleId")
    int markCountChange(String articleId, int changeType);

    @Select("SELECT COUNT(*) FROM marked WHERE userId = #{userId} and articleId = #{articleId}")
    int checkIfMarked(String userId, String articleId);


    @Insert("Insert into liked(userId,likeId) VALUES(#{userId},#{articleId})")
    int likeTo(String userId, String articleId);

    @Delete("Delete from liked where userId = #{userId} and likeId = #{articleId}")
    int cancelLiked(String userId, String articleId);

    @Update("UPDATE articleinfo SET likedCount =likedCount + #{changeType} " +
            "where articleId = articleId")
    int likeCountChange(String articleId, int changeType);

    @Select("SELECT COUNT(*) FROM liked WHERE userId = #{userId} and likeId = #{articleId}")
    int checkIfLiked(String userId, String articleId);




    @Insert("INSERT INTO articleinfo(articleId,userId,type,title,content,area,date)\n"+
            "VALUES(#{articleId},#{userId},#{type},#{title},#{content},#{area},#{date})")
    int publishBlog(articleInfoDto dto);

    @Select("SELECT a.*,b.userName,b.headPortrait FROM articleinfo a, userbaseinfo b " +
            "where a.articleId = #{articleId} and " +
            "a.userId = b.userId")
    articleInfoDto findBlogById(String articleId);


    @Select("select commentCount from articleinfo where articleId = #{articleId}")
    int commentCount(String articleId);

    @Update("update articleinfo set commentCount = commentCount + #{changeType} " +
            "where articleId = #{articleId}")
    int changeCommentCount(String articleId, int changeType);

    @Update("update articleinfo set views = views + 1 where articleId = #{articleId}")
    int changeViewCount(String articleId);
}
