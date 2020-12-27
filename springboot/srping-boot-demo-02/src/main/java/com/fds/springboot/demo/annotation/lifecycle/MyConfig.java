package com.fds.springboot.demo.annotation.lifecycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(MyBeanPostProcessor.class)
public class MyConfig {


    @Bean(initMethod = "start",destroyMethod = "close")
    public Car car(){
        return new Car();
    }

    @Bean
    public Train train(){
        return new Train();
    }

    @Bean
    public Rocket rocket(){
        return new Rocket();
    }
}
