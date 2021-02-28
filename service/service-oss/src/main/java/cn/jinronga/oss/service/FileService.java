package cn.jinronga.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName FileService
 * @Author 郭金荣
 * @Date 2021/2/28 22:42
 * @Description FileService
 * @Version 1.0
 */
public interface FileService {
    /**
     * 文件上传到阿里云
     * @param file
     * @return
     */
    String upload(MultipartFile file);
}
