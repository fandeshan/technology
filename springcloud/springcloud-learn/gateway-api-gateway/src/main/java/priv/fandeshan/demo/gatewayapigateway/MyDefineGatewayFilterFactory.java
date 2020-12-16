package priv.fandeshan.demo.gatewayapigateway;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class MyDefineGatewayFilterFactory extends AbstractGatewayFilterFactory<MyDefineGatewayFilterFactory.Config> {

    public MyDefineGatewayFilterFactory(){
        super(Config.class);
    }

    private static final String CONFIG_NAME="name";

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(CONFIG_NAME);
    }

    @Override
    public GatewayFilter apply(Config config) {
        //Filter 分为 pre 和 post 两种
        return (exchange,chain) -> {
            log.info("[pre] Filter Request,name:"+config.getName());
            return chain.filter(exchange).then(Mono.fromRunnable( ()->{
                log.info("[post]: Response Filter ");
            }));
        };
    }

    @Data
    public static class Config{
        private String name;
    }
}
