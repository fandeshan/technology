### **配置yum源**

#### ***使用ISO镜像配置本地yun源***

CentOS使用ISO镜像做本地yum源（需要网上去找ISO镜像）

```shell
mkdir -p /mnt/cdrom
```

上传iso镜像文件到/var/opt/isos目录下面

挂载到/mnt/cdrom 

 ```shell
mount -o loop var/opt/isos/CentOS-7-x86_64-Everything-1908.iso /mnt/cdrom
 ```

使用df -h命令查询挂载是否成功

```shell
df -h
```



创建repo文件并放到/etc/yum.repos.d/目录

```shell
cd /etc/yum.repos.d
```



​	编辑配置文件

```shell
vi local.repo
```

 ```shell
[local]
name=Extra Packages for Enterprise Linux 7 - $basearch
baseurl=file:///mnt/cdrom
failovermethod=priority
enabled=1
gpgcheck=1
gpgkey=file:///mnt/cdrom/RPM-GPG-KEY-CentOS-7
 ```

测试YUM安装

```shell
yum clean all
yum install ntp 
```

#### ***配置华为云yum源***

执行命令备份centos-base.repo

```shell
 mkdir -p /etc/yum.repos.d/repo_bak/
 mv /etc/yum.repos.d/*.repo /etc/yum.repos.d/repo_bak/
```

Curl获取相应版本的repo文件

```shell
curl -o /etc/yum.repos.d/CentOS-Base.repo http://mirrors.myhuaweicloud.com/repo/CentOS-Base-7.repo
 yum clean all
 yum makecache
```

####  ***离线安装rpm包自动解决依赖***

在有网络的机器安装

```shell
yum install -y yum-plugin-downloadonly
```

创建目录用来缓存rpm包

 ```shell
mkdir /data/rpm -p
 ```

制定download目录

```shell
yum install --downloadonly --downloaddir=/data/rpm  gem
```

去/data/rpm目录找到对应的rpm包，拷贝到内网机器，就ok了