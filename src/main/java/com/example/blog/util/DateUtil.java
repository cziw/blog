package com.example.blog.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 */
public class DateUtil {

    /**
     * 计算用户注册用了多久
     * @return
     */
    public String registerTime(){
        // 获取当前时间
        Long l = new Date().getTime();
        // 十分钟之后的时间戳
        Long ll = new Date().getTime() + 600000;
        SimpleDateFormat sdf=new SimpleDateFormat("mm:ss");
        // 相差多少秒
        String sd = sdf.format(new Date(Long.parseLong(String.valueOf(ll - l))));
        return sd;
    }

}
