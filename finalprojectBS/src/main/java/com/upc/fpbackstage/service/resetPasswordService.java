package com.upc.fpbackstage.service;

import com.upc.fpbackstage.dto.resetPasswordDto;
import com.upc.fpbackstage.po.Resetpassword;


public interface resetPasswordService {
    Resetpassword findUserByid(String userId);

    int save(Resetpassword dto);

    int updateById(Resetpassword dto);

}
