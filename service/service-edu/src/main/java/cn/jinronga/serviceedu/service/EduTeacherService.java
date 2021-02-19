package cn.jinronga.serviceedu.service;

import cn.jinronga.serviceedu.entity.EduTeacher;
import cn.jinronga.serviceedu.query.EduTeacherQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author jinronga
 * @since 2021-02-18
 */
public interface EduTeacherService extends IService<EduTeacher> {
    /**
     * 条件查询讲师数据且分页
     *
     * @param pageParam
     * @param teacherQuery
     */
    void pageQuery(Page<EduTeacher> pageParam, EduTeacherQuery teacherQuery);
}
