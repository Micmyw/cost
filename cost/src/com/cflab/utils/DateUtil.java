package com.cflab.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class DateUtil {
    /**
     * 将yyyy-MM-dd HH:mm:ss类型的字符串转换时间日期类型
     * @param dataStr 时间字符串
     * @return 时间类型
     */
    public static Date Str2date(String dataStr){
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return sdf.parse(dataStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据传入的时间字符串格式转换为时间日期类型
     * @param dataStr 时间字符串
     * @param pattern 时间格式
     * @return 时间类型
     */
    public static Date Str2date(String dataStr ,String pattern){
        SimpleDateFormat sdf= new SimpleDateFormat(pattern);
        try {
            return sdf.parse(dataStr);
        } catch (ParseException e) {
       //     e.printStackTrace();
        }
        return null;
    }

    /**
     * 将时间类型转换成yyyy-MM-dd HH:mm:ss格式的字符串
     * @param date 时间日期
     * @return 时间字符串
     */
    public static String Date2str(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    /**
     * 根据传入的格式，转换为相应的时间类型字符串
     * @param date 时间日期
     * @param pattern 日期格式
     * @return 时间字符串
     */
    public static String Date2str(Date date,String pattern){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }
}
