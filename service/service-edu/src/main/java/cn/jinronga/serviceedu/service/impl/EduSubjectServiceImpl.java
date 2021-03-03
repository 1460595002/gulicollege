package cn.jinronga.serviceedu.service.impl;

import cn.jinronga.servicebase.exception.GuliException;
import cn.jinronga.serviceedu.Listener.SubjectExcelListener;
import cn.jinronga.serviceedu.entity.EduSubject;
import cn.jinronga.serviceedu.excelProperty.ExcelSubjectData;
import cn.jinronga.serviceedu.mapper.EduSubjectMapper;
import cn.jinronga.serviceedu.service.EduSubjectService;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author jinronga
 * @since 2021-03-02
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {
    /**
     * 添加课程分类
     * easyExcel读取内容
     *
     * @param file
     */
    @Override
    public void batchImport(MultipartFile file, EduSubjectService subjectService) {

        //获取文件输入流
        try {
            InputStream fileInputStream = file.getInputStream();
            ExcelReader excelReader = EasyExcel.read(fileInputStream, ExcelSubjectData.class, new SubjectExcelListener(subjectService)).build();
            ReadSheet readSheet = EasyExcel.readSheet(0).build();
            excelReader.read(readSheet);
            //关闭
            excelReader.finish();

        } catch (IOException ioException) {
            throw new GuliException(20002,"添加课程分类失败");
        }

    }
}
