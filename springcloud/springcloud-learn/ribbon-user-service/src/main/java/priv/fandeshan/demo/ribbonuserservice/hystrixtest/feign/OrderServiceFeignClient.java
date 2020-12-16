package priv.fandeshan.demo.ribbonuserservice.hystrixtest.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * feign 集成hystrix方式
 */
@FeignClient(name="eureka-order-service",fallback =OrderServiceFeignClientFallback.class )
public interface OrderServiceFeignClient {

    @GetMapping("/order-service/orders/{orderId}")
    public String getOrder(@PathVariable("orderId")Integer orderId);

}
