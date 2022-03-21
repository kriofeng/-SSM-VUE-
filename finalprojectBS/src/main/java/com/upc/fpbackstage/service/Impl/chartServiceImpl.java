package com.upc.fpbackstage.service.Impl;

import com.upc.fpbackstage.mapper.chartDataMapper;
import com.upc.fpbackstage.service.chartService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class chartServiceImpl implements chartService {

    @Resource
    chartDataMapper cdm;

    @Override
    public List<Map<Object, Object>> typeCntP() {
        return cdm.typeCntP();
    }

    @Override
    public List<Map<Object, Object>> typeCntB() {
        return cdm.typeCntB();
    }

    @Override
    public List<Map<Object, Object>> typeCnt() {
        return cdm.typeCnt();
    }

    @Override
    public List<Map<Object, Object>> sexCnt() {
        return cdm.sexCnt();
    }
}
