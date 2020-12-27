package com.fds.springboot.demo.annotation.conditional;

import com.fds.springboot.demo.annotation.lazy.Person;
import org.springframework.context.annotation.*;

@Configuration

public class MyConfig {

    @Conditional(LinuxCondition.class)
    @Bean
    public Person person1(){
        return new Person("fds1",111);
    }

    @Conditional(WinCondition.class)
    @Bean Person person2(){
        return new Person("fds2",222);
    }

}
