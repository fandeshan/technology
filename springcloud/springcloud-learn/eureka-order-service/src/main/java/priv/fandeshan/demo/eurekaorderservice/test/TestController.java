package priv.fandeshan.demo.eurekaorderservice.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "Hello World";
    }

    @Value("${custom.property.hello}")
    private String text;

    @Autowired
    private Environment environment;
//    private org.springframework.cloud.config.environment.Environment environment1;

    @GetMapping("/config")
    public String getText(){
//        return environment.getProperty("order.value");
        return text;
    }

    @GetMapping("/orders/{orderId}")
    public String getOrder(@PathVariable("orderId")Integer orderId){
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "查询订单："+orderId;
    }
}
