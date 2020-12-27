package com.fds.springboot.demo.annotation.imports;

import org.springframework.beans.factory.FactoryBean;

public class MyFactoryBean implements FactoryBean<Entity4> {
    @Override
    public Entity4 getObject() throws Exception {
        return new Entity4();
    }

    @Override
    public Class<?> getObjectType() {
        return Entity4.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
