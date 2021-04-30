### 使用Minikube搭建单节点K8s

**Windows**

kubectl官网 ：https://kubernetes.io/docs/tasks/tools/install-kubectl/#install-kubectl-on-win

dows

minikube官网 ：https://kubernetes.io/docs/tasks/tools/install-minikube/

选择任意一种虚拟化的方式

```
• Hyper-V 

• VirtualBox
```

安装kubectl

```
(1)根据官网步骤 [或] 直接下载: 
https://storage.googleapis.com/kubernetes-release/release/v1.16.2/bin/windows/amd64/kubectl.exe 

(2)配置kubectl.exe所在路径的环境变量，使得cmd窗口可以直接使用kubectl命令 

(4)kubectl version检查是否配置成功 
```

安装minikube

```
(1)根据官网步骤 [或] 直接下载: 
https://github.com/kubernetes/minikube/releases/download/v1.5.2/minikube-windows-amd64.exe 

(2)修改minikube-windows-amd64.exe名称为minikube.exe 

(3)配置minikube所在路径的环境变量，使得cmd窗口可以直接使用minikube命令 

(4)minikube version检查是否配置成功 
```

使用minikube创建单节点的k8s

```shell
minikube start --vm-driver=virtualbox --image-repository=gcr.azk8s.cn/google-containers
```

小结

**其实就是通过minikube创建一个虚拟机**

**这个虚拟机中安装好了单节点的K8S环境然后通过kubectl进行交互CentOS**

```shell
# 创建K8S 
minikube start 

# 删除K8S 
minikube delete 

# 进入到K8S的机器中 
minikube ssh 

# 查看状态 
minikube status 

# 进入dashboard 
minikube dashboard
```

**CentOS**

kubectl官网 ：https://kubernetes.io/docs/tasks/tools/install-kubectl/#install-kubectl-on-linux

minikube官网 ：https://kubernetes.io/docs/tasks/tools/install-minikube/

安装docker

安装kubectl

```shell
# 01 下载[kubectl&minikube,传到自己的centos7机器中。] 

# 02 授权 
chmod +x ./kubectl 

# 03 添加到环境变量 
sudo mv ./kubectl /usr/local/bin/kubectl 

# 04 检查 
kubectl version
```

安装minikube

```shell
# 01 下载[kubectl&minikube,上传到自己的centos7机器中。] 
wget https://github.com/kubernetes/minikube/releases/download/v1.5.2/minikube-linux-amd64 

# 02 配置环境变量 
sudo mv minikube-linux-amd64 minikube && chmod +x minikube && mv minikube /usr/local/bin/ 

# 03 检查 
minikube version
```

使用minikube创建单节点的k8s

```shell
minikube start --vm-driver=none --image-repository=gcr.azk8s.cn/google- containers
```

**Mac OS**

也是下载安装kubectl和minikube，选择virtualbox，然后minikube start，就可以通过kubectl操作咯



# 01 搭建K8s集群[无需科学上网]

> `官网`：<https://kubernetes.io/docs/setup/production-environment/tools/kubeadm/install-kubeadm/#installing-kubeadm-kubelet-and-kubectl>
>
> `GitHub`：https://github.com/kubernetes/kubeadm
>
> `课程中`：使用kubeadm搭建一个3台机器组成的k8s集群，1台master节点，2台worker节点
>
> **如果大家机器配置不够，也可以使用在线的，或者minikube的方式或者1个master和1个worker**
>
> `配置要求`：
>
> - One or more machines running one of:
>   - Ubuntu 16.04+
>   - Debian 9+
>   - CentOS 7【课程中使用】
>   - Red Hat Enterprise Linux (RHEL) 7
>   - Fedora 25+
>   - HypriotOS v1.0.1+
>   - Container Linux (tested with 1800.6.0)
> - 2 GB or more of RAM per machine (any less will leave little room for your apps)
> - 2 CPUs or more
> - Full network connectivity between all machines in the cluster (public or private network is fine)
> - Unique hostname, MAC address, and product_uuid for every node. See here for more details.
> - Certain ports are open on your machines. See here for more details.
> - Swap disabled. You **MUST** disable swap in order for the kubelet to work properly.

## 1.1 版本统一

