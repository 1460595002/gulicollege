package cn.jinronga.serviceedu.controller;


import cn.jinronga.commonutils.R;
import cn.jinronga.serviceedu.entity.EduTeacher;
import cn.jinronga.serviceedu.query.EduTeacherQuery;
import cn.jinronga.serviceedu.service.EduTeacherService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
    public R list() {
        return R.ok().data("items", teacherService.list(null));
    }

    /**
     * 逻辑删除
     *
     * @return
     */
    @ApiOperation(value = "根据ID删除讲师")
    @DeleteMapping("delete/{id}")
    public R removeTeacher(@ApiParam(name = "id", value = "讲师ID", required = true)
                           @PathVariable("id") String id) {
        //逻辑删除
        boolean flag = teacherService.removeById(id);
        if (!flag) {
            R.error();
        }
        return R.ok();
    }

    @ApiOperation(value = "分页讲师列表")
    @PostMapping("/teacherPage/{page}/{limit}")
    public R pageList(@ApiParam(name = "page", value = "当前页面", required = true)
                      @PathVariable Long page,
                      @ApiParam(name = "limit", value = "每页记录数", required = true)
                      @PathVariable Long limit, @RequestBody(required = false) EduTeacherQuery eduTeacherQuery) {
        //创建page对象
        Page<EduTeacher> eduTeacherPage = new Page<>(page, limit);
        //调用方法实现分页
        //调用方法得时候，底层封装，把所有分页数据封装到了eduTeacherPage对象里
        teacherService.pageQuery(eduTeacherPage, eduTeacherQuery);
        List<EduTeacher> records = eduTeacherPage.getRecords();//list集合
        long total = eduTeacherPage.getTotal();
        return R.ok().data("total", total).data("rows", records);
    }
}

