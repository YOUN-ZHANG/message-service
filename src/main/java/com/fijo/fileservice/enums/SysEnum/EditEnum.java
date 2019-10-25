package com.fijo.fileservice.enums.SysEnum;

import lombok.Getter;

@Getter
public enum EditEnum {
    DELETE_SUCCESS("SUCCESS","删除成功"),
    DELETE_FAIL("FAIL","删除失败")
    ;

    private String code;
    private String msg;

    EditEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
