package com.fijo.fileservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "com.fijo.fileservice.mapper")
public class FileserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileserviceApplication.class, args);
    }

}
