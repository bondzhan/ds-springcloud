package org.bond.yy.common.util.csv;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

import java.io.*;
import java.util.List;

/**
 * @author lchm
 * @Description: csv操作工具类
 * @ClassName: CSVUtils
 * @date 2018-09-29 10:03
 */
public class CSVUtils {

    /**
     * 设置私有
     */
    private CSVUtils() {
    }

    /**
     * csv文件导入
     *
     * @param inputStream
     * @param type        指定转化的实体类
     * @return java.util.List<org.apache.poi.ss.formula.functions.T> 返回实体类的集合
     * @throws Exception
     * @Title: importCsvWithoutHeader
     * @Description:
     * @Author lchm
     * @date 2018/9/30
     */
    public static <T> List<T> importCsvWithoutHeader(InputStream inputStream, Class<? extends T> type) throws Exception {
        return new CsvToBeanBuilder(new InputStreamReader(inputStream, "GBK")).withSkipLines(1)
                .withType(type).build().parse();
    }

    public static <T> void createCsvfile(String filePath, List<T> dataList) throws Exception {
        Writer writer = new OutputStreamWriter(new FileOutputStream(filePath), "GBK");
        StatefulBeanToCsv<T> beanToCsv = new StatefulBeanToCsvBuilder<T>(writer).build();
        beanToCsv.write(dataList);
        writer.close();
    }
}