```
Docker       18.09.0
---
kubeadm-1.14.0-0 
kubelet-1.14.0-0 
kubectl-1.14.0-0
---
k8s.gcr.io/kube-apiserver:v1.14.0
k8s.gcr.io/kube-controller-manager:v1.14.0
k8s.gcr.io/kube-scheduler:v1.14.0
k8s.gcr.io/kube-proxy:v1.14.0
k8s.gcr.io/pause:3.1
k8s.gcr.io/etcd:3.3.10
k8s.gcr.io/coredns:1.3.1
---
calico:v3.9
```

## 1.2 准备3台centos

大家根据自己的情况来准备centos7的虚拟机。

要保证彼此之间能够ping通，也就是处于同一个网络中，虚拟机的配置要求上面也描述咯。

## 1.3 更新并安装依赖

> 3台机器都需要执行

```shell
yum -y update
yum install -y conntrack ipvsadm ipset jq sysstat curl iptables libseccomp
```

## 1.4 安装Docker

> 根据之前学习的Docker方式[Docker第一节课的笔记中也有这块的说明]
>
> 在每一台机器上都安装好Docker，版本为18.09.0
>
> ```shell
> 01 安装必要的依赖
> 	sudo yum install -y yum-utils \
>  device-mapper-persistent-data \
>  lvm2
>  
>  
> 02 设置docker仓库
> 	sudo yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
> 	
> 【设置要设置一下阿里云镜像加速器】
> sudo mkdir -p /etc/docker
> sudo tee /etc/docker/daemon.json <<-'EOF'
> {
> "registry-mirrors": ["这边替换成自己的实际地址"]
> }
> EOF
> sudo systemctl daemon-reload
> 
> 
> 03 安装docker
> 
> yum install -y docker-ce-18.09.0 docker-ce-cli-18.09.0 containerd.io
> 
> 
> 04 启动docker
> 	sudo systemctl start docker && sudo systemctl enable docker
> ```

## 1.5 修改hosts文件

> (1)master

```shell
# 设置master的hostname，并且修改hosts文件
sudo hostnamectl set-hostname m

vi /etc/hosts
192.168.8.51 m
192.168.8.61 w1
192.168.8.62 w2
```

> (2)两个worker

```shell
# 设置worker01/02的hostname，并且修改hosts文件
sudo hostnamectl set-hostname w1
sudo hostnamectl set-hostname w2

vi /etc/hosts
192.168.8.51 m
192.168.8.61 w1
192.168.8.62 w2
```

> (3)使用ping测试一下

## 1.6 系统基础前提配置

```shell
# (1)关闭防火墙
systemctl stop firewalld && systemctl disable firewalld

# (2)关闭selinux
setenforce 0
sed -i 's/^SELINUX=enforcing$/SELINUX=permissive/' /etc/selinux/config

# (3)关闭swap
swapoff -a
sed -i '/swap/s/^\(.*\)$/#\1/g' /etc/fstab

# (4)配置iptables的ACCEPT规则
iptables -F && iptables -X && iptables -F -t nat && iptables -X -t nat && iptables -P FORWARD ACCEPT

# (5)设置系统参数
cat <<EOF >  /etc/sysctl.d/k8s.conf
net.bridge.bridge-nf-call-ip6tables = 1
net.bridge.bridge-nf-call-iptables = 1
EOF

sysctl --system
```

## 1.7 Installing kubeadm, kubelet and kubectl

> (1)配置yum源

```shell
cat <<EOF > /etc/yum.repos.d/kubernetes.repo
[kubernetes]
name=Kubernetes
baseurl=http://mirrors.aliyun.com/kubernetes/yum/repos/kubernetes-el7-x86_64
enabled=1
gpgcheck=0
repo_gpgcheck=0
gpgkey=http://mirrors.aliyun.com/kubernetes/yum/doc/yum-key.gpg
       http://mirrors.aliyun.com/kubernetes/yum/doc/rpm-package-key.gpg
EOF
```

> (2)安装kubeadm&kubelet&kubectl

```shell
yum install -y kubeadm-1.14.0-0 kubelet-1.14.0-0 kubectl-1.14.0-0
```

> (3)docker和k8s设置同一个cgroup

