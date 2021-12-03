package com.example.apidemo.events;

import org.springframework.context.ApplicationEvent;



public class ApiEvent extends ApplicationEvent {

    private String name;


    public ApiEvent(Object source, String name) {
        super(source);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
