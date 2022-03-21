package com.upc.fpbackstage.util;

import java.text.SimpleDateFormat;
import java.util.Date;

//转换时间的工具类
public class DateConvent {

    public String toString(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String time = simpleDateFormat.format(date);
        return time;
    }

}

