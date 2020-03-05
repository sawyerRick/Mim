# Mim
分布式IM系统，用于学习分布式架构，学习ZooKeeper。

## 背景

学了计算机网络后，一直想实现一个IM。从Python的Socket实现，Java的Socket实现，Java的Nio实现[ChatServer](https://github.com/sawyerRick/ChatServer)到Mim。

Mim是一个分布式及时通讯系统。分为三个端：客户端、路由端、服务端。

## 架构

路由端和服务端支持水平扩展。

![架构](/Users/sawyerrick/Desktop/mim/IMG/架构.png)

## Usage

### 部署

打包：

```shell
mvn clean package
```

路由启动：

```shell
java -jar mim-router/target/mim-router-0.0.1-SNAPSHOT.jar
```

服务端启动：

```
java -jar  mim-server/target/mim-server-0.0.1-SNAPSHOT.jar
```

客户端启动：

注册：

```shell
curl -H "Accept: application/json" -H "Content-type: application/json" -X POST -d '{"username":"serial"}'  http://localhost:8081/registry

curl -H "Accept: application/json" -H "Content-type: application/json" -X POST -d '{"username":"sawyer"}'  http://localhost:8081/registry
```

client1:

```shell
java -jar mim-client/target/mim-client-0.0.1-SNAPSHOT.jar --server.port=9090 --mim.config.userId=2369058761398941 --mim.config.username=serial

```

client2:

```shell
java -jar mim-client/target/mim-client-0.0.1-SNAPSHOT.jar --server.port=9091 --mim.config.userId=2369106717063327 --mim.config.username=sawyer

```

### 运行

Client：

![Client运行截图](/Users/sawyerrick/Desktop/mim/IMG/Client运行截图.png)

Server心跳：

![Server心跳](/Users/sawyerrick/Desktop/mim/IMG/Server心跳.png)

Router路由：

![路由截图](/Users/sawyerrick/Desktop/mim/IMG/路由截图.png)

## TODO

- 添加更多内置命令
- 加密安全：TSL/SSL
- 优化Client界面
- 改用protobuf序列化
- ...

## LISENCE

MIT

### 感谢

[cim](https://github.com/crossoverJie/cim)