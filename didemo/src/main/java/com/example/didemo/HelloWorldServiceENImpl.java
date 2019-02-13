package com.example.didemo;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Primary
@Profile({"default",
        "en"})
public class HelloWorldServiceENImpl implements HelloWorldService {

    @Override
    public void sayHello(){
        System.out.println("Hello World!");
    }
}
