package com.upc.fpbackstage.controller;

import com.upc.fpbackstage.dto.adminInfoDto;
import com.upc.fpbackstage.service.adminLoginService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@CrossOrigin
@RestController
@RequestMapping("/admin")
public class adminLoginController {
    @Resource
    private adminLoginService adminService;

    @RequestMapping("/login")
    public int login(String adminId, String adminPwd){
        return adminService.adminLogin(adminId, adminPwd);
    }

}
