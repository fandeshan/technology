package com.fds.springboot.demo.annotation.conditional;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class MyTest {

    @Test
    public void test(){
        ApplicationContext app = new AnnotationConfigApplicationContext(MyConfig.class);
        System.out.println("IOC容器创建完成");
        String[] names = app.getBeanDefinitionNames();
        System.out.println(Arrays.toString(names)
                .replaceAll("\\[|\\]","")
                .replaceAll(", ","\n"));
    }

}
