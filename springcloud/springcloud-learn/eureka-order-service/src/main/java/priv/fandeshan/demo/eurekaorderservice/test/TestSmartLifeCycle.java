package priv.fandeshan.demo.eurekaorderservice.test;

import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

@Component
public class TestSmartLifeCycle implements SmartLifecycle {
    @Override
    public void start() {
        System.out.println("------start ");
    }

    @Override
    public void stop() {
        System.out.println("------stop ");
    }

    @Override
    public boolean isRunning() {
        System.out.println("------running ");
        return false;
    }
}
