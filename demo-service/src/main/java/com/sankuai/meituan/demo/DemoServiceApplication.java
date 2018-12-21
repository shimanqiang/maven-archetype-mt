package com.sankuai.meituan.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xxxx
 */
@SpringBootApplication
public class DemoServiceApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(DemoServiceApplication.class);
        springApplication.run(args);
    }
}
