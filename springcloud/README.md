## Spring Cloud 优雅下线方式

### 方式一：kill PID(kill -9 PID(更暴力))

​	该方式借助的是 Spring Boot 应用的 `Shutdown hook`，应用本身的下线也是优雅的，但如果你的服务发现组件使用的是 Eureka，那么默认最长会有 90 秒的延迟，其他应用才会感知到该服务下线，这意味着：该实例下线后的 90 秒内，其他服务仍然可能调用到这个已下线的实例。因此，该方式是不够优雅的

### 方式二：/shutdown端点

Spring Boot 提供了`/shutdown`端点，可以借助它实现优雅停机。

使用方式：在想下线应用的application.yml中添加如下配置，从而启用并暴露`/shutdown`端点：

````yaml
management:
  endpoint:
    shutdown:
      enabled: true
  endpoints:
    web:
      exposure:
        include: shutdown
````

然后发送 POST 请求到/shutdown端点

````shell
curl -X http://你想停止的服务地址/actuator/shutdown
````

该方式本质和方式一是一样的，也是借助 Spring Boot 应用的 Shutdown hook 去实现的。

### 方式三：/pause端点

Spring Boot 应用提供了`/pause`端点，利用该端点可实现优雅下线。

使用方式：在想下线应用的application.yml中添加配置，从而启用并暴露`/pause`端点：

````yaml
management:
  endpoint:
    # 启用pause端点
    pause:
      enabled: true
    # 启用restart端点，之所以要启用restart端点，是因为pause端点的启用依赖restart端点的启用
    restart:
      enabled: true
  endpoints:
    web:
      exposure:
        include: pause,restart
````

发送 POST 请求到`/actuator/pause`端点：

````shell
curl -X POST http://你想停止的服务实例地址/actuator/pause
````

执行后的效果类似下图

![image-20220506150134062](./img/image-20220506150134062.png)

如图所示，该应用在 Eureka Server 上的状已被标记为DOWN，但是应用本身其实依然是可以正常对外服务的。在 Spring Cloud 中，Ribbon 做负载均衡时，只会负载到标记为UP的实例上。

利用这两点，你可以：先用`/pause`端点，将要下线的应用标记为DOWN，但不去真正停止应用；然后过一定的时间（例如 90 秒，或者自己做个监控（metrics），看当前实例的流量变成 0 后）再去停止应用，例如`kill`应用。

#### **缺点 & 局限**

- 不同版本配置不太一样,在早期的Spring Cloud版本中，pause端点是不依赖restart端点的
- 无法和Eureka的健康检查配合使用；如果你的服务发现组件用的是Eureka，并且你的应用开启了健康检查eureka.client.healthcheck.enabled=true，那么/pause端点无效

### 方式四：/service-registry端点

使用方式：在想下线应用的application.yml中添加配置，从而暴露`/service-registry`端点：

````yaml
management:
  endpoints:
    web:
      exposure:
        include: service-registry
````

发送 POST 请求到`/actuator/service-registry`端点：

````shell
curl -X "POST" "http://localhost:8000/actuator/service-registry?status=DOWN" \
   -H "Content-Type: application/vnd.spring-boot.actuator.v2+json;charset=UTF-8"
````

实行后的效果类似如下图

![image-20220506150134062](./img/image-20220506150134062.png)

### 优雅的下线方式

在上文中，我们讲述了四种常见的下线方式，对比来看，方式四是一种比较优雅的下线方式。

在实际项目中，我们可以先使用`/service-registry`端点，将服务标记为DOWN，然后监控服务的流量，当流量为 0 时，即可升级该服务。当然，这里假设我们部署了多个服务实例，当一个服务实例DOWN掉之后，其他服务实例仍然是可以提供服务的，如果就部署一台服务的话，那么讨论优不优雅就没那么重要了。

除了上述的下线方式之外，还有一种利用`EurekaAutoServiceRegistration`对象达到优雅下线的目标。

- 执行`eurekaAutoServiceRegistration.start()`方法时，当前服务向 Eureka 注册中心注册服务；
- 执行`eurekaAutoServiceRegistration.stop()`方法时，当前服务会向 Eureka 注册中心进行反注册，注册中心收到请求后，会将此服务从注册列表中删除。

示例代码如下：

````java
@RestController
@RequestMapping(value = "/graceful/registry-service")
public class GracefulOffline {

    @Autowired
    private EurekaAutoServiceRegistration eurekaAutoServiceRegistration;

    @RequestMapping("/online")
    public String online() {
        this.eurekaAutoServiceRegistration.start();
        return "execute online method, online success.";
    }

    @RequestMapping("/offline")
    public String offline() {
        this.eurekaAutoServiceRegistration.stop();
        return "execute offline method, offline success.";
    }
}
````

到这里，我们已经介绍了两种相对优雅的下线方式了。具体如何操作，我们可以根据实际上情况进行包装，或者利用自动化的脚本来实现更加优雅的下线方式。

## 灰度发布



### 蓝绿部署



### 	滚动部署



### 金丝雀部署

