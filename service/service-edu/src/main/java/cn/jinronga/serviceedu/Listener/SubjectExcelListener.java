package cn.jinronga.serviceedu.Listener;

import cn.jinronga.servicebase.exception.GuliException;
import cn.jinronga.serviceedu.entity.EduSubject;
import cn.jinronga.serviceedu.excelProperty.ExcelSubjectData;
import cn.jinronga.serviceedu.service.EduSubjectService;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.Date;
import java.util.Map;

/**
 * @ClassName SubjectExcelListener
 * @Author 郭金荣
 * @Date 2021/3/2 22:21
 * @Description SubjectExcelListener
 * 创建读取Excel监听器
 */
public class SubjectExcelListener extends AnalysisEventListener<ExcelSubjectData> {

    public EduSubjectService subjectService;

    //创建有参数构造，传递subjectService用于操作数据库
    public SubjectExcelListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    //一行一行的去读取内容
    @Override
    public void invoke(ExcelSubjectData excelSubjectData, AnalysisContext analysisContext) {
        if (excelSubjectData == null) {
            throw new GuliException(20001, "添加失败！");
        }
        //添加一级分类并校验
        EduSubject eduSubject = existOneSubject(subjectService, excelSubjectData.getOneSubjectName());
        //数据库没有相同数据可以进行添加
        if (eduSubject == null) {
            eduSubject = new EduSubject();
//            eduSubject.setId("11785851084541216767");
            eduSubject.setTitle(excelSubjectData.getOneSubjectName());
            eduSubject.setParentId("0");
//            eduSubject.setGmtCreate(new Date());
//            eduSubject.setGmtModified(new Date());
            subjectService.save(eduSubject);
        }
        String pid = eduSubject.getId();

        //添加二级分类并进行校验
        EduSubject twoSubject = existTwoSubject(subjectService, excelSubjectData.getTwoSubjectName(), pid);
        //数据库没有相同数据可以进行添加
        if (twoSubject == null) {
            twoSubject = new EduSubject();
            twoSubject.setParentId(pid);
            twoSubject.setTitle(excelSubjectData.getTwoSubjectName());
            subjectService.save(twoSubject);
        }

    }

    /**
     * //读取excel表头信息
     *
     * @param headMap
     * @param context
     */
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头信息。。");
        super.invokeHeadMap(headMap, context);
    }

    //读取完成后执行
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    /**
     * 判断一级分类是否重复
     *
     * @param subjectService
     * @param name           导入进来的分类名字
     * @return
     */
    private EduSubject existOneSubject(EduSubjectService subjectService, String name) {
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", name);
        queryWrapper.eq("parent_id", "0");
        //根据excel数据进行查询（被调用做校验）
        EduSubject eduSubject = subjectService.getOne(queryWrapper);
        return eduSubject;
    }

    /**
     * 判断二级分类是否重复
     *
     * @param subjectService service查询数据库数据
     * @param name           导入进来的分类名字
     * @param pid            导入进来的数据父id
     * @return
     */
    private EduSubject existTwoSubject(EduSubjectService subjectService, String name, String pid) {
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", name);
        queryWrapper.eq("parent_id", pid);
        EduSubject eduSubject = subjectService.getOne(queryWrapper);

        return eduSubject;
    }


}
