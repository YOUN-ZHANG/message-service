package com.fijo.fileservice.enums.SysEnum;

import lombok.Getter;

@Getter
public enum ResultEnum {
    RESULT_CODE_SUCCESS(1,"success"),
    RESULT_CODE_ERROR(0,"error")
    ;

    private int code;
    private String msg;

    ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
