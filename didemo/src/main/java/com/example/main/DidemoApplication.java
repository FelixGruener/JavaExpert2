package com.example.main;

import com.example.BeanConfig;
import com.example.didemo.HelloWorldController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

@SpringBootApplication
@ComponentScan(basePackages = "com.example",
                excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,
                value = Configuration.class)
)
//@Import(BeanConfig.class)
//@ImportResource("classpath:spring-config.xml")
public class DidemoApplication {

    public static void main(String[] args) {

            ApplicationContext ctx = SpringApplication.run(DidemoApplication.class,args);

            HelloWorldController hwController1 =ctx.getBean(HelloWorldController.class);
            hwController1.sayHello();
            hwController1.sayHelloDE();

            HelloWorldController hwController2 =ctx.getBean(HelloWorldController.class);

            if(hwController1 == hwController2) {

                System.out.println(("Singleton"));

            } else {

                System.out.println("Protoype");
            }
    }
}

