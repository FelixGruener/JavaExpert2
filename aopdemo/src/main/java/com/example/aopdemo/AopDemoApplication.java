package com.example.aopdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:spring-config.xml")
public class AopDemoApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(AopDemoApplication.class, args);
        TopSecretMisssion tsMission = ctx.getBean(TopSecretMisssion.class);
        tsMission.giveMePublicInfo();
        tsMission.giveMeSecretInfo();
    }

}

