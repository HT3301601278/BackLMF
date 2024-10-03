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

## API 文档

### 基础URL

所有API都以以下基础URL开头:

```
http://localhost:8080/api
```

### 认证

除了特别说明的API外,所有API都需要进行Basic Auth认证。认证信息如下:

- 用户名: [注册用户的用户名]
- 密码: [注册用户的密码]

### API 列表

#### 1. 用户管理

##### 1.1 用户注册

- **URL**: `/users/register`

- **方法**: POST

- **认证**: 不需要

- **描述**: 注册新用户

- **请求体**:

  ```json
  {
    "username": "testuser",
    "password": "password123",
    "contactInfo": "test@example.com"
  }
  ```

- **成功响应**:

   - 状态码: 200 OK

   - 响应体:

     ```json
     {
       "id": 1,
       "username": "testuser",
       "contactInfo": "test@example.com"
     }
     ```

- **错误响应**:

   - 状态码: 400 Bad Request
   - 响应体: `"用户名已存在"`

- **测试用例**:

   1. 使用有效的用户信息注册新用户
   2. 尝试使用已存在的用户名注册
   3. 尝试使用空用户名或密码注册

##### 1.2 用户登录

- **URL**: `/users/login`

- **方法**: POST

- **认证**: 不需要

- **描述**: 用户登录

- **请求体**:

  ```json
  {
    "username": "testuser",
    "password": "password123"
  }
  ```

- **成功响应**:

   - 状态码: 200 OK

   - 响应体:

     ```json
     {
       "id": 1,
       "username": "testuser",
       "contactInfo": "test@example.com"
     }
     ```

- **错误响应**:

   - 状态码: 401 Unauthorized
   - 响应体: `"用户名或密码无效"`

- **测试用例**:

   1. 使用正确的用户名和密码登录
   2. 使用错误的密码尝试登录
   3. 使用不存在的用户名尝试登录

##### 1.3 获取当前用户信息

- **URL**: `/users/me`

- **方法**: GET

- **认证**: 需要

- **描述**: 获取当前登录用户的信息

- **成功响应**:

   - 状态码: 200 OK

   - 响应体:

     ```json
     {
       "id": 1,
       "username": "testuser",
       "contactInfo": "test@example.com"
     }
     ```

- **错误响应**:

   - 状态码: 401 Unauthorized
   - 响应体: `"未认证"`

- **测试用例**:

   1. 使用有效的认证信息获取用户信息
   2. 使用无效的认证信息尝试获取用户信息
   3. 不提供认证信息尝试获取用户信息

##### 1.4 更新用户信息

- **URL**: `/users/{id}`

- **方法**: PUT

- **认证**: 需要

- **描述**: 更新指定用户的信息

- **请求体**:

  ```json
  {
    "username": "updateduser",
    "contactInfo": "updated@example.com"
  }
  ```

- **成功响应**:

   - 状态码: 200 OK

   - 响应体:

     ```json
     {
       "id": 1,
       "username": "updateduser",
       "contactInfo": "updated@example.com"
     }
     ```

- **错误响应**:

   - 状态码: 404 Not Found
   - 响应体: `"用户不存在"`

- **测试用例**:

   1. 更新存在的用户信息
   2. 尝试更新不存在的用户信息
   3. 尝试使用已存在的用户名更新用户信息

#### 2. 设备管理

##### 2.1 添加设备

- **URL**: `/devices`

- **方法**: POST

- **认证**: 需要

- **描述**: 添加新设备

- **请求体**:

  ```json
  {
    "deviceId": "device001",
    "type": "ammonia_sensor",
    "location": "Factory A",
    "status": "active"
  }
  ```

- **成功响应**:

   - 状态码: 200 OK

   - 响应体:

     ```json
     {
       "id": 1,
       "deviceId": "device001",
       "type": "ammonia_sensor",
       "location": "Factory A",
       "status": "active"
     }
     ```

- **错误响应**:

   - 状态码: 400 Bad Request
   - 响应体: `"设备ID已存在"`

- **测试用例**:

   1. 添加新设备
   2. 尝试添加已存在的设备ID
   3. 尝试添加缺少必要字段的设备

##### 2.2 获取所有设备

- **URL**: `/devices`

- **方法**: GET

- **认证**: 需要

- **描述**: 获取所有设备列表

