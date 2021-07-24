package com.mohan.practice.config;


import groovy.lang.Singleton;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Getter
@PropertySource("classpath:${spring.profiles.active}.practice.properties")
@Singleton
public class AppConfig {

    @Value("${webUrl}")
    String webUrl;

    @Value("${serviceUrl}")
    String serviceUrl;

}
