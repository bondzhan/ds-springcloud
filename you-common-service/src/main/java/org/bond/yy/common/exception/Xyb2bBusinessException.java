package org.bond.yy.common.exception;

/**
 * @author lchm
 * @Description: 业务异常
 * @ClassName: S2b2cBusinessException
 * @date 2018-09-17 17:41
 */
public class Xyb2bBusinessException extends RuntimeException{

    public Xyb2bBusinessException(String message) {
        super(message);
    }

    public Xyb2bBusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
