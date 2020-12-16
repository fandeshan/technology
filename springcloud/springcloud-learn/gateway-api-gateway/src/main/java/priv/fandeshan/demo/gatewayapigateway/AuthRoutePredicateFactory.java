package priv.fandeshan.demo.gatewayapigateway;

import lombok.Data;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Component
public class AuthRoutePredicateFactory extends AbstractRoutePredicateFactory<AuthRoutePredicateFactory.Config> {

    public AuthRoutePredicateFactory(){
        super(Config.class);
    }
    private final String CONFIG_KEY = "NAME";
    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(CONFIG_KEY);
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {

        return exchange -> {
            HttpHeaders headers = exchange.getRequest().getHeaders();
            List<String> headList = headers.get(config.getName());
            return headList!= null && headList.size() > 0;
        };
    }

    @Data
    public static class Config{
        private String name;


    }
}
