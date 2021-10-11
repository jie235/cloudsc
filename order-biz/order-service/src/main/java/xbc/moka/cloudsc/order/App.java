package xbc.moka.cloudsc.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan(basePackages = "xbc.moka.cloudsc.order.mapper")
@EnableTransactionManagement
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "xbc.moka.cloudsc.feign.*")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
