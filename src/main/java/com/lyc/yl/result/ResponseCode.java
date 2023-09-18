package com.lyc.yl.result;

/**
 * 封装枚举变量
 */
public enum ResponseCode {
    SUCCESS("200","OK"),
    FAILED("500","系统异常"),
    SQL_EXCEPTION("501","sql异常")
    ;

    private String code;

    private String info;

    ResponseCode(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
