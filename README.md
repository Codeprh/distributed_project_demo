# 项目概述

本项目用于搭建日常分布式的demo系统。



## 搭建一个dubbo+springboot的demo项目

- 搭建springBoot项目
  1. [通过spring官网下载](https://start.spring.io/)
  2. 通过idea图形化界面操作
- 项目结构图

![image-20181217155254184](https://ws4.sinaimg.cn/large/006tNbRwgy1fy9sn0oq8tj307r028t8p.jpg)

- 结构介绍
  1. dubbo_demo_base：定义接口服务
  2. dubbo_demo_consumer：定义消费者
  3. dubbo_demo_provider：定义生产者

## 快速搭建搭建RocketMQ和RocketMQ-Console

- [下载rocketmq](https://rocketmq.apache.org/)

```shell
  > unzip rocketmq-all-4.3.2-source-release.zip
  > cd rocketmq-all-4.3.2/
  > mvn -Prelease-all -DskipTests clean install -U
  > cd distribution/target/apache-rocketmq
```

- 启动nameServer

```shell
  > nohup sh bin/mqnamesrv &
  > tail -f ~/logs/rocketmqlogs/namesrv.log
  The Name Server boot success...
```

- 启动broker

```shell
  > nohup sh bin/mqbroker -n localhost:9876 &
  > tail -f ~/logs/rocketmqlogs/broker.log 
  The broker[%s, 172.30.30.233:10911] boot success...
  > export NAMESRV_ADDR=localhost:9876
```

- 关闭服务

```shell
sh bin/mqshutdown broker
sh bin/mqshutdown namesrv
```

[搭建RocketMQ-Console](https://github.com/apache/rocketmq-externals/blob/master/rocketmq-console/README.md)

1. Docker启动

   ```shell
   #加载镜像
   docker pull styletang/rocketmq-console-ng
   #启动容器，需求修改namesvrAddr的ip地址和端口号
   docker run -e "JAVA_OPTS=-Drocketmq.namesrv.addr=127.0.0.1:9876 -Dcom.rocketmq.sendMessageWithVIPChannel=false" -p 8080:8080 -t styletang/rocketmq-console-ng
   
   
   ```

2. 非docker启动

   ```shell
   #第一步：先拉取源码
   git clone https://github.com/apache/rocketmq-externals/tree/master/rocketmq-console
   #第二步：进入目录，构建项目
   mvn spring-boot:run
   
   
   ```
