package com.lyc.yl.result;

/**
 * 返回值对象的封装
 */
public class ResponseData {

    private String code;

    private String info;

    private Object data;

    public ResponseData(String code, String info, Object data) {
        this.code = code;
        this.info = info;
        this.data = data;
    }

    public ResponseData(ResponseCode responseCode, Object data) {
        this.code = responseCode.getCode();
        this.info = responseCode.getInfo();
        this.data = data;
    }

    public ResponseData(ResponseCode responseCode) {
        this.code = responseCode.getCode();
        this.info = responseCode.getInfo();
    }

    public ResponseData(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public ResponseData() {
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
