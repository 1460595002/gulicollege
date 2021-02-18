package cn.jinronga.serviceedu.controller;


import cn.jinronga.serviceedu.entity.EduTeacher;
import cn.jinronga.serviceedu.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author jinronga
 * @since 2021-02-18
 */
@Api(description = "讲师管理")
@RestController
@RequestMapping("/serviceedu/teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService teacherService;

    //Rest风格

    /**
     * 查询讲师所有的数据
     *
     * @return
     */
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("/findAll")
    public List<EduTeacher> list() {
        return teacherService.list(null);
    }

    /**
     * 逻辑删除
     *
     * @return
     */
    @ApiOperation(value = "根据ID删除讲师")
    @DeleteMapping("delete/{id}")
    public boolean removeTeacher(@ApiParam(name = "id", value = "讲师ID", required = true)
                                 @PathVariable("id") String id) {
        //逻辑删除
        boolean flag = teacherService.removeById(id);
        return flag;
    }
}

