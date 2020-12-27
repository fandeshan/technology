package com.fds.springboot.demo.annotation.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Rocket {

    Rocket(){
        System.out.println("火箭启动");
    }

    @PostConstruct
    public void start(){
        System.out.println("火箭升空");
    }

    @PreDestroy
    public void close(){
        System.out.println("火箭降落");
    }
}
