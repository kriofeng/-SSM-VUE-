package com.upc.fpbackstage.service;

import com.github.pagehelper.PageInfo;
import com.upc.fpbackstage.dto.UserbaseinfoDto;
import com.upc.fpbackstage.dto.articleInfoDto;
import com.upc.fpbackstage.po.Articleinfo;
import com.upc.fpbackstage.po.Userbaseinfo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class articleInfoServiceTest {

    @Resource
    private articleInfoService articleInfoService;
    @Test
    void findAllBlogs() {
        PageInfo<Articleinfo> page=articleInfoService.findAllBlogs(1,3);
        System.out.println(page);
    }

    @Test
    void findBlogsByParam() {
        articleInfoDto dto=new articleInfoDto();
        dto.setUserId("u001");
        PageInfo<Articleinfo> page=articleInfoService.findBlogsByParam(1,5,dto);
        System.out.println(page);
    }
}