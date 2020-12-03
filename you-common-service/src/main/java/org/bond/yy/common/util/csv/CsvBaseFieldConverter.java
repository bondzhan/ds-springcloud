package org.bond.yy.common.util.csv;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

import org.apache.commons.lang3.StringUtils;
import org.bond.yy.common.util.DateStyle;
import org.bond.yy.common.util.DateUtil;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * @author lchm
 * @Description: TODO 这里用一句话描述这个类的作用
 * @ClassName: CsvBaseFieldConverter
 * @date 2018-09-29 16:04
 */
public class CsvBaseFieldConverter<T> extends AbstractBeanField<T> {

    private static final String INTEGER_CLASS_NAME = "java.lang.Integer";
    private static final String DATE_CLASS_NAME = "java.util.Date";

    @Override
    protected Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        Field field = getField();
        if (StringUtils.equals(field.getType().getName(), INTEGER_CLASS_NAME)) {
            if (StringUtils.isEmpty(value)) {
                return 0;
            }
            return Integer.valueOf(value);
        }
        if (StringUtils.equals(field.getType().getName(), DATE_CLASS_NAME)) {
            if (StringUtils.isEmpty(value)) {
                return null;
            }
            if (DateUtil.isDate(value)) {
                Date date = DateUtil.StringToDate(value);
                return date;
            }
            return null;
        }
        return value;
    }
}
