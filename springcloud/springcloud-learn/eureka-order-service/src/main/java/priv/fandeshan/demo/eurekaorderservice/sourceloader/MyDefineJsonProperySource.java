package priv.fandeshan.demo.eurekaorderservice.sourceloader;

import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.util.StringUtils;

import java.util.Map;

public class MyDefineJsonProperySource extends EnumerablePropertySource<Map<String,Object>> {

    public MyDefineJsonProperySource(String name, Map<String, Object> source) {
        super(name, source);
    }

    @Override
    public String[] getPropertyNames() {
        return StringUtils.toStringArray(this.source.keySet());
    }

    @Override
    public Object getProperty(String s) {
        return this.source.get(s);
    }
}
