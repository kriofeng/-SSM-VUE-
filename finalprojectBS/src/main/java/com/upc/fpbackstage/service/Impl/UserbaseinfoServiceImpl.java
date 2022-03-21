package com.upc.fpbackstage.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.upc.fpbackstage.dto.UserbaseinfoDto;
import com.upc.fpbackstage.dto.resetPasswordDto;
import com.upc.fpbackstage.mapper.UserbaseinfoMapper;
import com.upc.fpbackstage.po.Resetpassword;
import com.upc.fpbackstage.po.Userbaseinfo;
import com.upc.fpbackstage.result.Result;
import com.upc.fpbackstage.result.ResultFactory;
import com.upc.fpbackstage.service.EmailService;
import com.upc.fpbackstage.service.UserbaseinfoService;
import com.upc.fpbackstage.service.resetPasswordService;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import javax.annotation.Resource;

import java.util.Date;

import java.util.List;


@Service
public class UserbaseinfoServiceImpl implements UserbaseinfoService {

    @Resource
    private EmailService emailService;
    @Resource
    private resetPasswordService rsp;
    @Resource
    private UserbaseinfoMapper userbaseinfoMapper;
    @Override
    public int save(UserbaseinfoDto dto) {
        return userbaseinfoMapper.save(dto);
    }

    @Override
    public Userbaseinfo login(UserbaseinfoDto dto) {
        Userbaseinfo userbaseinfo=null;
        userbaseinfo=userbaseinfoMapper.login(dto);
        return userbaseinfo;
    }

    @Override
    public int checkEmail(String userId, String userEmail) {
        int row = userbaseinfoMapper.checkEmail(userId,userEmail);
        return row;
    }

    @Override
    public Result checkUserAndEmail(UserbaseinfoDto dto) {
        //获取前端传来的数据
        String userId = dto.getUserId();
        String userEmail = dto.getUserEmail();
        //将用户Id中可能存在HTML编码转义
        userId = HtmlUtils.htmlEscape(userId);
        userEmail = HtmlUtils.htmlEscape(userEmail);
        int row = checkEmail(userId,userEmail);
        if(row != 1){
            return ResultFactory.buildFailResult("账号不存在！");
        }
        //生成一个6位的数字
        int num = (int) ((Math.random() * 9 + 1) * 100000);
        String code = String.valueOf(num);
        int isEffective = 1;
        if(rsp.findUserByid(userId) != null )
        {
            Resetpassword rp = rsp.findUserByid(userId);
            String storeEmail = getEmail(userId);
            rp.setIsEffective(isEffective);
            rp.setCode(code);
            try{
                rsp.updateById(rp);
            }catch (Exception e) {
                System.out.println(e);
            }
        }else{
            //表中不存在这个用户，那么需要重新添加一条新数据
            resetPasswordDto rp = new resetPasswordDto();
            rp.setUserId(userId);
            rp.setCode(code);
            rp.setIsEffective(isEffective);
            try{
                rsp.save(rp);
            }catch (Exception e) {
                System.out.println(e);
            }
        }
        //发送html邮件到对应的邮箱号
        String title = "重置密码-来自石油大学论坛后台";
        String content = "<html>\n" +
                "<body>\n" +
                "<h3>hello! 忘记密码啦？!</h3>\n" +
                "<p>" + "用户" + userId + ": 你好"+"<br/>" + "你正在石油大学论坛博客后台进行重置密码操作<br/>" +
                "您本次重置密码的验证码为<br/>" +
                "<p style=\"font-size:24px; color: #409EFF\">" + code + "</p>" +
                "<br/>请在5分钟之内填写验证码"+
                "<br/>如果非本人操作，请忽略本邮件, 如有疑问，欢迎致信1397523118@qq.com" +
                "</P>" +
                "</body>\n" +
                "</html>\n";
        //发送Html邮件时间相对较长
        //发送Html邮件时间相对较长
        emailService.sendHtmlMail(userEmail, title, content);
        return ResultFactory.buildSuccessResult(userId);
    }

    @Override
    public String getEmail(String userId) {
        return userbaseinfoMapper.getEmail(userId);
    }

