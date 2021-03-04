package cn.jinronga.serviceedu.controller;


import cn.jinronga.commonutils.R;
import cn.jinronga.serviceedu.Vo.SubjectNestedVo;
import cn.jinronga.serviceedu.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author jinronga
 * @since 2021-03-02
 */
@Api(description = "课程分类")
@RestController
@RequestMapping("/serviceedu/subject")
@CrossOrigin
public class EduSubjectController {
    @Autowired
    private EduSubjectService subjectService;

    //导入Excel文件 课程分类
    @ApiOperation(value = "Excel批量导入")
    @PostMapping("addSubject")
    public R addSubject(@ApiParam("Excel文件") MultipartFile file) {
        //上传Excel
        subjectService.batchImport(file, subjectService);
        return R.ok();
    }

    /**
     * 课程分类
     */
    @ApiOperation(value = "嵌套数据列表")
    @GetMapping("/tree")
    public R nestedList(){
        List<SubjectNestedVo> subjectNestedVos = subjectService.nestedList();

        return R.ok().data("items",subjectNestedVos);
    }
}

