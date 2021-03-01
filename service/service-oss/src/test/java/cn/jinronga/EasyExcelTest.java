package cn.jinronga;

import cn.jinronga.entity.DemoData;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName EasyExcelTest
 * @Author 郭金荣
 * @Date 2021/3/1 23:02
 * @Description EasyExcelTest
 * @Version 1.0
 */
public class EasyExcelTest {
    public static void main(String[] args) {
//        inputDateEasyExcel1();
        inputDateEasyExcel2();
    }


    /**
     * 数据
     *
     * @return
     */
    private static List<DemoData> testData() {
        //循环设置要添加的数据，最终封装到list集合中
        ArrayList<DemoData> list = new ArrayList<>();
        for (int i = 1; i < 20; i++) {
            DemoData demoData = new DemoData();
            demoData.setSno(i);
            demoData.setSname("金融" + i + "号");

            list.add(demoData);
        }
        return list;
    }

    /**
     * EasyExcel实现导出方法一
     */
    public static void inputDateEasyExcel1() {
        //写入文件路径
        String fileName = "E:\\project\\1.xlsx";
/**
 * 这里 需要指定写用哪个class去写，
 * 然后写到第一个sheet，名字为模板 然后文件流会自动关闭
 * testData()数据方法
 */
        EasyExcel.write(fileName, DemoData.class).sheet().doWrite(testData());
    }

    /**
     * EasyExcel实现导出方法二
     */
    public static void inputDateEasyExcel2() {
        //写法二：
        String fileName = "E:\\project\\2.xlsx";
        //这里需要指定写用那个class去写
        ExcelWriter writerBuilder = EasyExcel.write(fileName, DemoData.class).build();

        WriteSheet writeSheet = EasyExcel.writerSheet("导出写法二").build();
        writerBuilder.write(testData(), writeSheet);

        // 千万别忘记finish否则硬盘奔溃 会帮忙关闭流
        writerBuilder.finish();

    }
}
