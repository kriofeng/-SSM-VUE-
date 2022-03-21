package com.upc.fpbackstage.controller;

import com.upc.fpbackstage.service.chartService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/chart")
public class chartController {

    @Resource
    chartService cs;

    @RequestMapping("/typecntP")
    List<Map<Object,Object>> typecntP(){
        return cs.typeCntP();
    }

    @RequestMapping("/typecntB")
    List<Map<Object,Object>> typecntB(){
        return cs.typeCntB();
    }

    @RequestMapping("/typecnt")
    List<Map<Object,Object>> typecnt(){
        return cs.typeCnt();
    }

    @RequestMapping("/sexCnt")
    List<Map<Object,Object>> sexCnt(){
        return cs.sexCnt();
    }
}
