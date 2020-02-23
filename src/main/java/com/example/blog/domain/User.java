package com.example.blog.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * zj_users 用户
 */
public class User implements Serializable {
    private static final long serialVersionUID = 7516252653367459390L;
    private int user_id, user_age;
    private String user_ip, user_name, user_password, user_email, user_profile_photo, user_telephone_number;
    private Date user_registration_time, user_birthday;

    public User() {
    }

    public User(String user_email, String user_password) {
        this.user_email = user_email;
        this.user_password = user_password;
    }

    public User(String user_ip, String user_name, String user_password, String user_email, String user_telephone_number, Date user_registration_time) {
        this.user_ip = user_ip;
        this.user_name = user_name;
        this.user_password = user_password;
        this.user_email = user_email;
        this.user_telephone_number = user_telephone_number;
        this.user_registration_time = user_registration_time;
    }

    public User(int user_id, int user_age, String user_telephone_number, String user_ip, String user_name, String user_password, String user_email, String user_profile_photo, Date user_registration_time, Date user_birthday) {
        this.user_id = user_id;
        this.user_age = user_age;
        this.user_telephone_number = user_telephone_number;
        this.user_ip = user_ip;
        this.user_name = user_name;
        this.user_password = user_password;
        this.user_email = user_email;
        this.user_profile_photo = user_profile_photo;
        this.user_registration_time = user_registration_time;
        this.user_birthday = user_birthday;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getUser_age() {
        return user_age;
    }

    public void setUser_age(int user_age) {
        this.user_age = user_age;
    }

    public String getUser_telephone_number() {
        return user_telephone_number;
    }

    public void setUser_telephone_number(String user_telephone_number) {
        this.user_telephone_number = user_telephone_number;
    }

    public String getUser_ip() {
        return user_ip;
    }

    public void setUser_ip(String user_ip) {
        this.user_ip = user_ip;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_profile_photo() {
        return user_profile_photo;
    }

    public void setUser_profile_photo(String user_profile_photo) {
        this.user_profile_photo = user_profile_photo;
    }

    public Date getUser_registration_time() {
        return user_registration_time;
    }

    public void setUser_registration_time(Date user_registration_time) {
        this.user_registration_time = user_registration_time;
    }

    public Date getUser_birthday() {
        return user_birthday;
    }

    public void setUser_birthday(Date user_birthday) {
        this.user_birthday = user_birthday;
    }
}
