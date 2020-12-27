package com.fds.springboot.demo.annotation.componentscan;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

public class MyTypeFilter implements TypeFilter {

    /**
     *
     *
     * @param metadataReader   获取当前正在操作的类的信息
     * @param metadataReaderFactory 获取上下文中所有信息
     * @return
     * @throws IOException
     */
    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        //获取当前类的所有的注解信息
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        //获取当前扫描的类的信息
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        //获取当前类的所有的资源
        Resource resource = metadataReader.getResource();
        String className = classMetadata.getClassName();
        System.out.println("------------"+className+"------------");
        if (className.contains("er")){
            return true;
        }
        return false;
    }
}
