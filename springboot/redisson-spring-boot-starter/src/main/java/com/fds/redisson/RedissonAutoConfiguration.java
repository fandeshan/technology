package com.fds.redisson;

import com.fds.redisson.config.RedissonProperties;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(RedissonProperties.class)
public class RedissonAutoConfiguration {

    @Bean
    RedissonClient redissonClient(RedissonProperties redissonProperties){
        Config config = new Config();
        String prefix = "redis://";
        if (redissonProperties.isSsl()){
            prefix = "rediss://";
        }
        SingleServerConfig singleServerConfig = config.useSingleServer()
                .setAddress(prefix + redissonProperties.getHost() + ":" + redissonProperties.getPort())
                .setTimeout(redissonProperties.getTimeout());
        return Redisson.create(config);
    }
}
