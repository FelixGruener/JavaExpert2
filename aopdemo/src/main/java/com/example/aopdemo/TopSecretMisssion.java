package com.example.aopdemo;

import org.springframework.stereotype.Controller;

@Controller
public class TopSecretMisssion {

    public void giveMePublicInfo(){
        System.out.println("JAva is Cool and fun");
    }

    public void giveMeSecretInfo(){
        System.out.println("Spring cooler and funnier");
    }
}
