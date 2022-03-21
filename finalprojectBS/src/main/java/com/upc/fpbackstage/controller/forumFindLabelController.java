package com.upc.fpbackstage.controller;

import com.upc.fpbackstage.po.Label;
import com.upc.fpbackstage.service.forumFindLabelService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/AllLabel")
public class forumFindLabelController {
    @Resource
    private forumFindLabelService forumfindLabelService;
    @RequestMapping("/findLabel")
    List<Label> findLabel(){
        return forumfindLabelService.findLabel();
    }

}