- **成功响应**:

   - 状态码: 200 OK

   - 响应体:

     ```json
     [
       {
         "id": 1,
         "deviceId": "device001",
         "type": "ammonia_sensor",
         "location": "Factory A",
         "status": "active"
       },
       {
         "id": 2,
         "deviceId": "device002",
         "type": "ammonia_sensor",
         "location": "Factory B",
         "status": "inactive"
       }
     ]
     ```

- **测试用例**:

   1. 获取所有设备列表
   2. 在添加多个设备后获取设备列表
   3. 在没有设备的情况下获取设备列表

##### 2.3 更新设备信息

- **URL**: `/devices/{id}`

- **方法**: PUT

- **认证**: 需要

- **描述**: 更新指定设备的信息

- **请求体**:

  ```json
  {
    "type": "updated_sensor",
    "location": "Updated Location",
    "status": "inactive"
  }
  ```

- **成功响应**:

   - 状态码: 200 OK

   - 响应体:

     ```json
     {
       "id": 1,
       "deviceId": "device001",
       "type": "updated_sensor",
       "location": "Updated Location",
       "status": "inactive"
     }
     ```

- **错误响应**:

   - 状态码: 404 Not Found
   - 响应体: `"设备不存在"`

- **测试用例**:

   1. 更新存在的设备信息
   2. 尝试更新不存在的设备信息
   3. 更新设备的部分信息

##### 2.4 获取单个设备信息

- **URL**: `/devices/{id}`

- **方法**: GET

- **认证**: 需要

- **描述**: 获取指定设备的详细信息

- **成功响应**:

   - 状态码: 200 OK

   - 响应体:

     ```json
     {
       "id": 1,
       "deviceId": "device001",
       "type": "ammonia_sensor",
       "location": "Factory A",
       "status": "active"
     }
     ```

- **错误响应**:

   - 状态码: 404 Not Found
   - 响应体: `"设备不存在"`

- **测试用例**:

   1. 获取存在的设备信息
   2. 尝试获取不存在的设备信息
   3. 获取刚刚更新过的设备信息

#### 3. 数据管理

##### 3.1 保存设备数据

- **URL**: `/data`

- **方法**: POST

- **认证**: 需要

- **描述**: 保存设备采集的数据

- **请求体**:

  ```json
  {
    "ammoniaConcentration": 0.5,
    "timestamp": "2023-05-20T10:30:00",
    "device": {
      "id": 1
    }
  }
  ```

- **成功响应**:

   - 状态码: 200 OK

   - 响应体:

     ```json
     {
       "id": 1,
       "ammoniaConcentration": 0.5,
       "timestamp": "2023-05-20T10:30:00",
       "device": {
         "id": 1,
         "deviceId": "device001"
       }
     }
     ```

- **错误响应**:

   - 状态码: 400 Bad Request
   - 响应体: `"设备不存在"`

- **测试用例**:

   1. 为存在的设备保存有效数据
   2. 尝试为不存在的设备保存数据
   3. 尝试保存无效格式的数据（如负的氨气浓度）

##### 3.2 获取设备数据

- **URL**: `/data/{deviceId}`

- **方法**: GET

- **认证**: 需要

- **描述**: 获取指定设备在给定时间范围内的数据

- **查询参数**:

   - `start`: 开始时间（ISO 8601格式）
   - `end`: 结束时间（ISO 8601格式）

- **成功响应**:

   - 状态码: 200 OK

   - 响应体:

     ```json
     [
       {
         "id": 1,
         "ammoniaConcentration": 0.5,
         "timestamp": "2023-05-20T10:30:00",
         "device": {
           "id": 1,
           "deviceId": "device001"
         }
       },
       {
         "id": 2,
         "ammoniaConcentration": 0.6,
         "timestamp": "2023-05-20T11:00:00",
         "device": {
           "id": 1,
           "deviceId": "device001"
         }
       }
     ]
     ```

- **测试用例**:

   1. 获取指定设备在有效时间范围内的数据
   2. 尝试获取不存在设备的数据
   3. 使用无效的时间范围获取数据（如结束时间早于开始时间）

#### 4. 报警管理

##### 4.1 创建报警

- **URL**: `/alarms`

- **方法**: POST

- **认证**: 需要

- **描述**: 创建新的报警

- **请求体**:

  ```json
  {
    "threshold": 1.0,
    "timestamp": "2023-05-20T12:00:00",
    "status": "active",
    "device": {
      "id": 1
    },
    "message": "氨气浓度超标"
  }
  ```

