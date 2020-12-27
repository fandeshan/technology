package com.fds.springboot.demo.annotation.componentscan;

import com.fds.springboot.demo.annotation.lazy.Person;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(value = "com.fds.springboot.demo.annotation.componentscan",
        includeFilters = {@ComponentScan.Filter(type = FilterType.CUSTOM,value = {MyTypeFilter.class})},
        useDefaultFilters = false
)
public class MyConfig {


}
