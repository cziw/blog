package com.example.blog.domain;

import java.io.Serializable;

public class Mail implements Serializable {
    private static final long serialVersionUID = -3831618394717686109L;
    private String email, mailcheckCode, time;

    public Mail() {
    }

    @Override
    public String toString() {
        return "Mail{" +
                "email='" + email + '\'' +
                ", mailcheckCode='" + mailcheckCode + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public Mail(String email, String mailcheckCode, String time) {
        this.email = email;
        this.mailcheckCode = mailcheckCode;
        this.time = time;
    }

    public String getMail() {
        return email;
    }

    public void setMail(String mail) {
        this.email = mail;
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