- **成功响应**:

   - 状态码: 200 OK

   - 响应体:

     ```json
     {
       "id": 1,
       "threshold": 1.0,
       "timestamp": "2023-05-20T12:00:00",
       "status": "active",
       "device": {
         "id": 1,
         "deviceId": "device001"
       },
       "message": "氨气浓度超标",
       "handled": false
     }
     ```

- **错误响应**:

   - 状态码: 400 Bad Request
   - 响应体: `"设备不存在"`

- **测试用例**:

   1. 为存在的设备创建有效报警
   2. 尝试为不存在的设备创建报警
   3. 创建报警并验证WebSocket通知是否发送

##### 4.2 获取设备报警

- **URL**: `/alarms/device/{deviceId}`

- **方法**: GET

- **认证**: 需要

- **描述**: 获取指定设备的所有报警

- **成功响应**:

   - 状态码: 200 OK

   - 响应体:

     ```json
     [
       {
         "id": 1,
         "threshold": 1.0,
         "timestamp": "2023-05-20T12:00:00",
         "status": "active",
         "device": {
           "id": 1,
           "deviceId": "device001"
         },
         "message": "氨气浓度超标",
         "handled": false
       }
     ]
     ```

- **测试用例**:

   1. 获取存在设备的报警列表
   2. 尝试获取不存在设备的报警列表
   3. 获取没有报警的设备的报警列表

##### 4.3 处理报警

- **URL**: `/alarms/{id}/handle`

- **方法**: PUT

- **认证**: 需要

- **描述**: 将指定报警标记为已处理

- **成功响应**:

   - 状态码: 200 OK

   - 响应体:

     ```json
     {
       "id": 1,
       "threshold": 1.0,
       "timestamp": "2023-05-20T12:00:00",
       "status": "handled",
       "device": {
         "id": 1,
         "deviceId": "device001"
       },
       "message": "氨气浓度超标",
       "handled": true
     }
     ```

- **错误响应**:

   - 状态码: 404 Not Found
   - 响应体: `"报警不存在"`

- **测试用例**:

   1. 处理存在的未处理报警
   2. 尝试处理不存在的报警
   3. 尝试处理已经被处理的报警

##### 4.4 获取未处理报警

- **URL**: `/alarms/unhandled`

- **方法**: GET

- **认证**: 需要

- **描述**: 获取所有未处理的报警

- **成功响应**:

   - 状态码: 200 OK

   - 响应体:

     ```json
     [
       {
         "id": 1,
         "threshold": 1.0,
         "timestamp": "2023-05-20T12:00:00",
         "status": "active",
         "device": {
           "id": 1,
           "deviceId": "device001"
         },
         "message": "氨气浓度超标",
         "handled": false
       }
     ]
     ```

- **测试用例**:

   1. 获取所有未处理的报警
   2. 在处理所有报警后获取未处理报警列表
   3. 在创建新的未处理报警后获取未处理报警列表

### API 测试流程

为了全面测试这个项目的 API，建议按以下顺序进行测试：

1. 用户注册
2. 用户登录
3. 获取当前用户信息
4. 更新用户信息
5. 添加设备
6. 获取所有设备
7. 更新设备信息
8. 获取单个设备信息
9. 保存设备数据
10. 获取设备数据
11. 创建报警
12. 获取设备报警
13. 处理报警
14. 获取未处理报警

在测试过程中，请确保使用有效的认证信息（用户名和密码）进行需要认证的 API 调用。对于不需要认证的 API，如用户注册和登录，可以直接调用。

### 注意事项

1. 所有 API 的基础 URL 为 `http://localhost:8080/api`连接。
2. 对于需要认证的 API，请在请求头中添加 Basic Auth 认证信息。
3. 在测试过程中，请注意检查响应状态码和响应体，确保它们符合预期。
4. 对于时间相关的字段，如 `timestamp`，请使用 ISO 8601 格式（例如：`2023-05-20T12:00:00`）。

## 安全性

本项目使用Spring Security进行身份验证和授权。请确保在生产环境中使用更安全的密码加密方法。

## 未来改进

1. 添加单元测试和集成测试
2. 实现更复杂的报警规则
3. 添加数据可视化功能
4. 实现设备远程控制功能
5. 优化数据存储和查询性能
