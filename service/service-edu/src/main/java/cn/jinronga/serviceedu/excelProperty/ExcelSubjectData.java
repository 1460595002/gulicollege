package cn.jinronga.serviceedu.excelProperty;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @ClassName ExcelSubjectData
 * @Author 郭金荣
 * @Date 2021/3/2 21:59
 * @Description ExcelSubjectData
 * @Version 1.0 创建和Excel对应的实体类
 */
@Data
public class ExcelSubjectData {

    @ExcelProperty(index = 0)
    private String oneSubjectName;

    @ExcelProperty(index = 1)
    private String twoSubjectName;

}
