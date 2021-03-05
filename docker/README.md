# 安装docker

## 在mac 上安装docker
参考：https://docs.docker.com/docker-for-mac/install/

* 下载docker安装文件：[稳定版](https://download.docker.com/mac/stable/Docker.dmg), [最新版](https://download.docker.com/mac/edge/Docker.dmg)

* 安装运行docker  
![](./assets/2018-02-17-08-57-08.png)

* 申请自己的docker id，登陆；


## 在windows上安装docker  

参考： https://docs.docker.com/docker-for-windows/install/

## 在centos 7 上安装docker

* 执行如下脚本即可  

```bash 

# 安装docker
curl https://gitee.com/nickqiu/notes-everything/raw/master/docs/docker/install_docker.sh | bash  

# 安装docker-compose  
echo "start to dowload docker-compose...."
curl -L https://get.daocloud.io/docker/compose/releases/download/1.21.2/docker-compose-`uname -s`-`uname -m` > /usr/local/bin/docker-compose
chmod +x /usr/local/bin/docker-compose

```


### 指定容器运行根目录(可选)

```BASH
cat <<'EOF' >  /etc/docker/daemon.json
{
  "registry-mirrors" : [
    "https://registry.docker-cn.com"
  ],
  "data-root" : "/home/vagrant/data"
}
EOF

```


### 设置私有仓库方法(可选)  


```json 
cat <<'EOF'>/etc/docker/daemon.json
{
  "registry-mirrors" : [
    "https://registry.docker-cn.com"
  ],
  "insecure-registries" : [
    "20.250.204.146:6110"
  ]
}
EOF

```


##   登陆仓库

```
docker login -u name -p pasword 20.250.204.146:6110
```


# Docker打包Springboot项目

> 目标：用 Docker 的方式搭建一个 Java Spring Boot 应用


## 准备工作

操作前需要确保我们已经在本机上安装好了docker


## 操作步骤

* 创建一个spring boot项目，引入web依赖

* 创建控制类测试使用

```java
@RestController
public class TestControler {
    @RequestMapping("/")
    String index(){
        return "hello docker";
    }
}
```

* 创建Dockerfile

```Dockerfile
FROM java:8-jdk-alpine

ADD target/demo-*.jar /app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]
```


* 创建编译脚本build.sh

```bash
#!/usr/bin/env bash 
#mvn package 
docker build -t demo-spring:v1.0 . 
#docker run -d -p 8080:8080 demo-spring 
#docker tag demo-spring:v1.0 qiujiahong/demo-spring:1.0 
#docker push qiujiahong/demo-spring:1.0
```

* 运行容器

```
docker run  -p 8080:8080 demo-spring:v1.0
```

* 后台运行容器

```
docker run -d -p 8080:8080 demo-spring:v1.0
```

# 使用maven上传镜像到docker hub

## 设置账户

* 使用idea，pom.xml文件内右键 -> maven -> open 'settings.xml'

* 如下设置服务器,配置成自己真实的用户名和密码

```xml
<?xml version="1.0" encoding="UTF-8"?>
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">

    <servers>
        <server>
            <id>docker.io</id>
            <username>username</username>
            <password>password</password>
        </server>
    </servers>
</settings>
```

* 执行命令上传

    ```
    [linux]$ mvn clean package docker:build docker:push
    ```

* 查看上传结果

![](./assets/2018-02-23-16-04-22.png)

镜像的导入和导出
# 拉取镜像
docker pull gcr.io/google_samples/gb-redisslave:v1

# 保存镜像
docker save -o gb-redisslave.tar gcr.io/google_samples/gb-redisslave:v1

# 导入镜像
docker load -i gb-redisslave.tar 
应用： 因为网络原因，如上的google镜像我们无法访问，于是可以先在一台有科学上网的电脑上拉去并导出镜像，然后再在需要的主机上拉取对应镜像；

# docker常用命令


## 最常用命令

```
## 1.启动docker镜像
docker run hello-world
docker run  -d -p 80:80 -e name='nick' nginx     

-d - 后台运行
-p - 端口映射
-e - 变量
-v - 挂载目录或者文件

## 2.查询docker镜像/容器
docker image ls
docker image ls -a 
docker image ls -qa 
docker ps  
docker ps -a
docker ps -qa 
docker logs -f bd85e5bcffc5        # 查看日志
docker exec -it bd85e5bcffc5 bash  # 命令行进入该容器
docker inspect bd85e5bcffc5

## 3.停止/删除
docker stop bd85e5bcffc5
docker rm bd85e5bcffc5
docker ps -qa | xargs docker stop 
docker rmi bd85e5bcffc5             # 注意这里是删除镜像，删除前对应容器需要先删除
## Display Docker version and info
docker --version
docker version
docker info

## 编译docker镜像
docker build -t friendlyhello .  # Create image using this directory's Dockerfile
docker tag <image> username/repository:tag  # Tag <image> for upload to registry
docker push username/repository:tag            # Upload tagged image to registry
docker run username/repository:tag                   # Run image from a registry

## 提交一个正在运行的容器一个新镜像
docker commit c16378f943fe username/repository:tag
## 保存镜像到文件
docker save 66bc0f66b7af >/tmp/mysql.tar
docker save REPOSITORY:TAG>/tmp/mysql.tar
## 加载镜像
docker load < /tmp/new-image.tar
docker load --input rocketmq.tar
```
