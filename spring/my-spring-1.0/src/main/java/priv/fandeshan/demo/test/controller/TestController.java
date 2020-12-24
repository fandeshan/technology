package priv.fandeshan.demo.test.controller;

import priv.fandeshan.demo.mvcframework.annotation.FDSAutowired;
import priv.fandeshan.demo.mvcframework.annotation.FDSController;
import priv.fandeshan.demo.mvcframework.annotation.FDSRequestMapping;
import priv.fandeshan.demo.mvcframework.annotation.FDSRequestParam;
import priv.fandeshan.demo.test.service.ITestService;

@FDSController
@FDSRequestMapping("/test")
public class TestController {

    @FDSAutowired
    private ITestService testService;

    @FDSRequestMapping("/find")
    public String find(@FDSRequestParam("name") String name){
        String result = "";
        result = testService.find(name);
        return result;
    }
}
