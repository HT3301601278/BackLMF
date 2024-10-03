# 氨气监测系统后端

## 项目概述

这是一个基于Spring Boot的氨气监测系统后端项目。该系统旨在实时监控和管理氨气浓度,提供设备管理、数据收集、报警处理等功能。

## 技术栈

- Java 8+
- Spring Boot 2.x
- Spring Data JPA
- Spring Security
- WebSocket
- MySQL

## 项目结构

```
src/main/java/org/example/backlmf/
├── BacklmfApplication.java
├── config/
│   ├── CorsConfig.java
│   ├── SecurityConfig.java
│   └── WebSocketConfig.java
├── controller/
│   ├── AlarmController.java
│   ├── DataController.java
│   ├── DeviceController.java
│   └── UserController.java
├── entity/
│   ├── Alarm.java
│   ├── Data.java
│   ├── Device.java
│   └── User.java
├── exception/
│   ├── DeviceIdAlreadyExistsException.java
│   └── UsernameAlreadyExistsException.java
├── repository/
│   ├── AlarmRepository.java
│   ├── DataRepository.java
│   ├── DeviceRepository.java
│   └── UserRepository.java
├── service/
│   ├── AlarmService.java
│   ├── DataService.java
│   ├── DeviceService.java
│   └── UserService.java
└── websocket/
    └── AlarmWebSocketHandler.java
```

## 核心功能

1. 用户管理
2. 设备管理
3. 数据收集与存储
4. 报警处理
5. 实时通知 (WebSocket)

## 详细说明

### 1. 配置 (config/)

#### CorsConfig.java

配置跨域资源共享(CORS),允许前端应用与后端API进行通信。

#### SecurityConfig.java

配置Spring Security,处理身份验证和授权。

#### WebSocketConfig.java

配置WebSocket,用于实时通知功能。

### 2. 控制器 (controller/)

控制器处理HTTP请求,并将请求路由到相应的服务。

#### UserController.java

处理用户相关的请求,如注册、登录、获取用户信息等。

#### DeviceController.java

处理设备相关的请求,如添加设备、更新设备状态、关联设备与用户等。

#### DataController.java

处理数据相关的请求,如保存数据、获取特定时间范围内的数据等。

#### AlarmController.java

处理报警相关的请求,如创建报警、获取未处理的报警等。

### 3. 实体 (entity/)

实体类代表数据库中的表,定义了系统中的核心对象。

#### User.java

表示系统用户,包含用户名、密码等信息。

#### Device.java

表示监测设备,包含设备ID、类型、位置等信息。

#### Data.java

表示监测数据,包含氨气浓度、时间戳等信息。

#### Alarm.java

表示报警信息,包含阈值、状态、消息等信息。

### 4. 异常 (exception/)

自定义异常类,用于处理特定的业务逻辑错误。

### 5. 仓库 (repository/)

仓库接口定义了与数据库交互的方法,使用Spring Data JPA实现。

### 6. 服务 (service/)

服务类包含业务逻辑,处理控制器和仓库之间的数据流。

#### UserService.java

处理用户相关的业务逻辑,如注册、查找用户等。

#### DeviceService.java

处理设备相关的业务逻辑,如添加设备、更新设备状态等。

#### DataService.java

处理数据相关的业务逻辑,如保存数据、查询数据等。

#### AlarmService.java

处理报警相关的业务逻辑,如创建报警、处理报警等。

## 如何运行

1. 确保已安装Java 8+和Maven。

2. 克隆项目到本地。

3. 在`src/main/resources/application.properties`中配置数据库连接信息。

4. 在项目根目录下运行:

   ```
   mvn spring-boot:run
   ```

5. 应用将在`http://localhost:8080`上运行。

## API文档

API文档可以通过集成Swagger或Spring REST Docs生成。(注:当前项目中未包含API文档生成工具,建议未来添加)

## 安全性

本项目使用Spring Security进行身份验证和授权。请确保在生产环境中使用更安全的密码加密方法。

## 未来改进

1. 添加单元测试和集成测试
2. 实现更复杂的报警规则
3. 添加数据可视化功能
4. 实现设备远程控制功能
5. 优化数据存储和查询性能