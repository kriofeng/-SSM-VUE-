package com.upc.fpbackstage.mapper;

import com.upc.fpbackstage.dto.noticeDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface noticeMapper {
    @Insert("insert into notice(noticeId,noticeTitle,noticeContent,date) " +
            "value(#{noticeId},#{noticeTitle},#{noticeContent},#{date})")
    int addNotice(noticeDto dto);

    @Select("Select * from notice Order by date")
    List<noticeDto> findAllNotice();

    @Select("Select * from notice Order by date desc limit 1")
    noticeDto findNewNotice();

    @Delete("delete from notice where noticeId = #{noticeId}")
    int deleteNotice(String noticeId);

    @Select("Select count(*) from notice")
    int noticeCount();
}
