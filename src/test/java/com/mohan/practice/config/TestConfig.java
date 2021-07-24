package com.mohan.practice.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.TestPropertySource;

@Configuration
@ComponentScan("com.mohan.practice")
@TestPropertySource(value={"classpath:${spring.profiles.active}.practice.properties",
        "classpath:application.properties"})
public class TestConfig {

}
