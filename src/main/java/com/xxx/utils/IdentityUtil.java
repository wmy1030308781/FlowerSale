package com.xxx.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IdentityUtil {
    public static  boolean isMobile(String phone){
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        // 验证手机号
        String s2="^[1-9]\\d{5}(18|19|20|(3\\d))\\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";
        if(StringUtils.isNotBlank(phone)){
            p = Pattern.compile(s2);
            m = p.matcher(phone);
            b = m.matches();
        }
        return b;
    }
}
