package com.fds.springboot.demo.annotation.imports;

import com.fds.springboot.demo.annotation.lazy.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {Cat.class,MyImportSelector.class,MyImportBeanDefinitionRegistrar.class})
public class MyConfig {

    @Bean
    public Person person(){
        return new Person("fds",111);
    }

    @Bean
    public MyFactoryBean entity4(){
        return  new MyFactoryBean();
    }

}
