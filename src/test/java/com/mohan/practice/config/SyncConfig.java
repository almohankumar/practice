package com.mohan.practice.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Getter
@PropertySource("classpath:${spring.profiles.active}.practice.properties")
//@Scope("singleton")
public class SyncConfig {

    @Value("${implicitTimeout}")
    String implicitTimeout;

    @Value("${pageTimeout}")
    String pageTimeout;

    @Value("${waitTime}")
    String waitTime;

    @Value("${serviceTimeout}")
    String serviceTimeout;

    public int getImplicitTimeout() {

        if(implicitTimeout!=null){
            return Integer.parseInt(implicitTimeout);
        }
        return 0;

    }

    public int getPageTimeout() {

        if(pageTimeout!=null){
            return Integer.parseInt(pageTimeout);
        }
        return 0;

    }

    public int getWaitTime() {

        if(waitTime!=null){
            return Integer.parseInt(waitTime);
        }
        return 0;

    }

    public int getServiceTimeout() {

        if(serviceTimeout!=null){
            return Integer.parseInt(serviceTimeout);
        }
        return 0;

    }
}
