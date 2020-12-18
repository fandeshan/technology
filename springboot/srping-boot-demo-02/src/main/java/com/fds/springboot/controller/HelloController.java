package com.fds.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class HelloController {

//    @Autowired
//    private RedissonClient redissonClient;
//
//    @GetMapping("/hello")
//    @LoadBalanced
//    private String hello(){
//        RBucket bucket = redissonClient.getBucket("name");
//        if ( bucket.get() == null) {
//            bucket.set("com.fds");
//        }
//        return bucket.get().toString();
//    }

    @Value("${server.port}")
    private Integer port;

    /**
     * 以下两个接口为了测试session共享，以两个不同端口启动该服务两个实例
     *  由于pom引入session存储到redis中，所以在一个实例调用set方法，
     *  另一个实例可以用get方法拿到session中的值
     *
     */
    @GetMapping(value = "/set")
    private String setSession(HttpSession httpSession){
        httpSession.setAttribute("name","fandeshan,"+port);
        return port+" set success";
    }

    @GetMapping(value = "/get")
    private String getSession(HttpSession httpSession){
        return port+" get session:"+httpSession.getAttribute("name");
    }

}
