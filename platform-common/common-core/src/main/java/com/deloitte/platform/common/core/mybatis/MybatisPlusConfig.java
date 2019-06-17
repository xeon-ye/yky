package com.deloitte.platform.common.core.mybatis;



import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author : jackliu
 * @Date : Create in 12:20 29/01/2019
 * @Description :
 * @Modified :
 */
@EnableTransactionManagement
@Configuration
@MapperScan({"com.deloitte.services.**.mapper","com.deloitte.platform.basic.*.mapper"})
public class MybatisPlusConfig {
    /**
     * mybatis-plus SQL执行效率插件【生产环境可以关闭】
     */
    @Bean
//    @Profile({"dev","test"})// 设置 dev test 环境开启
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        /*<!-- SQL 执行性能分析，开发环境使用，线上不推荐。 maxTime 指的是 sql 最大执行时长 -->*/
        performanceInterceptor.setMaxTime(30000);
        /*<!--SQL是否格式化 默认false-->*/
        performanceInterceptor.setFormat(true);
        return performanceInterceptor;
    }

    /**
     * BaseMapper的selectPage方法和AR提供的selectPage方法都不是物理分页，需要配置分页插件后才是物理分页
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }


    /**
     * 字段为空后将jdbcType设置成null，默认是Other
     */
    @Bean
    public ConfigurationCustomizer configurationCustomizer(){
        return new MybatisPlusCustomizers();
    }

    /**
     * 乐观锁
     * @return
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    class MybatisPlusCustomizers implements ConfigurationCustomizer {

        @Override
        public void customize(org.apache.ibatis.session.Configuration configuration) {
            configuration.setJdbcTypeForNull(JdbcType.NULL);
        }
    }
}
