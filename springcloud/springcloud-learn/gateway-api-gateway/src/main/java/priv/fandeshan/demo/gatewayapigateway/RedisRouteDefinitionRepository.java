package priv.fandeshan.demo.gatewayapigateway;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * redis存储动态路由
 *
 * 创建动态路由url POST /actuator/gateway/routes/{route_id}
 * json数据如下
 * {
 *     "id": "baidu_route",
 *     "predicates": [{
 *       "name": "Path",
 *       "args": {"_genkey_0": "/baidu"}
 *     }],
 *     "filters": [{
 *        "args": {
 *            "_genkey_0": 1
 *        }
 *        "name": "StripPrefix"
 *     }],
 *     "uri": "https://www.baidu.com",
 *     "order": 0
 * }
 * 创建路由之后需要刷新 POST /actuator/gateway/refresh
 *
 */
@Component
public class RedisRouteDefinitionRepository implements RouteDefinitionRepository {

    private final static String GATEWAY_ROUTE_KEY="gateway_dynamic_route";

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        List<RouteDefinition> routeDefinitionList = new ArrayList<>();
        redisTemplate.opsForHash().values(GATEWAY_ROUTE_KEY).stream().forEach(
                route -> {
                    routeDefinitionList.add(JSON.parseObject(route.toString(),RouteDefinition.class));
                }
        );
        return Flux.fromIterable(routeDefinitionList);
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return route.flatMap(routeDefinition -> {
            redisTemplate.opsForHash().put(GATEWAY_ROUTE_KEY,routeDefinition.getId(),JSON.toJSONString(routeDefinition));
            return Mono.empty();
        });
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return routeId.flatMap( id -> {
            if ( redisTemplate.opsForHash().hasKey(GATEWAY_ROUTE_KEY,id)){
                redisTemplate.opsForHash().delete(GATEWAY_ROUTE_KEY,id);
                return Mono.empty();
            }
            return Mono.defer(()->Mono.error(new Exception("routeDefinition not found. "+id)));
        });
    }
}
