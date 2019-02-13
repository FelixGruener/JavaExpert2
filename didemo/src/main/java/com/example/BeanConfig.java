package com.example;

import com.example.didemo.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public HelloWorldController hwController() {
        return new HelloWorldController();
    }

    @Bean
    public HelloWorldService hwService(){
        return new HelloWorldServiceENImpl();
    }

    @Bean @German
    public HelloWorldService hwServiceDE(){
        return new HelloWorldServiceDEImpl();
    }

}
