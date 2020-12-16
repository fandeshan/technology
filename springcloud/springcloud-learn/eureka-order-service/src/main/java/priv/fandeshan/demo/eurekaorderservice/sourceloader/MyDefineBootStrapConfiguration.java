package priv.fandeshan.demo.eurekaorderservice.sourceloader;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyDefineBootStrapConfiguration {

    @Bean
    public JsonProperySourceLocator jsonProperySourceLocator(){
        return new JsonProperySourceLocator();
    }

}
