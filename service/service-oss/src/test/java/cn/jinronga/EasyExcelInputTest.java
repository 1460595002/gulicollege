package cn.jinronga;

import cn.jinronga.entity.ReadData;
import cn.jinronga.listener.ExcelListener;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @ClassName EasyExcelInputTest
 * @Author 郭金荣
 * @Date 2021/3/2 8:46
 * @Description EasyExcelInputTest
 * @Version 1.0
 */
public class EasyExcelInputTest {
    public static void main(String[] args) throws FileNotFoundException {
       //写法一：
        String fileName = "E:\\project\\2.xlsx";
   //这里需要指定读那个class去读取，读到第一个sheet文件流会自动关闭
        EasyExcel.read(fileName, ReadData.class,
                new ExcelListener()).sheet().doRead();;
         // 写法2：
        InputStream in = new BufferedInputStream(new FileInputStream("E:\\project\\1.xlsx"));
         ExcelReader excelReader = EasyExcel.read(in, ReadData.class, new
                ExcelListener()).build();
         ReadSheet readSheet = EasyExcel.readSheet(0).build();
         excelReader.read(readSheet);
         // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
         excelReader.finish();
    }

}
