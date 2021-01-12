package com.yinhai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@ServletComponentScan
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableTransactionManagement
@EnableAsync
@EnableCaching
//@EnableScheduling
public class BysjApplication {

public static void main(String[] args) {
        SpringApplication application = new SpringApplication(BysjApplication.class);
        try {
            application.run(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
