package priv.fandeshan.demo.ribbonuserservice.hystrixtest.feign;

import org.springframework.stereotype.Component;

@Component
public class OrderServiceFeignClientFallback implements OrderServiceFeignClient{
    @Override
    public String getOrder(Integer orderId) {
        return "查询订单失败："+orderId;
    }
}
