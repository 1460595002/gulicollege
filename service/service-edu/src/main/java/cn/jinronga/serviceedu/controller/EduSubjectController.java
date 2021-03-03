package cn.jinronga.serviceedu.controller;


import cn.jinronga.commonutils.R;
import cn.jinronga.serviceedu.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
public class EduSubjectController {
    @Autowired
    private EduSubjectService subjectService;

    //导入Excel文件 课程分类
    @ApiOperation(value = "Excel批量导入")
    @PostMapping("addSubject")
    public R addSubject(@ApiParam("Excel文件") MultipartFile file) {
        subjectService.batchImport(file, subjectService);
        return R.ok();
    }
}

