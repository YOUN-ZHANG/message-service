package com.fijo.fileservice.enums.SysEnum;

import lombok.Getter;

@Getter
public enum  FileEnum {
    maxLength(1000,"mp5"),
    allowExtName(1000,"mp4")
    ;
    private int maxLengths;
    private String allowExtNames;
    FileEnum(int maxLengths, String allowExtNames) {
        this.maxLengths = maxLengths;
        this.allowExtNames = allowExtNames;
    }
}
