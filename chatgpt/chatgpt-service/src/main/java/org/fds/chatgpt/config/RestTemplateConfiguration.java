package org.fds.chatgpt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author fandeshan
 * @description //TODO 写点注释吧
 * @date 2023/2/15
 */
@Configuration
public class RestTemplateConfiguration {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
