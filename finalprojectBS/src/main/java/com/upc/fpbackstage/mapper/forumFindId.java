package com.upc.fpbackstage.mapper;

import com.upc.fpbackstage.po.Articleinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface forumFindId {
    @Select("SELECT count(*) from articleinfo where type = 1")
    int findId();
}
