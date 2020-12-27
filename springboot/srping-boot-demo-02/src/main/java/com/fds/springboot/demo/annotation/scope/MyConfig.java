package com.fds.springboot.demo.annotation.scope;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class MyConfig {

    /**
     * 默认单例模式
     * @see org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_SINGLETON
     *
     * 原型，多例
     * @see org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE
     *
     * 同一次web请求创建一个实例
     * @see org.springframework.web.context.WebApplicationContext#SCOPE_REQUEST
     *
     * 同一个session内创建一个实例
     * @see org.springframework.web.context.WebApplicationContext#SCOPE_SESSION
     * @return
     */
   @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    @Bean
    public Person person(){
        System.out.println("将对象添加到IOC容器中");
        return new Person("fds",999);
    }
}
