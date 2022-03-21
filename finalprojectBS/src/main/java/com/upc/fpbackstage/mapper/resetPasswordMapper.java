package com.upc.fpbackstage.mapper;

import com.upc.fpbackstage.dto.resetPasswordDto;
import com.upc.fpbackstage.po.Resetpassword;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface resetPasswordMapper {
    @Select("SELECT * FROM resetpassword where userId = #{userId}")
    Resetpassword findUserByid(String userId);

    @Insert("INSERT INTO resetpassword(userId,code,isEffective) VALUES(#{userId},#{code},#{isEffective})")
    int save(Resetpassword dto);

    @Update("UPDATE resetpassword set code=#{code},isEffective=#{isEffective} where userId=#{userId}")
    int updateById(Resetpassword dto);

}
