package xbc.moka.cloudsc.common.rsp;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

//使用 basePackages 指定只处理本地包的返回值
//否则访问swagger接口会出现 "Unable to infer base url" 异常
//@RestControllerAdvice(basePackages = {"xbc.moka.cloudsc.order", "xbc.moka.cloudsc.acct", "xbc.moka.cloudsc.product"})
@RestControllerAdvice(basePackages = "xbc.moka.cloudsc")
public class ResponseAdvice implements ResponseBodyAdvice<Object> {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        //true为支持advice功能，false为不支持
        return true;
    }

    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (o instanceof String) {
            return objectMapper.writeValueAsString(ResultData.success(o));
        }
        if (o instanceof ResultData) {
            return o;
        }
        /**
         * 没有加上 basePackages 之前，会拦截所有的对象
         * 不妨打印出对象的类型，能得到下面的输出，确认是把swagger的返回至也拦截了
         */
/*        Class<?> aClass1 = o.getClass();
        String name = aClass1.getName();
        System.out.println(name);*/
/*        springfox.documentation.swagger.web.UiConfiguration
        springfox.documentation.swagger.web.SecurityConfiguration
        java.util.ArrayList*/
        return ResultData.success(o);
    }
}
