package com.upc.fpbackstage.mapper;

import com.upc.fpbackstage.dto.adminInfoDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface adminInfoMapper {
    @Select("SELECT COUNT(*) FROM admininfo where adminId = #{adminId} and adminPwd = md5(#{adminPwd})")
    int adminLogin(String adminId, String adminPwd);
}
