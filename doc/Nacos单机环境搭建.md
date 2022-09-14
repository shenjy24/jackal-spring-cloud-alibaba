### 一.环境安装

#### 1.安装Nacos

从 [nacos仓库](https://github.com/alibaba/nacos/releases) 下载安装包并进行解压。

#### 2.配置Nacos

Nacos 默认端口为 8848，因为和本机已有进程端口冲突，因此改为18848。配置文件是 conf 目录下的`application.properties`，修改配置为 `server.port=18848`。

#### 3.启动Nacos

（1）Linux/Unix/Mac

```
sh startup.sh -m standalone
```

（2）Windows

```
startup.cmd -m standalone
```

注：以上启动的是单机模式，要部署集群，请参考[集群部署说明](https://nacos.io/zh-cn/docs/cluster-mode-quick-start.html)。

#### 4.验证Nacos

打开 `localhost:18848/nacos` 即可打开 Nacos 控制台，`/nacos`为默认配置，可以通过`application.properties`中的`server.servlet.contextPath`配置进行修改。

默认用户名密码为：nacos/nacos

#### 5.关闭Nacos

（1）Linux/Unix/Mac

```
sh shutdown.sh
```

（2）Windows

```
shutdown.cmd
```



