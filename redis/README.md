### REDIS集群安装

集群需要三台服务器，192.168.31.7、192.168.31.73、192.168.31.209
服务器和端口号的对应关系如下：
192.168.31.7 7000/7001
192.168.31.73 7002/7003
192.168.31.209 7004/7005
下面以7000端口为例进行说明
登录192.168.31.7，拿到redis的压缩包（C:\work\soft\redis目录下），拷贝到/data目录下
解压

```shell
cd /data
tar -xvf redis-5.0.5.tar
cd redis-5.0.5
```

创建目录cluster

```shell
mkdir -p cluster/7000
```

创建集群配置文件

```shell
vim redis.conf
```

```shell
port  7000
bind 192.168.31.7
daemonize    yes
appendonly  no
maxmemory 10G
pidfile  /var/run/redis_7000.pid
cluster-enabled  yes
cluster-config-file  nodes_7000.conf
cluster-node-timeout  15000
requirepass "password" #（此处修改为程序中使用的密码）
masterauth "password " #（此处修改为程序中使用的密码）
dir /data/redis-5.0.5/cluster/7000/
logfile /data/redis-5.0.5/cluster/7000/redis.log
```

启动redis服务器

```shell
cd /data/redis-5.0.5
./src/redis-server cluster/7000/redis.conf
```

其他服务器一样的方式配置，启动redis服务器

执行redis集群创建命令

```shell
./redis-cli --cluster create 192.168.31.7:7000  192.168.31.7:7001 192.168.31.73:7002 192.168.31.73:7003 192.168.31.209:7004 192.168.31.209:7005 --cluster-replicas 1
```

进入集群环境，验证集群

```
./src/redis-cli -c -h 192.168.31.7 -p 7000
```

执行auth “password”,验证密码
执行cluster info，如果cluster state状态为ok，则集群创建成功

```
cluster_state:ok
cluster_slots_assigned:16384
cluster_slots_ok:16384
cluster_slots_pfail:0
cluster_slots_fail:0
cluster_known_nodes:6
cluster_size:3
cluster_current_epoch:6
cluster_my_epoch:3
cluster_stats_messages_ping_sent:3009066
cluster_stats_messages_pong_sent:2908868
cluster_stats_messages_meet_sent:5
cluster_stats_messages_sent:5917939
cluster_stats_messages_ping_received:2908867
cluster_stats_messages_pong_received:3009071
cluster_stats_messages_meet_received:1
cluster_stats_messages_received:5917939
```

执行cluster nodes，输出如下结果，则证明所有槽已经分配，集群可以正常运行

```
0b58d73e5b57c99954f9ffdae8954424ee57cdf3 192.168.31.7:7000@17000 master - 0 1582100542000 1 connected 0-5460
278cbb4de0e4fe1aba5f2e4811bd241be767c938 192.168.31.7:7001@17001 slave 68aaf1aed60edd7bebf7cb6137657abe088d09b9 0 1582100541410 5 connected
68aaf1aed60edd7bebf7cb6137657abe088d09b9 192.168.31.209:7004@17004 master - 0 1582100542412 5 connected 10923-16383
294eabd14122b09583b1b121b83f1bc205bdce6a 192.168.31.73:7002@17002 myself,master - 0 1582100542000 3 connected 5461-10922
63b4d5059337c1941a5711cbaafea3e5e711cf3b 192.168.31.209:7005@17005 slave 294eabd14122b09583b1b121b83f1bc205bdce6a 0 1582100541000 6 connected
a6939e63d35d80c785f4555ebd439e9f321eb967 192.168.31.73:7003@17003 slave 0b58d73e5b57c99954f9ffdae8954424ee57cdf3 0 1582100543414 4 connected
```