package cn.jinronga.serviceedu.Vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SubjectNestedVo
 * @Author 郭金荣
 * @Date 2021/3/4 21:46
 * @Description SubjectNestedVo
 * @Version 1.0 课程编号vo
 */
@Data
public class SubjectNestedVo {
    /**
     * 课程id
     */
    private String id;
    /**
     * 课程分类
     */
    private String title;
    /**
     * SubjectVo集合
     */
    private List<SubjectVo> children = new ArrayList<>();
}
