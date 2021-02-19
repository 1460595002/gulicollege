package cn.jinronga.serviceedu.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @ClassName MyBatisPlusConfig
 * @Author 郭金荣
 * @Date 2021/2/18 20:41
 * @Description MyBatisPlusConfig
 * @Version 1.0
 */
@Configuration
@EnableTransactionManagement
@MapperScan("cn.jinronga.serviceedu.mapper")
public class MyBatisPlusConfig {

    /**
     * 逻辑删除插件
     *
     * @return
     */
    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}