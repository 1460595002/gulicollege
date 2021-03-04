package cn.jinronga.serviceedu.service.impl;

import cn.jinronga.servicebase.exception.GuliException;
import cn.jinronga.serviceedu.Listener.SubjectExcelListener;
import cn.jinronga.serviceedu.Vo.SubjectNestedVo;
import cn.jinronga.serviceedu.Vo.SubjectVo;
import cn.jinronga.serviceedu.entity.EduSubject;
import cn.jinronga.serviceedu.excelProperty.ExcelSubjectData;
import cn.jinronga.serviceedu.mapper.EduSubjectMapper;
import cn.jinronga.serviceedu.service.EduSubjectService;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.security.auth.Subject;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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
            throw new GuliException(20002, "添加课程分类失败");
        }

    }

    @Override
    public List<SubjectNestedVo> nestedList() {
        //最终要得到的数据列表
        ArrayList<SubjectNestedVo> subjectNestedVos = new ArrayList<>();


        //获取一级分类数据
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", 0);
        queryWrapper.orderByAsc("sort", "id");
        List<EduSubject> eduSubjects = baseMapper.selectList(queryWrapper);

        //获取耳机分类数据记录
        QueryWrapper<EduSubject> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.ne("parent_id", 0);
        queryWrapper1.orderByAsc("sort", "id");
        List<EduSubject> eduSubjects1 = baseMapper.selectList(queryWrapper1);

        //填充一级分类Vo
        int count = eduSubjects.size();
        for (int i = 0; i < count; i++) {
            EduSubject eduSubject = eduSubjects.get(i);

            //创建一级分类Vo对象
            SubjectNestedVo subjectNestedVo = new SubjectNestedVo();
            BeanUtils.copyProperties(eduSubject, subjectNestedVo);
            subjectNestedVos.add(subjectNestedVo);
            int subSize = eduSubjects1.size();
            ArrayList<SubjectVo> subjectVoArrayList = new ArrayList<>();

            for (int j = 0; j < subSize; j++) {
                EduSubject eduSubject1 = eduSubjects1.get(j);
                if (eduSubject.getId().equals(eduSubject1.getParentId())) {

                    //创建二级分类Vo对象
                    SubjectVo subjectNestedTwo = new SubjectVo();
                    BeanUtils.copyProperties(eduSubject1, subjectNestedTwo);
                    subjectVoArrayList.add(subjectNestedTwo);
                }
            }
            subjectNestedVo.setChildren(subjectVoArrayList);
        }

        return subjectNestedVos;
    }
}