    @Override
    public Result checkCode(String code, String userId) {
        Resetpassword rp = rsp.findUserByid(userId);
        if (rp.getCode().equals(code)) {
            return ResultFactory.buildSuccessResult("验证码匹配正确", userId);
        }
        return ResultFactory.buildFailResult("验证码匹配错误!");
    }

    @Override
    public Result updatePassword(String userId, String pwd) {
        int row = userbaseinfoMapper.updatePassword(userId, pwd);
        if (row == 1) {
            return ResultFactory.buildSuccessResult("修改成功");
        } else {
            return ResultFactory.buildFailResult("失败！");
        }
    }

    @Override
    public Userbaseinfo findUser(String userId) {
        return userbaseinfoMapper.findUser(userId);
    }

    public PageInfo<Userbaseinfo> findFocus(int pageNum, int pageSize, UserbaseinfoDto dto) {
        PageInfo<Userbaseinfo> page=null;
        PageHelper.startPage(pageNum,pageSize);
        List<Userbaseinfo> userbaseinfos=userbaseinfoMapper.findFocus(dto);
        page=new PageInfo<>(userbaseinfos);
        return page;
    }

    @Override
    public PageInfo<Userbaseinfo> findFollow(int pageNumf, int pageSizef, UserbaseinfoDto dto) {
        PageInfo<Userbaseinfo> page=null;
        PageHelper.startPage(pageNumf,pageSizef);
        List<Userbaseinfo> userbaseinfos=userbaseinfoMapper.findFollow(dto);
        page=new PageInfo<>(userbaseinfos);
        return page;
    }

    @Override
    public int concleFollow(UserbaseinfoDto dto) {
        return userbaseinfoMapper.concleFollow(dto);
    }

    @Override
    public int followTo(UserbaseinfoDto dto) {
        return userbaseinfoMapper.followTo(dto);
    }

    @Override
    public PageInfo<Userbaseinfo> findAll(int pageNum, int pageSize) {
        PageInfo<Userbaseinfo> page = null;
        PageHelper.startPage(pageNum,pageSize);
        List<Userbaseinfo> userinfos = userbaseinfoMapper.findAll();
        page = new PageInfo<>(userinfos);
        return page;
    }

    @Override
    public PageInfo<UserbaseinfoDto> GetAllUserInfo(int pageNum, int pageSize) {
        PageInfo<UserbaseinfoDto> page = null;
        PageHelper.startPage(pageNum, pageSize);
        List<UserbaseinfoDto> infos = userbaseinfoMapper.GetAllUserInfo();
        page = new PageInfo<>(infos);
        return page;
    }

    @Override
    public PageInfo<UserbaseinfoDto> GetAllUserInfoByParam(UserbaseinfoDto dto, int pageNum, int pageSize) {
        PageInfo<UserbaseinfoDto> page = null;
        PageHelper.startPage(pageNum, pageSize);
        List<UserbaseinfoDto> infos = userbaseinfoMapper.GetAllUserInfoByParam(dto);
        page = new PageInfo<>(infos);
        return page;
    }

    @Override
    public int banUser(String userId, int banType) {
        int row = userbaseinfoMapper.banUser(userId, banType);
        return row;
    }

    @Override
    public int getUserCount() {
        return userbaseinfoMapper.getUserCount();
    }

    @Override
    public int cancleuser(String userId) {

        int row=userbaseinfoMapper.cancleuser(userId);
        return row;
    }

    @Override
    public int updateuserinfo(UserbaseinfoDto dto) {
        int row=userbaseinfoMapper.updateuserinfo(dto);
        return row;
    }

    @Override
    public int isFollow(String followerId, String followedId) {
        return userbaseinfoMapper.isFollow(followerId, followedId);
    }

    @Override
    public int addFollow(String followerId, String followedId) {
        return userbaseinfoMapper.addFollow(followerId, followedId);
    }

    @Override
    public int deleteFollow(String followerId, String followedId) {
        return userbaseinfoMapper.deleteFollow(followerId, followedId);
    }


}
