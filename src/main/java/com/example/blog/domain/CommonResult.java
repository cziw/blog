package com.example.blog.domain;

import java.io.Serializable;

/**
 * 自定义返回值
 */
public class CommonResult implements Serializable {
    private static final long serialVersionUID = -8251193727481843651L;
    private int status;
    private Object object;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public CommonResult(int status, Object object) {
        this.status = status;
        this.object = object;
    }

    public CommonResult(int status, Object object, String msg) {
        this.status = status;
        this.object = object;
        this.msg = msg;
    }

    public CommonResult() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
