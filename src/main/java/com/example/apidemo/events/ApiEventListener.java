package com.example.apidemo.events;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
public class ApiEventListener {


    @Async
    @EventListener
    public void handleApiEvent(ApiEvent event) throws InterruptedException {
        System.out.println("From Listener  : Source : "+event.getSource().getClass().getName() + ", Message : "+ event.getName());
        Thread.sleep(5000);
        System.out.println("From Listener  : completed");
    }
}
