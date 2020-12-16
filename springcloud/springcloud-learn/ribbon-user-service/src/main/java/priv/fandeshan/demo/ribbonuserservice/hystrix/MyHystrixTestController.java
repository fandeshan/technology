package priv.fandeshan.demo.ribbonuserservice.hystrix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(path = "myHystrix")
public class MyHystrixTestController {

    @Autowired
    private RestTemplate restTemplate;

    @MyHystrixCommand( fallback = "fallback")
    @GetMapping( path = "/test")
    public String test(Integer orderId){
        return restTemplate.getForObject("http://localhost:8801/order/"+orderId,String.class);
    }

    public String fallback(Integer orderId){
        return "请求被降级";
    }
}
