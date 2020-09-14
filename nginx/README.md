### NGINX安装

tar包安装nginx

```shell
mkdri -p /usr/local/nginx/
```

解压nginx.tar包(在C:\work\soft\nginx目录下)

```shell
tar -zxvf nginx-1.15.10.tar.gz
```

安装依赖

```shell
yum install -y pcre-devel openssl openssl-devel
```

编译（以下两个选一个）：

1、

```shell
./configure  --prefix=/usr/local/nginx/  --sbin-path=/usr/local/nginx/sbin/nginx --conf-path=/usr/local/nginx/conf/nginx.conf --error-log-path=/var/log/nginx/error.log  --http-log-path=/var/log/nginx/access.log  --pid-path=/var/run/nginx/nginx.pid --lock-path=/var/lock/nginx.lock  --user=nginx --group=nginx --with-http_ssl_module --with-http_stub_status_module --with-http_gzip_static_module --http-client-body-temp-path=/var/tmp/nginx/client/ --http-proxy-temp-path=/var/tmp/nginx/proxy/ --http-fastcgi-temp-path=/var/tmp/nginx/fcgi/ --http-uwsgi-temp-path=/var/tmp/nginx/uwsgi --http-scgi-temp-path=/var/tmp/nginx/scgi --with-pcre
```

2、

```shell
./configure --prefix=/usr/local/nginx  \
--conf-path=/usr/local/nginx/etc/nginx.conf  \
--user=nginx --group=nginx  \
--error-log-path=/usr/local/nginx/nginxlog/error.log  \
--http-log-path=/usr/local/nginx/nginxlog/access.log  \
--pid-path=/usr/local/nginx/pids/nginx.pid  \
--lock-path=/usr/local/nginx/locks/nginx.lock  \
--with-http_ssl_module  \
--with-http_stub_status_module  \
--with-http_gzip_static_module  \
--http-client-body-temp-path=/usr/local/nginx/tmp/client  \
--http-proxy-temp-path=/usr/local/nginx/tmp/proxy  \
--http-fastcgi-temp-path=/usr/local/nginx/tmp/fastcgi  \
--http-uwsgi-temp-path=/usr/local/nginx/tmp/uwsgi  \
--http-scgi-temp-path=/usr/local/nginx/tmp/scgi
```

执行 如下命令，进行脚本级配置

```shell
make
```


进行 安装 ，命令如下：

```shell
make install
```

 进入 /usr/local/nginx/sbin

```shell
cd /usr/local/nginx/sbin
./nginx
```


如果遇到如下错误：nginx: [emerg] getpwnam("nginx") failed （没有安装nginx用户导致的无法启动），执行如下命令：

```shell
useradd -s /sbin/nologin -M nginx
```



重新 启动nginx 

```shell
./nginx
```

如果遇到如下错误：nginx: [emerg] mkdir() "/var/tmp/nginx/client/" failed (2: No such file or directory)由于目录没有创建，手动创建该目录

重新执行启动nginx命令

```shell
./nginx
```

配置nginx的systemctl 命令

```shell
vim /usr/lib/systemd/system/nginx.service
```

填写如下：

```shell
[Unit]
Description=nginx-The High-performance HTTP Server
After=network.target remote-fs.target nss-lookup.target

[Service]
Type=forking
PIDFile=/usr/local/nginx/logs/nginx.pid
ExecStartPre=/usr/local/nginx/sbin/nginx -t -c /usr/local/nginx/conf/nginx.conf
ExecStart=/usr/local/nginx/sbin/nginx -c /usr/local/nginx/conf/nginx.conf
ExecReload=/usr/local/nginx/sbin/nginx -s reload
ExecStop=/usr/local/nginx/sbin/nginx -s stop
ExecQuit=/usr/local/nginx/sbin/nginx -s quit
PrivateTmp=true

[Install]
WantedBy=multi-user.target
```

在启动服务之前，需要先重载systemctl命令

```shell
systemctl daemon-reload
```


启动nginx服务

```shell
systemctl start nginx
```

设置开机启动

```shell
systemctl enable nginx
```

