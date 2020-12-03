package org.bond.yy.common.util;

import javax.servlet.http.HttpServletResponse;

import org.bond.yy.common.exception.Xyb2bBusinessException;

import java.io.*;

/**
 * @author lchm
 * @Description: 文件下载
 * @ClassName: FileDownloadUtil
 * @date 2018-09-19 17:37
 */
public class FileDownloadUtil {

    private FileDownloadUtil() {
    }

    public static void download(String fileName, File file, HttpServletResponse response) throws Xyb2bBusinessException {
        OutputStream out = null;
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            response.setContentType("multipart/form-data");
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("UTF-8"), "ISO8859-1"));
            String len = String.valueOf(file.length());
            response.setHeader("Content-Length", len);
            out = response.getOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = in.read(b)) != -1) {
                out.write(b, 0, n);
            }
            out.flush();
        } catch (FileNotFoundException e) {
            throw new Xyb2bBusinessException("文件不存在");
        } catch (IOException e) {
            throw new Xyb2bBusinessException("文件下载失败");
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (Exception e) {
                System.out.println("文件下载失败");
            }
        }
    }

}
