package com.example.jsonpath;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class JsonpathdemoApplication {

    public static void main(String[] args) throws Exception {

        try (
            ConfigurableApplicationContext ctx = SpringApplication.run(JsonpathdemoApplication.class, args);
        ) {

            JsonPathController controller = ctx.getBean(JsonPathController.class);

            controller.demoObjectMapperUsingPath();
            controller.demoObjectMapperUsingAt();
            controller.demoJsonPath();



        }
    }



}

