package priv.fandeshan.demo.test.service.impl;

import priv.fandeshan.demo.mvcframework.annotation.FDSService;
import priv.fandeshan.demo.test.service.ITestService;

@FDSService
public class TestServiceImpl implements ITestService {
    @Override
    public String find(String name) {
        return "find name:"+name+",from service";
    }
}
