package com.fds.springboot.demo.annotation.scope;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyTest {

    @Test
    public void test(){
        ApplicationContext app = new AnnotationConfigApplicationContext(MyConfig.class);
        System.out.println("IOC容器初始化中");
        Object person1 = app.getBean("person");
        Object person2 = app.getBean("person");
        System.out.println(person1 == person2);
    }

}
