package com.upc.fpbackstage.service;

import com.github.pagehelper.PageInfo;
import com.upc.fpbackstage.dto.UserbaseinfoDto;
import com.upc.fpbackstage.po.Userbaseinfo;
import com.upc.fpbackstage.result.Result;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

public interface UserbaseinfoService {
    int save(UserbaseinfoDto dto);

    Userbaseinfo login(UserbaseinfoDto dto);

    int checkEmail(String userId, String userEmail);

    Result checkUserAndEmail(UserbaseinfoDto dto);

    String getEmail(String userId);

    Result checkCode(String code, String userId);

    Result updatePassword(String userId, String pwd);

    Userbaseinfo findUser(String userId);

    PageInfo<Userbaseinfo> findFocus(int pageNum,int pageSize,UserbaseinfoDto dto);
    PageInfo<Userbaseinfo> findFollow(int pageNumf,int pageSizef,UserbaseinfoDto dto);

    int concleFollow(UserbaseinfoDto dto);
    int followTo(UserbaseinfoDto dto);

    PageInfo<Userbaseinfo> findAll(int pageNum, int pageSize);

    PageInfo<UserbaseinfoDto> GetAllUserInfo(int pageNum, int pageSize);

    PageInfo<UserbaseinfoDto> GetAllUserInfoByParam(UserbaseinfoDto dto,int pageNum, int pageSize);

    int banUser(String userId, int banType);

    int getUserCount();

    int cancleuser(String userId);

    int updateuserinfo(UserbaseinfoDto dto);

    int isFollow(String followerId, String followedId);


    int addFollow(String followerId, String followedId);


    int deleteFollow(String followerId, String followedId);

}
