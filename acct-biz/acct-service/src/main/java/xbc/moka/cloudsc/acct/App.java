package xbc.moka.cloudsc.acct;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {"xbc.moka.cloudsc.common", "xbc.moka.cloudsc.acct"})
@MapperScan(basePackages = "xbc.moka.cloudsc.acct.mapper")
@EnableTransactionManagement
@EnableDiscoveryClient
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
