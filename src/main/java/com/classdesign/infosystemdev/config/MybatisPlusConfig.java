package com.classdesign.infosystemdev.config;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 解决分页问题
 */

@Configuration
@MapperScan("com.classdesign.infosystemdev.mapper")  //获取需要扫描的类
public class MybatisPlusConfig {

    @Bean
    public MybatisPlusInterceptor  mybatisPlusInterceptor (){
            MybatisPlusInterceptor interceptor=new MybatisPlusInterceptor();
            interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
            return interceptor;
    }
}
