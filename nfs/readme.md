**安装**

在master节点上搭建一个NFS服务器，目录为/nfs/data

```shell
#nfs(network file system)网络文件系统，是FreeBSD支持的文件系统中的一种，允许网络中的计算机之间通过TCP/IP网络共享资源

	# 安装nfs
	yum install -y nfs-utils
	# 创建nfs目录
	mkdir -p /nfs/data/
	mkdir -p /nfs/data/mysql
	# 授予权限
	chmod -R 777 /nfs/data
	# 编辑export文件
	vi /etc/exports
	  /nfs/data *(rw,no_root_squash,sync)
	# 使得配置生效
	exportfs -r
	# 查看生效
	exportfs
	# 启动rpcbind、nfs服务
	systemctl restart rpcbind && systemctl enable rpcbind
	systemctl restart nfs && systemctl enable nfs
	# 查看rpc服务的注册情况
	rpcinfo -p localhost
	# showmount测试
	showmount -e master-ip
```

 所有node上安装客户端

```shell
yum -y install nfs-utils
systemctl start nfs && systemctl enable nfs
```