```shell
# docker
vi /etc/docker/daemon.json
    "exec-opts": ["native.cgroupdriver=systemd"],
    
systemctl restart docker
    
# kubelet，这边如果发现输出directory not exist，也说明是没问题的，大家继续往下进行即可
sed -i "s/cgroup-driver=systemd/cgroup-driver=cgroupfs/g" /etc/systemd/system/kubelet.service.d/10-kubeadm.conf
	
systemctl enable kubelet && systemctl start kubelet
```

## 1.8 proxy/pause/scheduler等国内镜像

> (1)查看kubeadm使用的镜像
>
> kubeadm config images list
>
> 可以发现这里都是国外的镜像

```
k8s.gcr.io/kube-apiserver:v1.14.0
k8s.gcr.io/kube-controller-manager:v1.14.0
k8s.gcr.io/kube-scheduler:v1.14.0
k8s.gcr.io/kube-proxy:v1.14.0
k8s.gcr.io/pause:3.1
k8s.gcr.io/etcd:3.3.10
k8s.gcr.io/coredns:1.3.1
```

> (2)解决国外镜像不能访问的问题

- 创建kubeadm.sh脚本，用于拉取镜像/打tag/删除原有镜像

```shell
#!/bin/bash

set -e

KUBE_VERSION=v1.14.0
KUBE_PAUSE_VERSION=3.1
ETCD_VERSION=3.3.10
CORE_DNS_VERSION=1.3.1

GCR_URL=k8s.gcr.io
ALIYUN_URL=registry.cn-hangzhou.aliyuncs.com/google_containers

images=(kube-proxy:${KUBE_VERSION}
kube-scheduler:${KUBE_VERSION}
kube-controller-manager:${KUBE_VERSION}
kube-apiserver:${KUBE_VERSION}
pause:${KUBE_PAUSE_VERSION}
etcd:${ETCD_VERSION}
coredns:${CORE_DNS_VERSION})

for imageName in ${images[@]} ; do
  docker pull $ALIYUN_URL/$imageName
  docker tag  $ALIYUN_URL/$imageName $GCR_URL/$imageName
  docker rmi $ALIYUN_URL/$imageName
done
```

- 运行脚本和查看镜像

```
# 运行脚本
sh ./kubeadm.sh

# 查看镜像
docker images
```

- 将这些镜像推送到自己的阿里云仓库【可选，根据自己实际的情况】

```shell
# 登录自己的阿里云仓库
docker login --username=xxx registry.cn-hangzhou.aliyuncs.com
```

```shell
#!/bin/bash

set -e

KUBE_VERSION=v1.14.0
KUBE_PAUSE_VERSION=3.1
ETCD_VERSION=3.3.10
CORE_DNS_VERSION=1.3.1

GCR_URL=k8s.gcr.io
ALIYUN_URL=xxx

images=(kube-proxy:${KUBE_VERSION}
kube-scheduler:${KUBE_VERSION}
kube-controller-manager:${KUBE_VERSION}
kube-apiserver:${KUBE_VERSION}
pause:${KUBE_PAUSE_VERSION}
etcd:${ETCD_VERSION}
coredns:${CORE_DNS_VERSION})

for imageName in ${images[@]} ; do
  docker tag $GCR_URL/$imageName $ALIYUN_URL/$imageName
  docker push $ALIYUN_URL/$imageName
  docker rmi $ALIYUN_URL/$imageName
done
```

> 运行脚本 sh ./kubeadm-push-aliyun.sh

## 1.9 kube init初始化master

> (1)kube init流程

