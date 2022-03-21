package com.upc.fpbackstage.service.Impl;

import com.upc.fpbackstage.dto.resetPasswordDto;
import com.upc.fpbackstage.mapper.resetPasswordMapper;
import com.upc.fpbackstage.po.Resetpassword;
import com.upc.fpbackstage.service.resetPasswordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class resetPasswordServiceImpl implements resetPasswordService {
    @Resource
    resetPasswordMapper rsmapper;

    @Override
    public Resetpassword findUserByid(String userId) {
        Resetpassword user = rsmapper.findUserByid(userId);
        return user;
    }

    @Override
    public int save(Resetpassword dto) {
        int row = rsmapper.save(dto);
        return row;
    }

    @Override
    public int updateById(Resetpassword dto) {
        int row = rsmapper.updateById(dto);
        return row;
    }
}
