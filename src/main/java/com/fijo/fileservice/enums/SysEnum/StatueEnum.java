package com.fijo.fileservice.enums.SysEnum;

import lombok.Getter;

@Getter
public enum StatueEnum {
    RESULT_CODE_SUCCESS("1","success"),
    RESULT_CODE_ERROR("0","error"),
    LOGIN_SUCCESS("1","登录成功"),
    STATE_TRUE("1","正常"),
    STATE_FALSE("0","弃用")
    ;

    private String code;
    private String msg;

    StatueEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
