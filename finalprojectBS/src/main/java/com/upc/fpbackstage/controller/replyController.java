package com.upc.fpbackstage.controller;

import com.upc.fpbackstage.dto.replyDto;
import com.upc.fpbackstage.service.replyService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/reply")
public class replyController {
    @Resource
    private replyService sv;

    @RequestMapping("/findReply")
    public List<replyDto> findReply(String commentId){
        return sv.findAllReply(commentId);
    }

    @RequestMapping("/addReply")
    public int addReply(replyDto dto){
        return sv.addReply(dto);
    }


}
