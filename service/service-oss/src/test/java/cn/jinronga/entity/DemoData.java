package cn.jinronga.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @ClassName DemoData
 * @Author 郭金荣
 * @Date 2021/3/1 23:02
 * @Description DemoData
//设置表头和添加的数据字段
 */
@ToString
@Data
public class DemoData {
    //设置表头名称
    @ExcelProperty("学生编号")
    private int sno;

    //设置表头名称
    @ExcelProperty("学生姓名")
    private String sname;

}
