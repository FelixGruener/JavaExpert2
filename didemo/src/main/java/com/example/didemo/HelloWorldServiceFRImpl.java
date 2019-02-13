package com.example.didemo;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"default",
        "fr"})
public class HelloWorldServiceFRImpl implements HelloWorldService {

    @Override
    public void sayHello(){
        System.out.println("Bonjour le monde!");
    }
}
