package com.example.blog.domain;

import java.io.Serializable;

public class Mail implements Serializable {
    private static final long serialVersionUID = -3831618394717686109L;
    private int uid;
    private String mailcheckCode, time;

    public Mail() {
    }

    public Mail(int uid, String mailcheckCodetime, String time) {
        this.uid = uid;
        this.mailcheckCode = mailcheckCodetime;
        this.time = time;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getMailcheckCode() {
        return mailcheckCode;
    }

    public void setMailcheckCode(String mailcheckCode) {
        this.mailcheckCode = mailcheckCode;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
