package priv.fandeshan.demo.eurekaorderservice.profiledemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ProfileConfiguration {

    @Profile("dev")
    @Bean
    public ProfileService profileServiceDev(){
        return new ProfileService("dev");
    }

    @Profile("prd")
    @Bean
    public ProfileService profileServicePrd(){
        return new ProfileService("prd");
    }
}
