package com.fds.springboot.demo.annotation.imports;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"com.fds.springboot.demo.annotation.imports.Entity1","com.fds.springboot.demo.annotation.imports.Entity2"};
    }
}
