package com.fds.springboot.demo.annotation.imports;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        boolean entity1 = registry.containsBeanDefinition("com.fds.springboot.demo.annotation.imports.Entity1");
        boolean entity2 = registry.containsBeanDefinition("com.fds.springboot.demo.annotation.imports.Entity2");
        if (entity1 && entity2) {
            BeanDefinition beanDefinition = new RootBeanDefinition(Entity3.class);
            registry.registerBeanDefinition("entity3",beanDefinition);
        }
    }
}
