package com.upc.fpbackstage.controller;


import com.upc.fpbackstage.dto.noticeDto;
import com.upc.fpbackstage.service.noticeService;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/notice")
public class noticeController {
    @Resource
    private noticeService ns;

    @RequestMapping("/addNotice")
    int addNotice(noticeDto dto){
        return ns.addNotice(dto);
    }

    @RequestMapping("findAllNotice")
    List<noticeDto> findAllNotice(){
        return ns.findAllNotice();
    }

    @RequestMapping("/findNewNotice")
    noticeDto findNewNotice(){
        return ns.findNewNotice();
    }


    @RequestMapping("/deleteNotice")
    int deleteNotice(String noticeId){
        return ns.deleteNotice(noticeId);
    }

    @RequestMapping("/noticeCount")
    int noticeCount(){
        return ns.noticeCount();
    }
}
