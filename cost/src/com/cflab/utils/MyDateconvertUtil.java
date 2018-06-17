package com.cflab.utils;

import org.apache.commons.beanutils.Converter;

import java.util.Date;
import java.util.regex.Pattern;

/**
 * 时间转换器
 */
public class MyDateconvertUtil implements Converter {
    private String parttern;
    public  MyDateconvertUtil(){};
    public MyDateconvertUtil(String parttern) {
        this.parttern = parttern;
    }


    @Override
    /**
     * type  表示要格式化的属性类型
     * value   表示前端传给后台得知，
     *返回值object表示最后要设置给该属性的值
     */
    public Object convert(Class type, Object value) {
        if (value==null) {
            return null;
        }
        if (type == Date.class) {
            //自定义时间转换工具
            return DateUtil.Str2date((String)value,parttern );
        }
        return value;
    }
}
