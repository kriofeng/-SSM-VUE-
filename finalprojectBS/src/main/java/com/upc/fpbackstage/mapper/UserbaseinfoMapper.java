package com.upc.fpbackstage.mapper;

import com.upc.fpbackstage.dto.UserbaseinfoDto;
import com.upc.fpbackstage.po.Userbaseinfo;
import org.apache.ibatis.annotations.*;

import java.util.Map;

import java.util.List;

@Mapper
public interface UserbaseinfoMapper {
    int save(UserbaseinfoDto dto);

    Userbaseinfo login(UserbaseinfoDto dto);


    @Select("select COUNT(*) from userbaseinfo where userId = #{userId} and userEmail = #{userEmail}")
    int checkEmail(String userId, String userEmail);

    @Select("SELECT userEmail from userbaseinfo where userId = #{userId}")
    String getEmail(String userId);

    @Update("UPDATE userbaseinfo set userPwd = MD5(#{pwd}) where userId = #{userId}")
    int updatePassword(String userId, String pwd);

    List<Userbaseinfo> findFocus(UserbaseinfoDto dto);
    List<Userbaseinfo> findFollow(UserbaseinfoDto dto);

    @Select("select * from userbaseinfo where userId = #{userId}")
    Userbaseinfo findUser(String userId);

    int concleFollow(UserbaseinfoDto dto);//取关
    int followTo(UserbaseinfoDto dto);//回关

    @Select("select * from userbaseinfo")
    List<Userbaseinfo> findAll();

    List<UserbaseinfoDto> GetAllUserInfo();

    List<UserbaseinfoDto> GetAllUserInfoByParam(UserbaseinfoDto dto);

    @Update("UPDATE userbaseinfo set userStatus = #{banType} where userId = #{userId}")
    int banUser(String userId, int banType);

    @Select("Select Count(*) as cnt from userbaseinfo")
    int getUserCount();

    //注销用户
    @Update("UPDATE userbaseinfo set userStatus = 0 where userId = #{userId}")
    int cancleuser(String userId);

    //修改用户信息
    int updateuserinfo(UserbaseinfoDto dto);

    //是否关注

    @Select("select count(*) from userfollow where followedId = #{followedId} " +
            "and followerId = #{followerId}")
    int isFollow(String followerId, String followedId);

    @Insert("insert into userfollow(followedId, followerId) values(#{followedId},#{followerId})")
    int addFollow(String followerId, String followedId);

    @Delete("delete from userfollow where followerId = #{followerId} and followedId = #{followedId}")
    int deleteFollow(String followerId, String followedId);

}
