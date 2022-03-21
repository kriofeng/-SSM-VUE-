package com.upc.fpbackstage.result;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@Data
@Component
@NoArgsConstructor
public class Result {
    /**
     * 响应码，结果信息，数据
     */
    private int code;
    private String message;
    private Object result;

    Result(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.result = data;
    }

}
