package com.upc.fpbackstage.controller;

import com.github.pagehelper.PageInfo;
import com.upc.fpbackstage.dto.UserbaseinfoDto;
import com.upc.fpbackstage.mapper.UserbaseinfoMapper;
import com.upc.fpbackstage.po.Userbaseinfo;
import com.upc.fpbackstage.service.UserbaseinfoService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserbaseinfoController {
    @Resource
    private UserbaseinfoService userbaseinfoService;
    @RequestMapping("/findAll")
    public PageInfo<Userbaseinfo> findAll(int pageNum, int pageSize)
    {
        return userbaseinfoService.findAll(pageNum, pageSize);
    }

    @RequestMapping("/reg")
    public int save(UserbaseinfoDto dto){
        return userbaseinfoService.save(dto);
    }

    @RequestMapping("/login")
    public Userbaseinfo login(UserbaseinfoDto dto){
        Userbaseinfo userbaseinfo=null;
        userbaseinfo=userbaseinfoService.login(dto);
        return userbaseinfo;
    }


    @RequestMapping("/checkEmail")
    public int checkEmail(String userId, String userEmail)
    {
        int row = userbaseinfoService.checkEmail(userId, userEmail);
        return row;}

    @RequestMapping("/findFocus")
    public PageInfo<Userbaseinfo> findFocus(int pageNum,int pageSize,UserbaseinfoDto dto){
        PageInfo<Userbaseinfo> page=null;
        page=userbaseinfoService.findFocus(pageNum,pageSize,dto);
        return page;
    }

    @RequestMapping("/findFollow")
    public PageInfo<Userbaseinfo> findFollow(int pageNumf,int pageSizef,UserbaseinfoDto dto){
        PageInfo<Userbaseinfo> page=null;
        page=userbaseinfoService.findFollow(pageNumf,pageSizef,dto);
        return page;
    }

    @RequestMapping("/GetUserInfo")
    public PageInfo<UserbaseinfoDto> GetUserInfo(int pageNum, int pageSize)
    {
        PageInfo<UserbaseinfoDto> page = null;
        page = userbaseinfoService.GetAllUserInfo(pageNum, pageSize);
        return page;
    }

    @RequestMapping("/GetUserInfoByParam")
    public PageInfo<UserbaseinfoDto> GetUserInfoByParam(UserbaseinfoDto dto,int pageNum, int pageSize)
    {
        PageInfo<UserbaseinfoDto> page = null;
        page = userbaseinfoService.GetAllUserInfoByParam(dto,pageNum, pageSize);
        return page;
    }

    @RequestMapping("/banUser")
    public int banUser(String userId, int banType)
    {
        return userbaseinfoService.banUser(userId,banType);
    }

    @RequestMapping("/Count")
    public int Count(){
        return userbaseinfoService.getUserCount();
    }

    @RequestMapping("/concleFollow")
    public int concleFollow(UserbaseinfoDto dto){
        return userbaseinfoService.concleFollow(dto);
    }

    @RequestMapping("/followTo")
    public int followTo(UserbaseinfoDto dto){
        return userbaseinfoService.followTo(dto);
    }

    @RequestMapping("findUser")
    public Userbaseinfo findUer(String userId){
        return userbaseinfoService.findUser(userId);
    }

    @RequestMapping("/cancleuser")
    public int cancleuser(String userId){
        return userbaseinfoService.cancleuser(userId);
    }

    @RequestMapping("/updateuserinfo")
    public int updateuserinfo(UserbaseinfoDto dto){
        return userbaseinfoService.updateuserinfo(dto);
    }

    @RequestMapping("/isFollow")
    public int isFollow(String followerId, String followedId)
    {
        return userbaseinfoService.isFollow(followerId, followedId);
    }

    @RequestMapping("addFollow")
    public int addFollow(String followerId, String followedId) {
        return userbaseinfoService.addFollow(followerId, followedId);
    }

    @RequestMapping("deleteFollow")
    public int deleteFollow(String followerId, String followedId) {
        return userbaseinfoService.deleteFollow(followerId, followedId);
    }
}
