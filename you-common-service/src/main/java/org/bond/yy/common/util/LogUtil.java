package org.bond.yy.common.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author lzy
 * @Description: 异常完整日志输出
 * @ClassName: LogUtil
 * @date 2018-12-26 10:23
 */
public class LogUtil {
	
 public static String logStackTrace (Exception e) {
        StringWriter sw = new StringWriter ();
        PrintWriter pw = new PrintWriter (sw, true);
        e.printStackTrace (pw);
        String errorStr = sw.toString ();
        pw.flush ();
        pw.close ();
        return  errorStr;
    }
}
