package com.atguigu.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * swagger配置类
 * @author sunhcer
 * @date 2021/01/25 21:52
 * ui地址 :http://localhost:8001/swagger-ui.html#/
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
//                .apis(RequestHandlerSelectors.any())// 扫描所有包路径
                .apis(RequestHandlerSelectors.basePackage("com.atguigu.springcloud.controller"))//apis() ：这种方式我们可以通过指定包名的方式，让 Swagger 只去某些包下面扫描。
                .paths(PathSelectors.any())//paths() ：这种方式可以通过筛选 API 的 url 来进行过滤。
                .build()
                .apiInfo(apiInfo());
    }


    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Spring Boot 项目集成 Swagger 实例文档",
                "简单swagger",
                "API V1.0",
                "Terms of service",
                new Contact("名字想好没", "小刚啊", "还在梦游呢"),
                "Apache", "快醒醒", Collections.emptyList());
    }
}
