package com.sankuai.meituan.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author shimanqiang
 * @since 2019/1/2 上午9:44
 */
@SpringBootApplication
@ComponentScan("com.sankuai.meituan.demo")
public class TestApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }
}
