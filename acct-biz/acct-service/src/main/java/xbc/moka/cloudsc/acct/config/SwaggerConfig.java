package xbc.moka.cloudsc.acct.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import io.swagger.annotations.Api;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@ConditionalOnClass(Docket.class)
@EnableKnife4j//knife4j文档地址为 http://localhost:13091/doc.html 与swagger的地址不同
//swagger (springfox 3.x) 的文档地址为 http://localhost:13091/swagger-ui/index.html 与2.x的版本地址有区别
//swagger (springfox 2.x) 的文档地址为 http://localhost:13091/swagger-ui.html
public class SwaggerConfig {

    private static final String VERSION = "1.0";

    @Value("${swagger.enable}")
    private boolean enable;

    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enable)
                .groupName("SwaggerDemo")
                .apiInfo(apiInfo())
                .select()
                //.apis()方法用于指定生成注解的范围，可以有四种取值:
                //RequestHandlerSelectors.any() -> 为所有接口都生成api文档，这样就不用在接口上加注解，但是生成的文档没有注释，可读性不强
                //RequestHandlerSelectors.basePackage(xx.xx) --> 为指定包下的controller生成接口文档
                //RequestHandlerSelectors.withClassAnnotation(Api.class) -> 为有@Api注解的接口生成文档
                //RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class) -> 为有@ApiOperation注解的方法生成api文档
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build();
    }

    public ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("接口文档")
                .contact(new Contact("java日知录", "http://javadaily.cn", "jianzh5@163.com"))
                .description("swagger接口文档")
                .version(VERSION)
                .build();
    }
}
