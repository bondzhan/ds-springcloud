package org.bond.yy.common.util;

import com.google.common.base.Strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Thstone
 * @version V1.0
 * @Title:
 * @Package com.xingyun.s2b2c.common.util
 * @Description: (短信和邮件发送工具类)
 * @date 2018/8/27 18:51
 */
public class SMSAndEmailUtil {

    /**
     *
     * @Title: generateAuthNum
     * @Description: 生成随机数字验证码(纯数字)
     * @param authLen	验证码位数
     * @return	验证码
     * @author Thstone
     */

    public static String generateAuthNum(int authLen){
        int num = (int) (Math.random() * Math.pow(10, authLen));
        String numStr = String.valueOf(num);
        StringBuilder buil = new StringBuilder(authLen);
        // 生成authLen位数验证码,不足的左边补0
        for (int i = numStr.length(); i < authLen; i++) {
            buil.append('0');
        }
        buil.append(numStr);
        return String.valueOf(buil);
    }

    /**
     *
     * @Title: replaceSmsContent
     * @Description: 替换短信模板单个参数
     * @param template	模板内容
     * @param key		替换的参数关键字,无需替换内容可为空
     * @param value		替换的值
     * @return
     * @author Tito
     */
    public static String replaceSmsContent(String template, String key, String value) {
        if (Strings.isNullOrEmpty(template)) {
            return null;
        }
        String content = template;
        // 匹配${*}的参数
        Pattern pattern = Pattern.compile("\\$\\{[\\w_]{1,64}\\}");
        Matcher matcher = pattern.matcher(template);
        while (matcher.find()) {
            String matchedStr = matcher.group();
            String paramCode = matchedStr.replace("${", "").replace("}", "");
            if (Strings.isNullOrEmpty(key)) {
                return null;
            }
            if (!key.equals(paramCode)) {
                return null;
            }
            content = content.replace(matchedStr, value);

        }
        return content;
    }

}
