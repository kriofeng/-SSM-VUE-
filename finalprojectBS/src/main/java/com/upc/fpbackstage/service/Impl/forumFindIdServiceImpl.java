package com.upc.fpbackstage.service.Impl;

import com.upc.fpbackstage.mapper.forumFindId;
import com.upc.fpbackstage.po.Articleinfo;
import com.upc.fpbackstage.service.forumFindIdService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class forumFindIdServiceImpl implements forumFindIdService {
    @Resource
    private forumFindId forumfindId;
    @Override
    public int findId() {
        return forumfindId.findId();
    }
}
