package priv.fandeshan.demo.gatewayapigateway.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import priv.fandeshan.demo.tools.api.ResultInfo;

@FeignClient(value = "user-service")
public interface IUserFeignClient {

    @GetMapping(value = "/user-service/token",consumes = MediaType.APPLICATION_JSON_VALUE)
    ResultInfo<String> validToken(@RequestParam("token") String token);

}
