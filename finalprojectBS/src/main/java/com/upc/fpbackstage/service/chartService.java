package com.upc.fpbackstage.service;

import java.util.List;
import java.util.Map;

public interface chartService {
    List<Map<Object,Object>> typeCntP();

    List<Map<Object,Object>> typeCntB();

    List<Map<Object,Object>> typeCnt();

    List<Map<Object,Object>> sexCnt();
}
