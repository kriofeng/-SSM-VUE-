package com.upc.fpbackstage.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface chartDataMapper {
    @Select("SELECT a.labelId,IFNULL(c.ct,0) AS cnt\n" +
            "FROM label a\n" +
            "LEFT JOIN (\n" +
            "select b.area,COUNT(*) as ct\n" +
            "from  articleinfo b\n" +
            "where b.type = 1\n" +
            "GROUP BY b.area\n" +
            ") c\n" +
            "on a.labelId = c.area\n")
    List<Map<Object,Object>> typeCntP();

    @Select("SELECT a.labelId,IFNULL(c.ct,0) AS cnt\n" +
            "FROM label a\n" +
            "LEFT JOIN (\n" +
            "select b.area,COUNT(*) as ct\n" +
            "from  articleinfo b\n" +
            "where b.type = 2\n" +
            "GROUP BY b.area\n" +
            ") c\n" +
            "on a.labelId = c.area\n")
    List<Map<Object,Object>> typeCntB();

    @Select("SELECT a.labelId,IFNULL(c.ct,0) AS cnt\n" +
            "             FROM label a \n" +
            "             LEFT JOIN (\n" +
            "             select b.area,COUNT(*) as ct\n" +
            "             from  articleinfo b\n" +
            "             GROUP BY b.area\n" +
            "             ) c\n" +
            "             on a.labelId = c.area")
    List<Map<Object,Object>> typeCnt();

    @Select("SELECT a.userSex ,count(*) as sex\n" +
            "from userbaseinfo a\n" +
            "GROUP BY a.userSex")
    List<Map<Object,Object>> sexCnt();
}
