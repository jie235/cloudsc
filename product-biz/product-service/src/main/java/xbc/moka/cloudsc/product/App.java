package xbc.moka.cloudsc.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//要想responseAdvice生效，这里需要这么配置
@SpringBootApplication(scanBasePackages = {"xbc.moka.cloudsc.common", "xbc.moka.cloudsc.product"})
@MapperScan(basePackages = "xbc.moka.cloudsc.product.mapper")
@EnableTransactionManagement
@EnableDiscoveryClient
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
