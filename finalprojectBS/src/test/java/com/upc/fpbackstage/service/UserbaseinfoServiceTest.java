package com.upc.fpbackstage.service;

import com.github.pagehelper.PageInfo;
import com.upc.fpbackstage.dto.UserbaseinfoDto;
import com.upc.fpbackstage.po.Userbaseinfo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserbaseinfoServiceTest {

    @Resource
    private UserbaseinfoService userbaseinfoService;
    @Test
    void findFocus() {
        UserbaseinfoDto dto=new UserbaseinfoDto();
        dto.setFollowedId("44");
        PageInfo<Userbaseinfo> page=userbaseinfoService.findFocus(1,3,dto);
        System.out.println(page);
    }

    @Test
    void findFollow() {
        UserbaseinfoDto dto=new UserbaseinfoDto();
        dto.setFollowedId("55");
        PageInfo<Userbaseinfo> page=userbaseinfoService.findFollow(1,3,dto);
        System.out.println(page);
    }

    @Test
    void concleFollow() {
        UserbaseinfoDto dto=new UserbaseinfoDto();
        dto.setUserId("44");
        dto.setFollowerId("5");
        int row=userbaseinfoService.concleFollow(dto);
        System.out.println(row);
    }

    @Test
    void returnFollow() {
        UserbaseinfoDto dto=new UserbaseinfoDto();
        dto.setUserId("44");
        dto.setFollowerId("5");
        int row=userbaseinfoService.followTo(dto);
        System.out.println(row);
    }
}