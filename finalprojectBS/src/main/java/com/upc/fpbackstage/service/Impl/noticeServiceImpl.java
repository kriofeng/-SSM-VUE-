package com.upc.fpbackstage.service.Impl;

import com.upc.fpbackstage.dto.noticeDto;
import com.upc.fpbackstage.mapper.noticeMapper;
import com.upc.fpbackstage.service.noticeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class noticeServiceImpl implements noticeService {
    @Resource
    private noticeMapper mp;


    @Override
    public int addNotice(noticeDto dto) {
        return mp.addNotice(dto);
    }

    @Override
    public List<noticeDto> findAllNotice() {
        return mp.findAllNotice();
    }

    @Override
    public noticeDto findNewNotice() {
        return mp.findNewNotice();
    }

    @Override
    public int deleteNotice(String noticeId) {
        return mp.deleteNotice(noticeId);
    }

    @Override
    public int noticeCount() {
        return mp.noticeCount();
    }
}
