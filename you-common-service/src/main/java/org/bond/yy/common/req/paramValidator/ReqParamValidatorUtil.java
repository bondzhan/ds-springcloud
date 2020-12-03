package org.bond.yy.common.req.paramValidator;

import org.bond.yy.common.exception.Xyb2bBusinessException;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author lchm
 * @Description:
 * @date 2018-08-11
 */
public class ReqParamValidatorUtil {

    private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    public static <T> List<String> validateParam(T t, Class<?>... groups) {
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(t, groups);

        List<String> messageList = new ArrayList<>();
        for (ConstraintViolation<T> constraintViolation : constraintViolations) {
            messageList.add(constraintViolation.getMessage());
        }
        return messageList;
    }

    /**
     * 参数校验
     *
     * @param obj    需要校验的bean
     * @param groups 校验组(1:default,2:groups)
     * @return 校验失败message字符串，成功返回null
     */
    public static String validate(Object obj, Class<?>... groups) {
        try {
            StringBuilder errorStr = new StringBuilder();
            List<String> errorList = validateParam(obj, groups);
            if (CollectionUtils.isEmpty(errorList)) {
                //没有不符合要求的参数
                return null;
            } else {
                //错误信息用，号连接
                errorList.forEach(row -> {
                    errorStr.append(row + ",");
                });
                int length = errorStr.length();
                errorStr.delete(length - 1, length);//去掉最后一个,逗号
                return errorStr.toString();
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     *
     * @param obj    需要校验的bean
     * @param groups 校验组(1:default,2:groups+default)
     * @return
     */
    public static String validateWithDefault(Object obj, Class<?>... groups) {
        int len = groups.length;
        Class<?>[] group = new Class[len + 1];
        if (0 != len) {
            for (int i = 0; i < len; i++) {
                group[i] = groups[i];
            }
        }
        group[len] = Default.class;
        return validate(obj,group);
    }

    /**
     * 参数校验
     *
     * @param obj    需要校验的bean
     * @param groups 校验组(1:default,2:groups+default)
     * @throws Exception
     */
    public static void validator(Object obj, Class<?>... groups) throws Xyb2bBusinessException {
        String errorParam = validateWithDefault(obj, groups);
        if (!StringUtils.isEmpty(errorParam)) {
            throw new Xyb2bBusinessException(errorParam);
        }
    }

}
