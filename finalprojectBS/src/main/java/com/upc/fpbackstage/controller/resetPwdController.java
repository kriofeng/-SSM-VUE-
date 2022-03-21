package com.upc.fpbackstage.controller;

import com.upc.fpbackstage.dto.UserbaseinfoDto;
import com.upc.fpbackstage.result.Result;
import com.upc.fpbackstage.result.ResultFactory;
import com.upc.fpbackstage.service.UserbaseinfoService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@CrossOrigin
@RestController
@RequestMapping("/reset")
public class resetPwdController {
    @Resource
    private UserbaseinfoService uservice;

    @RequestMapping("/resetPwd")
    public Result resetPwd(UserbaseinfoDto dto){
        if(dto.getUserId()!=null && dto.getUserEmail()!=null){
            return uservice.checkUserAndEmail(dto);
        }else if(dto.getCode()!=null && dto.getUserId()!=null){
            return uservice.checkCode(dto.getCode(),dto.getUserId());
        }else if(dto.getUserId() != null && dto.getUserPwd()!=null){
            return uservice.updatePassword(dto.getUserId(),dto.getUserPwd());
        }
        return ResultFactory.buildFailResult("错误！");
    }

}
