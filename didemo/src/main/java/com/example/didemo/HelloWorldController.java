package com.example.didemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE) //Default: SCOPE_SINGLETON
public class HelloWorldController {




    @Autowired
    private HelloWorldService hwService;
    private HelloWorldService hwServiceDE;

    @Autowired @German
    public void setHelloWorldServiceDE(HelloWorldService hwServiceDE){
        this.hwServiceDE = hwServiceDE;
    }

    public void sayHello(){
        hwService.sayHello();
    }

    public void sayHelloDE(){
        hwServiceDE.sayHello();
    }

}
