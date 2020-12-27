package com.fds.springboot.demo.annotation.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Train implements InitializingBean, DisposableBean {

    Train (){
        System.out.println("火车启动");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("火车开始启动加油");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("火车停车");
    }


}
