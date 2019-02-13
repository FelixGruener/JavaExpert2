package com.example.aopdemo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class SecurityAspects {

    private static final Logger LOGGER = Logger.getLogger(SecurityAspects.class.getName());
    private static final String USER_ALLOWED = "Bernhard";
    @Before("execution(* com.example.aopdemo.TopSecretMisssion.*(..))")
    public void logUser(JoinPoint jp) throws Throwable{
        LOGGER.info("method  " + jp.getSignature().getName() + " called by user " + getUser() + " .");
    }

    @Around("execution(* com.example.aopdemo.TopSecretMisssion.giveMeSecretInfo(..))")
    public Object checkUser(ProceedingJoinPoint jp) throws Throwable {
        String user = getUser();

        if (!USER_ALLOWED.equalsIgnoreCase(user)){
            return new RuntimeException("access denied: invalid User");
        }
        return jp.proceed();
    }

    public void showWarning(){
        System.out.println("Keep this to yourself");
    }

    private String getUser() {
        return System.getenv("username");
    }

}
