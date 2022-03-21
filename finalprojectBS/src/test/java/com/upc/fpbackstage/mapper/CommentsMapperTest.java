package com.upc.fpbackstage.mapper;

import com.upc.fpbackstage.dto.commentDto;
import com.upc.fpbackstage.po.Comments;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentsMapperTest {
    @Resource
    private CommentsMapper cm;

    @Test
    void findAllComments() {
        List<commentDto> list = null;
        String articleId = "b5";
        list = cm.findAllComments(articleId);
        list.forEach(s->{
            System.out.println(s.getContent());
        });
    }
}