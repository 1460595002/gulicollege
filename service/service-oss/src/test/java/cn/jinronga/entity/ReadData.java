package cn.jinronga.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @ClassName ReadData
 * @Author 郭金荣
 * @Date 2021/3/2 8:34
 * @Description ReadData
 * @Version 1.0
 * 通过EasyExcel导入实体类
 */
@Data
@ToString
public class ReadData {

    //设置列对应的属性
    @ExcelProperty(index = 0)
    private int sid;

    //设置列对应的属性
    @ExcelProperty(index = 1)
    private String sname;
}
