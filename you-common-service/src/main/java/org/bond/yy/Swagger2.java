/**   
* @Title: Swagger2.java 
* @Package com.xingyun.xyb2b.admin 
* @Description: TODO(用一句话描述该文件做什么) 
* @author bond
* @date 2016年9月7日 下午6:08:50 
* @company 版权所有 深圳市天行云供应链有限公司
* @version V1.0   
*/
package org.bond.yy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/** 
* @ClassName: Swagger2 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author bond
* @date 2016年9月7日 下午6:08:50 
*  
*/
@Configuration
@EnableSwagger2
public class Swagger2 {
	@Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xingyun.xyb2b"))
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("xyb2b 接口管理")
                .description("后台管理API")
                .termsOfServiceUrl("http://www.xyb2b.com/")
                .contact("深圳行云全球汇")
                .version("1.0")
                .build();
    }
}
