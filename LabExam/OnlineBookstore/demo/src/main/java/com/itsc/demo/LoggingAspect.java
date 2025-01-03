package com.itsc.demo;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.itsc.demo.BookRegistrationServlet.doPost(..))")
    public void logBeforeRegistration() {
        System.out.println("Executing BookRegistrationServlet#doPost...");
    }
}
