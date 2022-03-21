package com.upc.fpbackstage.service.Impl;

import com.upc.fpbackstage.mapper.forumFindLabel;
import com.upc.fpbackstage.po.Label;
import com.upc.fpbackstage.service.forumFindLabelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class forumFindLabelServiceImpl implements forumFindLabelService {
    @Resource
    private forumFindLabel forumfindLabel;

    @Override
    public List<Label> findLabel() {
        return forumfindLabel.findLabel();
    }
}
