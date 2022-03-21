package com.upc.fpbackstage.service;

import com.upc.fpbackstage.dto.noticeDto;

import java.util.List;

public interface noticeService {

    int addNotice(noticeDto dto);

    List<noticeDto> findAllNotice();

    noticeDto findNewNotice();

    int deleteNotice(String noticeId);

    int noticeCount();
}
