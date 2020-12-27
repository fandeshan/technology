package com.fds.springboot.demo.annotation.lazy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class MyConfig {

    /**
     *  默认非延迟加载
     *  延迟加载，懒加载只针对单例bean有效
     *   默认容器启动时不创建对象
     * @return
     */
    @Lazy
    @Bean
    public Person person(){
        System.out.println("将对象添加到IOC容器中");
        return new Person("fds",999);
    }
}
