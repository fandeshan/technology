### JDK安装配置

拿到jdk的压缩包（C:\work\soft\java\目录下）
解压

```shell
tar xvf jdk1.8.0_211.tar
```

配置环境变量

```shell
vim /etc/profile
```

追加如下内容

```shell
export JAVA_HOME=/data/server/jdk1.8.0_211
export PATH=$PATH:${JAVA_HOME}/bin
export CLASSPATH=.:${JAVA_HOME}/jre/lib/rt.jar:${JAVA_HOME}/lib/dt.jar:${JAVA_HOME}/lib/tools.jar
```

```shell
source /etc/profile
```

查看配置是否生效

```shell
java -version
```

