package cn.jinronga.servicebase.exception;

import cn.jinronga.commonutils.R;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName GuliException
 * @Author 郭金荣
 * @Date 2021/2/21 13:47
 * @Description 自定义异常类
 * @Version 1.0
 */
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuliException extends RuntimeException {
    @ApiModelProperty(value = "状态码")
    private Integer code;
    private String msg;
}
