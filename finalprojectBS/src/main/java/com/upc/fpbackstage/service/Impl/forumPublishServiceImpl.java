package com.upc.fpbackstage.service.Impl;

import com.upc.fpbackstage.dto.articleInfoDto;
import com.upc.fpbackstage.mapper.forumPublishMapper;
import com.upc.fpbackstage.service.forumPublishService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class forumPublishServiceImpl implements forumPublishService {
    @Resource
    private forumPublishMapper forumpublishMapper;
    @Override
    public int forumPublish(articleInfoDto dto){
        return forumpublishMapper.forumPublish(dto);
    }
}
