package com.classdesign.infosystemdev.config;


import com.baomidou.mybatisplus.generator.config.PackageConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 这里的配置主要是为了解决swagger2配置问题
 */

@EnableSwagger2  //开启swagger功能
@Configuration   //标明配置类
public class SwaggerConfig {

    @Bean
    public Docket  createRestApi(Environment environment){
           return  new Docket(DocumentationType.SWAGGER_2)  //这个地方的文档格式选择swagger2
                   .apiInfo(apiInfo())  //定义API主界面的基本信息
                   .enable(true)  //选择是否开启swagger2
                   .select()
                   .apis(RequestHandlerSelectors.basePackage("com.classdesign.infosystemdev"))
                   /**
                    *RequestHandlerSelectors:配置要扫描的包
                    *any():扫描全部
                    *none:不扫描
                    */
                   .paths(PathSelectors.any()) //选择所有的API，如果你想只为部分API生成文档，可以在这个地方配置
                   .build();
    }

    /**
     *用于定义API主界面的信息，比如可以声明API的总标题，描述以及版本号。
     * @return
     */
    private ApiInfo  apiInfo(){
        return  new ApiInfoBuilder()
                .title("人力资源管理系统API")
                .description("人力资源管理系统API专业管理")
                .termsOfServiceUrl("")
                .version("2.0")
                .build();
    }

}
