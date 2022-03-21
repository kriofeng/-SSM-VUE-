package com.upc.fpbackstage.mapper;

import com.upc.fpbackstage.dto.articleInfoDto;
import com.upc.fpbackstage.po.Articleinfo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class articleInfoMapperTest {
    @Resource
    private articleInfoMapper articleInfoMapper;

    @Test
    void findAllPosts() {
        List<Articleinfo> pList = articleInfoMapper.findAllPosts();
        pList.forEach(s->{
            System.out.println(s.getArticleId());
        });
    }

    @Test
    void focusTrend() {
        articleInfoDto dto=new articleInfoDto();
        dto.setFollowerId("u002");
        List<Articleinfo> pList = articleInfoMapper.focusTrend(dto);
        pList.forEach(s->{
            System.out.println(s.getTitle());
        });
    }
}