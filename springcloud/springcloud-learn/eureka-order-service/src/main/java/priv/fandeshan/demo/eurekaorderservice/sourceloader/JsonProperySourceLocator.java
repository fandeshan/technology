package priv.fandeshan.demo.eurekaorderservice.sourceloader;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * springboot 中提供的一个属性文件的扩展接口（在springboot启动的过程中会触发）
 */
@Slf4j
public class JsonProperySourceLocator implements PropertySourceLocator{

    private final static String DEFAULT_LOCATION = "classpath:my.json";

    private final ResourceLoader resourceLoader = new DefaultResourceLoader(getClass().getClassLoader());

    @Override
    public PropertySource<?> locate(Environment environment) {
    MyDefineJsonProperySource jsonProperySource = new MyDefineJsonProperySource("jsonPropertyConfig",mapPorpertySource());
        return jsonProperySource;
    }

    private Map<String, Object> mapPorpertySource(){
        Resource resource = this.resourceLoader.getResource(DEFAULT_LOCATION);
        if (resource == null) {
            return  null;
        }
        Map<String, Object> result = new HashMap<>();
        JsonParser jsonParser = JsonParserFactory.getJsonParser();
        Map<String,Object> fileMap = jsonParser.parseMap(readFile(resource));
        processorMap("",result,fileMap);
        return result;
    }

    private void processorMap(String prefix,Map<String,Object> result,Map<String,Object> fileMap){
        if (prefix != null && prefix.length() > 0){
            prefix += ".";
        }
        for (Map.Entry<String,Object>entrySet : fileMap.entrySet()){
            if (entrySet.getValue() instanceof Map){
                processorMap(prefix + entrySet.getKey(),result,(Map<String,Object>)entrySet.getValue());
            } else {
                result.put(prefix + entrySet.getKey(),entrySet.getValue());
            }
        }
    }

    private String readFile(Resource resource)  {
        FileInputStream fileInputStream = null;
        try{
            fileInputStream = new FileInputStream(resource.getFile());
            byte[] tmp = new byte[1024];
            int len;
            StringBuilder sb = new StringBuilder();
            while ((len = fileInputStream.read(tmp))!=-1){
                sb.append(new String(tmp,"UTF-8"));
            }
            return sb.toString();
        }catch (Exception e){
            log.error("read json file exception:",e);
        }finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    log.error("close file exception :",e);
                }
            }
        }
        return null;

    }

}
