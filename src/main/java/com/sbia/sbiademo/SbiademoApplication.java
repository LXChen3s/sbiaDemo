package com.sbia.sbiademo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.sbia.sbiademo.mapper")
public class SbiademoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbiademoApplication.class, args);
    }
}
