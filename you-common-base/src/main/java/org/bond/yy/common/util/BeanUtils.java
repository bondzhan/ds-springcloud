package org.bond.yy.common.util;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author lchm
 * @Description: bean工具类
 * @ClassName: BeanUtils
 * @date 2018-08-24 11:02
 */
public class BeanUtils {

    /**
     * @Title: BeanUtils
     * @Description: 私有化工具类的构造
     * @Author lchm
     * @date
     */
    private BeanUtils() {
    }

    /**
     * @param obj
     * @return boolean
     * @throws
     * @Title: checkNull
     * @Description: 判断对象是否为空（属性全为空）
     * @Author lchm
     * @date 2018/8/24
     */
    public static boolean checkNull(Object obj) {
        boolean result = true;
        if (obj == null) {
            return true;
        }
        try {
            Class<?> cls = obj.getClass();
            Field[] fields = cls.getDeclaredFields();
            for (Field field : fields) {
                if (StringUtils.equals(field.getName(),"serialVersionUID")){
                    continue;
                }
                field.setAccessible(true);
                if (field.get(obj) != null) {
                    result = false;
                    break;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return result;
    }

    /**
     * @param bean
     * @param fieldList bean中的属性
     * @return boolean
     * @throws
     * @Title: checkFieldsNotNull
     * @Description: 判断bean中的fieldList是否全部为空
     * @Author lchm
     * @date 2018/8/24
     */
    public static boolean checkFieldsIsNull(Object bean, String... fieldList) {
        boolean result = true;
        if (bean == null) {
            return false;
        }
        if (fieldList.length < 1) {
            return true;
        }
        try {
            Class<?> cls = bean.getClass();
            Method[] methods = cls.getDeclaredMethods();
            for (String fieldStr : fieldList) {
                if (StringUtils.isEmpty(fieldStr)) {
                    continue;
                }
                String getMethod = parGetMethod(fieldStr);
                if (checkGetMethod(methods, getMethod)) {
                    Method fieldGetMet = cls.getMethod(getMethod, new Class[]{});
                    Object fieldVal = fieldGetMet.invoke(bean, new Object[]{});
                    if (null != fieldVal) {
                        //属性中只要有一个bu为空直接返回false
                        return false;
                    }
                } else {
                    return false;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return result;
    }

    /**
     * @param fieldName
     * @return java.lang.String
     * @throws
     * @Title: parGetMethod
     * @Description: 拼接属性get方法
     * @Author lchm
     * @date 2018/8/24
     */
    public static String parGetMethod(String fieldName) {
        if (StringUtils.isEmpty(fieldName)) {
            return null;
        }
        int startIndex = 0;
        if (fieldName.charAt(0) == '_'){
            startIndex = 1;
        }
        return "get"
                + fieldName.substring(startIndex, startIndex + 1).toUpperCase()
                + fieldName.substring(startIndex + 1);
    }

    /**
     * @param methods
     * @param fieldGetMethod
     * @return boolean
     * @throws
     * @Title: checkGetMethod
     * @Description: 判断属性的get方法是否存在
     * @Author lchm
     * @date 2018/8/24
     */
    public static boolean checkGetMethod(Method[] methods, String fieldGetMethod) {
        for (Method met : methods) {
            if (StringUtils.equals(fieldGetMethod, met.getName())) {
                return true;
            }
        }
        return false;
    }

}
