package com.gtmdmock.admin.componts;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;


/**
 * @Profile 注解 标识加载在dev和test文件使用
 */
@Configuration
@EnableSwagger2
@Profile({"dev", "test"})
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("Mock-Admin")
                //加载配置信息
                .apiInfo(apiInfo())
                //设置全局参数
//                .globalOperationParameters(globalParamBuilder())
                //设置全局响应参数
                .globalResponseMessage(RequestMethod.GET,responseBuilder())
                .globalResponseMessage(RequestMethod.POST,responseBuilder())
                .globalResponseMessage(RequestMethod.PUT,responseBuilder())
                .globalResponseMessage(RequestMethod.DELETE,responseBuilder())
                .select()
                //加载swagger 扫描包
                .apis(RequestHandlerSelectors.basePackage("com.gtmdmock.admin.controller"))
                .paths(PathSelectors.any()).build();
                //设置安全认证
//                .securitySchemes(security());

    }

    /**
     * 获取swagger创建初始化信息
     * @return
     */
    private ApiInfo apiInfo() {
        Contact contact = new Contact("test", "", "");
        return new ApiInfoBuilder().title("MockServer接口文档").description("dev by test").contact(contact)
                .version("1.0.0").build();
    }

    /**
     * 安全认证参数
     * @return
     */
//    private List<ApiKey> security() {
//        List<ApiKey> list=new ArrayList<>();
//        list.add(new ApiKey("token", "token", "header"));
//        return list;
//    }

    /**
     * 构建全局参数列表
     * @return
     */
//    private List<Parameter> globalParamBuilder(){
//        List<Parameter> pars = new ArrayList<>();
//        pars.add(parameterBuilder("token","Authorization Bearer {access_token}","string","header",false).build());
//        return pars;
//    }

    /**
     * 创建参数
     * @return
     */
    private ParameterBuilder parameterBuilder(String name,String desc,String type ,String parameterType,boolean required) {
        ParameterBuilder tokenPar = new ParameterBuilder();
        tokenPar.name(name).description(desc).modelRef(new ModelRef(type)).parameterType(parameterType).required(required).build();
        return tokenPar;
    }

    /**
     * 创建全局响应值
     * @return
     */
    private List<ResponseMessage> responseBuilder() {
        List<ResponseMessage> responseMessageList = new ArrayList<>();
        responseMessageList.add(new ResponseMessageBuilder().code(200).message("响应成功").build());
        responseMessageList.add(new ResponseMessageBuilder().code(500).message("服务器内部错误").build());
        return responseMessageList;
    }

}