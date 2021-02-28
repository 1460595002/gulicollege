package cn.jinronga.oss.service.impl;

import cn.jinronga.oss.service.FileService;
import cn.jinronga.oss.utils.ConstantPropertiesUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @ClassName FileServiceImpl
 * @Author 郭金荣
 * @Date 2021/2/28 22:43
 * @Description FileServiceImpl
 * @Version 1.0
 */
@Service
public class FileServiceImpl implements FileService {
    @Override
    public String upload(MultipartFile file) {

        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = ConstantPropertiesUtil.END_POINT;
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        String backetName = ConstantPropertiesUtil.BUCKET_NAME;


        try {
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            // 获取上传文件的输入流
            InputStream inputStream = file.getInputStream();

            // 获取文件名称
            String filename = file.getOriginalFilename();
            // 1. 在文件名称中添加随机的唯一的值，防止名称一样时文件的覆盖
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            filename = uuid + filename;

            // 2. 把文件安装日期进行分类，会自动创建文件夹
            // 获取当前的日期
            String datePath = new DateTime().toString("yyyy/MM/dd");
            filename = datePath + "/" + filename;


            // 调用oss方法实现上传
            // 第一个参数 Bucket名称
            // 第二个参数 上传到oss文件的路径和文件名称
            // 输入流
            ossClient.putObject(backetName, filename, inputStream);

            // 关闭OSSClient。
            ossClient.shutdown();

            // 上传文件之后的路径，需要自己拼接
            String url = "https://"+backetName+"."+endpoint+"/"+filename;

            //https://edu-pony.oss-cn-beijing.aliyuncs.com/2020/12/21/c382358e1e4340eca6957eee10fe5a7f01.jpg
            return url;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
