### ***挂载磁盘*** 

```shell
df -h  #(查看磁盘占用情况)
```

![img](./img/wps1.jpg) 

```shell
fdisk -l  #(查看是否有未挂载的磁盘)
```

![img](./img/wps2.jpg) 

```shell
fdisk /dev/vdb   #(进入磁盘)
```

![img](./img/wps3.jpg) 

查看磁盘是否挂载上

```shell
fdisk -l
```

![img](./img/wps4.jpg) 

 ```shell
mkfs.ext3 /dev/vdb1   #(格式化磁盘)
 ```

![img](./img/wps5.jpg) 

```shell
partprobe  #(不重启服务器识别挂载的磁盘)

mkdir -p /data  #(创建文件夹)

mount /dev/vdb1 /data  #(将文件夹挂载到磁盘)

vi /etc/fstab   #(编辑fstab设置启动自动挂载)
```

![img](./img/wps6.jpg) 

```shell
df -h   (成功后查看磁盘占用情况)
```

