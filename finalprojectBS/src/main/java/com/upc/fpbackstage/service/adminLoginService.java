package com.upc.fpbackstage.service;

import com.upc.fpbackstage.dto.adminInfoDto;

public interface adminLoginService {
    int adminLogin(String adminId, String adminPwd);
}
