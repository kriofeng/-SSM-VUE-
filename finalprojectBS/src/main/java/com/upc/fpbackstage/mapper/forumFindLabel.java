package com.upc.fpbackstage.mapper;

import com.upc.fpbackstage.po.Label;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface forumFindLabel {
    @Select("select * from label")
    List<Label> findLabel();
}
