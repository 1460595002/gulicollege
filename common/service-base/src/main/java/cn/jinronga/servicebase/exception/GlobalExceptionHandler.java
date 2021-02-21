package cn.jinronga.servicebase.exception;


import cn.jinronga.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName GlobalExceptionHandler
 * @Author 郭金荣
 * @Date 2021/2/21 12:47
 * @Description GlobalExceptionHandler
 * @Version 1.0
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    //指定出现什么异常执行这个方法
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return R.error().message("执行了全局异常处理！");
    }

    //特定异常
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public R error(ArithmeticException e) {
        e.printStackTrace();
        return R.error().message("执行了ArithmeticException异常！");
    }

    //自定义异常
    @ExceptionHandler(GuliException.class)
    @ResponseBody
    public R error(GuliException e) {
        log.error("错误代码：{}",e.getCode()
        );
        log.error("错误信息：{}",e.getMsg());

        e.printStackTrace();
        return R.error().code(e.getCode()).message(e.getMsg());
    }
}
