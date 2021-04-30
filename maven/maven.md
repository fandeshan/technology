



maven deploy本地的jar包及pom文件到私服

```shell
mvn deploy:deploy-file -Dfile=C:\work\cpgroup\pd-common-2.1.06.jar -DgroupId=com.paic.pd.common -DartifactId=pd-common -Dversion=2.1.06 -Dpackaging=jar -Durl=http://192.168.30.208:8111/nexus/repository/maven-releases/  -DpomFile=C:\work\cpgroup\pd-common-2.1.06.pom -DrepositoryId=nexus
```



401错误问题排查

1、查看当前生效setting文件

```shell
mvn help:effective-settings
```

比对repositoryId设置和setting文件中的server是否一致，以及setting文件本身配置的正确性