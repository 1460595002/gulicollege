package cn.jinronga.commonutils;

import cn.jinronga.commonutils.result.IResultCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName R
 * @Author 郭金荣
 * @Date 2021/2/19 20:25
 * @Description R
 * @Version 1.0
 */
@ApiModel("统一返回结果")
@Data
public class R {
    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;
    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<String, Object>();
    /**
     * 构造方法私有化
     */
    private R(){}

    /**
     * 成功的静态方法
     * @return ResultVo
     */
    public static R ok(){
        R resultVo = new R();
        resultVo.setSuccess(true);
        resultVo.setCode(ResultCode.SUCCESS.getCode());
        resultVo.setMessage(ResultCode.SUCCESS.getMsg());
        return resultVo;
    }

    /**
     * 失败的静态方法
     * @return ResultVo
     */
    public static R error(){
        R resultVo = new R();
        resultVo.setSuccess(false);
        resultVo.setCode(ResultCode.ERROR.getCode());
        resultVo.setMessage(ResultCode.ERROR.getMsg());
        return resultVo;
    }


    public R success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public R message(String message){
        this.setMessage(message);
        return this;
    }

    public R code(Integer code){
        this.setCode(code);
        return this;
    }

    public R data(String key,Object value){
        this.data.put(key,value);
        return this;
    }

    public R data(Map<String,Object> map){
        this.setData(map);
        return this;
    }

    public R codeAndMessage(IResultCode resultCode){
        this.setCode(resultCode.getCode());
        this.setMessage(resultCode.getMsg());
        return this;
    }



}
