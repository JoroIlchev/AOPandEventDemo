package com.example.apidemo.aspect;

import com.example.apidemo.mail.EmailSender;
import com.example.apidemo.mail.EmailService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;

@Aspect
@Configuration
public class ApiAspect {

    private final EmailService emailService;

    public ApiAspect(EmailService emailService) {
        this.emailService = emailService;
    }

    @Pointcut("execution(* com.example.apidemo.controller.ApiController.*(..))")
    private void countApiCallTime() {
    }

    @Around("countApiCallTime()")
    public Object timeCounter(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopwatch = new StopWatch();
        stopwatch.start();
        Object proceed = joinPoint.proceed();
        stopwatch.stop();

        long result = stopwatch.getLastTaskTimeMillis();

        System.out.println("Elapse time is :" + result);

//        emailService.sendSimpleMessage("test@yahoo.com", "From my test", "Elapse time was too long");
//        EmailSender emailSender = new EmailSender();
//        emailSender.sendEMail();

        return proceed;

    }

}