```
01-进行一系列检查，以确定这台机器可以部署kubernetes

02-生成kubernetes对外提供服务所需要的各种证书可对应目录
/etc/kubernetes/pki/*

03-为其他组件生成访问kube-ApiServer所需的配置文件
    ls /etc/kubernetes/
    admin.conf  controller-manager.conf  kubelet.conf  scheduler.conf
    
04-为 Master组件生成Pod配置文件。
    ls /etc/kubernetes/manifests/*.yaml
    kube-apiserver.yaml 
    kube-controller-manager.yaml
    kube-scheduler.yaml
    
05-生成etcd的Pod YAML文件。
    ls /etc/kubernetes/manifests/*.yaml
    kube-apiserver.yaml 
    kube-controller-manager.yaml
    kube-scheduler.yaml
	etcd.yaml
	
06-一旦这些 YAML 文件出现在被 kubelet 监视的/etc/kubernetes/manifests/目录下，kubelet就会自动创建这些yaml文件定义的pod，即master组件的容器。master容器启动后，kubeadm会通过检查localhost：6443/healthz这个master组件的健康状态检查URL，等待master组件完全运行起来

07-为集群生成一个bootstrap token

08-将ca.crt等 Master节点的重要信息，通过ConfigMap的方式保存在etcd中，工后续部署node节点使用

09-最后一步是安装默认插件，kubernetes默认kube-proxy和DNS两个插件是必须安装的
```

> (2)初始化master节点
>
> 官网：<https://kubernetes.io/docs/reference/setup-tools/kubeadm/kubeadm/>
>
> `注意`：**此操作是在主节点上进行**

```
# 本地有镜像
kubeadm init --kubernetes-version=1.14.0 --apiserver-advertise-address=192.168.8.51 --pod-network-cidr=10.244.0.0/16
【若要重新初始化集群状态：kubeadm reset，然后再进行上述操作】
```

**记得保存好最后kubeadm join的信息**

> (3)根据日志提示

```
mkdir -p $HOME/.kube
sudo cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
sudo chown $(id -u):$(id -g) $HOME/.kube/config
```

**此时kubectl cluster-info查看一下是否成功**

> (4)查看pod验证一下
>
> 等待一会儿，同时可以发现像etc，controller，scheduler等组件都以pod的方式安装成功了
>
> `注意`：coredns没有启动，需要安装网络插件

```
kubectl get pods -n kube-system
```

> (5)健康检查

```
curl -k https://localhost:6443/healthz
```

## 1.10 部署calico网络插件

> 选择网络插件：<https://kubernetes.io/docs/concepts/cluster-administration/addons/>
>
> calico网络插件：<https://docs.projectcalico.org/v3.9/getting-started/kubernetes/>

> `calico，同样在master节点上操作`

```
# 在k8s中安装calico
kubectl apply -f https://docs.projectcalico.org/v3.9/manifests/calico.yaml

# 确认一下calico是否安装成功
kubectl get pods --all-namespaces -w
```

## 1.11 kube join

> **记得保存初始化master节点的最后打印信息【注意这边大家要自己的，下面我的只是一个参考】**

```
kubeadm join 192.168.0.51:6443 --token yu1ak0.2dcecvmpozsy8loh \
    --discovery-token-ca-cert-hash sha256:5c4a69b3bb05b81b675db5559b0e4d7972f1d0a61195f217161522f464c307b0
```

> (1)在woker01和worker02上执行上述命令

> (2)在master节点上检查集群信息

```
kubectl get nodes

NAME                   STATUS   ROLES    AGE     VERSION
master-kubeadm-k8s     Ready    master   19m     v1.14.0
worker01-kubeadm-k8s   Ready    <none>   3m6s    v1.14.0
worker02-kubeadm-k8s   Ready    <none>   2m41s   v1.14.0
```

## 1.12 再次体验Pod

> (1)定义pod.yml文件，比如pod_nginx_rs.yaml

```yml
cat > pod_nginx_rs.yaml <<EOF
apiVersion: apps/v1
kind: ReplicaSet
metadata:
  name: nginx
  labels:
    tier: frontend
spec:
  replicas: 3
  selector:
    matchLabels:
      tier: frontend
  template:
    metadata:
      name: nginx
      labels:
        tier: frontend
    spec:
      containers:
      - name: nginx
        image: nginx
        ports:
        - containerPort: 80
EOF
```

> (2)根据pod_nginx_rs.yml文件创建pod

```
kubectl apply -f pod_nginx_rs.yaml
```

> (3)查看pod

```
kubectl get pods
kubectl get pods -o wide
kubectl describe pod nginx
```

> (4)感受通过rs将pod扩容

```
kubectl scale rs nginx --replicas=5
kubectl get pods -o wide
```

> (5)删除pod

```
kubectl delete -f pod_nginx_rs.yaml
```

