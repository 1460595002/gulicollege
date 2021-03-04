package cn.jinronga.serviceedu.service;

import cn.jinronga.serviceedu.Vo.SubjectNestedVo;
import cn.jinronga.serviceedu.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author jinronga
 * @since 2021-03-02
 */
public interface EduSubjectService extends IService<EduSubject> {
    /**
     * 导入excel文件
     *
     * @param file
     */
    void batchImport(MultipartFile file, EduSubjectService subjectService);

    /**
     * 二级分类数据
     *
     * @return
     */
    List<SubjectNestedVo> nestedList();

}
