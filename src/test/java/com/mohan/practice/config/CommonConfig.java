package com.mohan.practice.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

@Configuration
@Getter
@PropertySource("classpath:common.practice.properties")
@Scope("singleton")
public class CommonConfig {

    @Value("${driverPath}")
    String driverPath;

    @Value("${fallbackDriverPath}")
    String fallbackDriverPath;

    @Value("${uploadPath}")
    String uploadPath;

    @Value("${downloadPath}")
    String downloadPath;

    @Value("${resourcePath}")
    String resourcePath;

    @Value("${filePath}")
    String filePath;

    @Value("${basePath}")
    String basePath;


}
