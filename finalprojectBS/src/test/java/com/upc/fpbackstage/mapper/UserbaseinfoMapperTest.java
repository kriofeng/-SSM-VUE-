package com.upc.fpbackstage.mapper;

import com.upc.fpbackstage.dto.UserbaseinfoDto;
import com.upc.fpbackstage.po.Userbaseinfo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserbaseinfoMapperTest {

    @Resource
    private UserbaseinfoMapper userbaseinfoMapper;

    @Test
    @Transactional
    void save() {
        UserbaseinfoDto dto=new UserbaseinfoDto();
        dto.setUserId("XM");
        dto.setUserName("xiaoming");
        dto.setUserEmail("5758qq.com");
        dto.setUserPwd("77777");
        dto.setUserSex(1);
        int row=userbaseinfoMapper.save(dto);
        assertEquals(1,row);
    }

    @Test
    void login() {
        UserbaseinfoDto dto=new UserbaseinfoDto();
        dto.setUserId("1");
        dto.setUserPwd("11");
        Userbaseinfo users=userbaseinfoMapper.login(dto);
    }

    @Test
    void findFocus() {
        UserbaseinfoDto dto=new UserbaseinfoDto();
        dto.setUserId("u001");
        List<Userbaseinfo> users=userbaseinfoMapper.findFollow(dto);
        System.out.println(users);
    }

    @Test
    void getAllUserInfoByParam() {
        UserbaseinfoDto dto = new UserbaseinfoDto();
        dto.setUserId("u001");
        List<UserbaseinfoDto> users = userbaseinfoMapper.GetAllUserInfoByParam(dto);
        System.out.println(users);
    }
}