package cn.jinronga.oss.controller;

import cn.jinronga.commonutils.R;
import cn.jinronga.oss.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName FileController
 * @Author 郭金荣
 * @Date 2021/2/28 22:47
 * @Description FileController
 * @Version 1.0 文件上传控制器
 */
@Api(description = "阿里云文件管理")
@CrossOrigin//跨域
@RestController
@RequestMapping("/admin/oss/file")
public class FileController {
    @Autowired
    private FileService service;

    /**
     * 文件上传
     *
     * @param file
     * @return
     */
    @ApiOperation(value = "文件上传")
    @PostMapping("upload")
    public R upload(
            @ApiParam(name = "file", value = "文件", required = true)
            @RequestParam("file") MultipartFile file) {

        String uploadUrl = service.upload(file);

        return R.ok().message("文件上传成功！").data("url", uploadUrl);
    }
}
