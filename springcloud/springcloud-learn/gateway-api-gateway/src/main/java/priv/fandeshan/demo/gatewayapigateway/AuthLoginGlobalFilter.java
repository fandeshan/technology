package priv.fandeshan.demo.gatewayapigateway;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import priv.fandeshan.demo.gatewayapigateway.feign.IUserFeignClient;
import priv.fandeshan.demo.tools.api.ResultInfo;
import priv.fandeshan.demo.tools.enums.ResultCode;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 登录校验拦截
 */
@PropertySource(value = "classpath:loginfilter.properties")
@Component
@Slf4j
public class AuthLoginGlobalFilter implements GlobalFilter, Ordered {

    @Value("#{'${jwt.ignoreUrls}'.split(',')}")
    List<String> ignoreUrls;

    @Autowired
    private IUserFeignClient userFeignClient;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        if (ignoreUrls != null && ignoreUrls.contains(request.getURI().getPath())){
            return chain.filter(exchange);
        }
        String accessToken = request.getHeaders().getFirst("access_token");
        if (StringUtils.isBlank(accessToken)){
            return onError(exchange,"尚未登录");
        }

        ResultInfo<String> resultInfo = userFeignClient.validToken(accessToken);
        if (ResultCode.SUCCESS.getCode().equals(resultInfo.getCode())){
            ServerHttpRequest shr = request.mutate().header("uid", resultInfo.getData()).build();
            return chain.filter(exchange.mutate().request(shr).build());

        }
        return onError(exchange,resultInfo.getMessage());
    }

    private Mono<Void> onError(ServerWebExchange exchange,String msg){
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add("Content-Type","application/json;charset=UTF8");
        ResultInfo rd = new ResultInfo.Builder<>().buildCustomize(HttpStatus.UNAUTHORIZED.value() + "", msg);
        ObjectMapper objectMapper = new ObjectMapper();
        String rs = "";
        try {
            rs = objectMapper.writeValueAsString(rd);
        } catch (JsonProcessingException e) {
            log.error("occur Exception:"+e);
        }
        DataBuffer buffer = response.bufferFactory().wrap(rs.getBytes());
        return response.writeWith(Flux.just(buffer));
    }


    @Override
    public int getOrder() {
        return 0;
    }
}
