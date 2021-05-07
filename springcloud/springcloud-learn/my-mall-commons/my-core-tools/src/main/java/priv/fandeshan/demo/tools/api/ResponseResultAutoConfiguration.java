package priv.fandeshan.demo.tools.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.Collections;

@Configuration
@ComponentScan(
        basePackageClasses = {ResponseResultHandler.class, ResponseResultInterceptor.class}
)
public class ResponseResultAutoConfiguration extends WebMvcConfigurationSupport {
    @Autowired
    private ResponseResultInterceptor responseResultInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //配置拦截器
        registry.addInterceptor(responseResultInterceptor).addPathPatterns("/**")
                .excludePathPatterns(Collections.singletonList("/swagger-ui.html"))
                .excludePathPatterns("/swagger-resources/**")
                .excludePathPatterns("/error")
                .excludePathPatterns("/webjars/**");

    }
}
