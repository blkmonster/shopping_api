package com.lyc.yl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.lyc.yl.dao") //让Spring扫描DA0
@SpringBootApplication
public class YlApplication {

    public static void main(String[] args) {
        SpringApplication.run(YlApplication.class, args);
    }

}
