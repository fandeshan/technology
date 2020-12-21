package priv.fandeshan.demo.ribbonuserservice.hystrixtest;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import priv.fandeshan.demo.ribbonuserservice.hystrixtest.feign.OrderServiceFeignClient;

@RestController
@RequestMapping(value = "/hystrix")
public class HystrixTestController {


    @Autowired
    private OrderServiceFeignClient orderServiceFeignClient;

    @GetMapping( value="/test1")
    public String test1(){
        return "hello user-services";
    }

    @HystrixCommand(commandProperties = {
            //开启状态
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
            //最小请求次数
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),
            //5s 熔断时间
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000"),
            // 错误百分比
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50")
    },fallbackMethod = "fallback")
    @GetMapping( value="/orders/{orderId}")
    public String getOrder(@PathVariable("orderId") Integer orderId){
        if (orderId != null && orderId % 2 == 0){
            return "成功！不经过order-service查询，订单信息："+orderId;
        }
        return "成功！，经过order-service查询，"+orderServiceFeignClient.getOrder(orderId);
    }

    /**
     *  此触发熔断方法，方法名与配置相同，请求及响应参数要与原方法一样
     * @param orderId
     * @return
     */
    public String fallback(Integer orderId){
        //异常兜底操作处理
        return "系统繁忙";
    }

    @HystrixCommand(commandProperties = {
           //超时熔断
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    },fallbackMethod = "fallbackTimeout")
    @GetMapping( value ="/ordersTime/{orderId}")
    public String getOrderTimeout(@PathVariable("orderId") Integer orderId){
        return "成功！，经过order-service查询，"+orderServiceFeignClient.getOrder(orderId);
    }

    public String fallbackTimeout(Integer orderId){
        //异常兜底操作处理
        return "系统超时";
    }
}
