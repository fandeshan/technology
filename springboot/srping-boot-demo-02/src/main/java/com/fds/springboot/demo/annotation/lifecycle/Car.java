package com.fds.springboot.demo.annotation.lifecycle;

public class Car {

    Car(){
        System.out.println("启动车辆");
    }

    public void start(){
        System.out.println("开始加油跑");
    }

    public void close(){
        System.out.println("刹车停车");
    }
}
