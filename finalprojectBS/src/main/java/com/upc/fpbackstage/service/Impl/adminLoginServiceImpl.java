package com.upc.fpbackstage.service.Impl;

import com.upc.fpbackstage.dto.adminInfoDto;
import com.upc.fpbackstage.mapper.adminInfoMapper;
import com.upc.fpbackstage.service.adminLoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class adminLoginServiceImpl implements adminLoginService {
    @Resource
    private adminInfoMapper adminMapper;

    @Override
    public int adminLogin(String adminId, String adminPwd) {
        return adminMapper.adminLogin(adminId,adminPwd);

    }
}
