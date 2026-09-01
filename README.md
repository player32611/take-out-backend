# take-out-backend

外卖管理系统后端，基于 Spring Boot 构建的 RESTful API 服务，为餐饮企业提供完整的后台管理接口和用户端接口。

## 技术栈

- **框架**: Spring Boot 4.1 + Spring WebMVC
- **ORM**: MyBatis（Spring Boot Starter 4.0.1）+ PageHelper 分页
- **数据库**: MySQL（mysql-connector-j）
- **缓存**: Redis（Spring Data Redis）
- **实时通信**: WebSocket（Spring Boot Starter）
- **鉴权**: JWT（jjwt 0.11.5）
- **文件存储**: 阿里云 OSS（alibabacloud-oss-v2）
- **报表导出**: Apache POI 5.4.1
- **HTTP 客户端**: Apache HttpClient
- **工具库**: Lombok、Fastjson、commons-lang3
- **构建工具**: Maven（Java 26）

## 项目结构

```
take-out-backend/
├── pom.xml                          # 父 POM，聚合三个子模块
├── take-out-common/                 # 公共模块
│   └── src/main/java/com/player32611/
│       ├── constant/                # 常量定义
│       │   ├── AutoFillConstant.java
│       │   ├── CategoryConstant.java
│       │   ├── JwtClaimsConstant.java
│       │   ├── MessageConstant.java
│       │   ├── PasswordConstant.java
│       │   ├── ShopConstant.java
│       │   ├── StatusConstant.java
│       │   └── WechatConstant.java
│       ├── context/
│       │   └── BaseContext.java     # 线程级上下文（当前用户 ID）
│       ├── enumration/
│       │   └── OperationType.java   # INSERT / UPDATE 枚举
│       ├── exception/               # 业务异常类
│       │   ├── BaseException.java
│       │   ├── AccountLockedException.java
│       │   ├── AccountNotFoundException.java
│       │   ├── AddressBookBusinessException.java
│       │   ├── CategoryBusinessException.java
│       │   ├── DeletionNotAllowedException.java
│       │   ├── LoginFailedException.java
│       │   ├── OrderBusinessException.java
│       │   ├── PasswordErrorException.java
│       │   ├── ReportBusinessException.java
│       │   ├── ShoppingCartBusinessException.java
│       │   └── UpdateNotAllowedException.java
│       ├── properties/              # 配置属性类
│       │   ├── AliOssProperties.java
│       │   ├── JwtProperties.java
│       │   └── WeChatProperties.java
│       ├── result/                  # 统一响应模型
│       │   ├── Result.java          # 通用响应
│       │   └── PageResult.java      # 分页响应
│       └── utils/                   # 工具类
│           ├── AliOssUtil.java      # 阿里云 OSS 文件上传
│           ├── ExcelUtil.java       # Excel 样式工具
│           ├── HttpClientUtil.java  # HTTP 请求工具
│           └── JwtUtil.java         # JWT 生成与解析
├── take-out-pojo/                   # POJO 模块
│   └── src/main/java/com/player32611/
│       ├── entity/                  # 数据实体
│       │   ├── Employee.java        # 员工
│       │   ├── Category.java        # 分类
│       │   ├── Dish.java            # 菜品
│       │   ├── DishFlavor.java      # 菜品口味
│       │   ├── Setmeal.java         # 套餐
│       │   ├── SetmealDish.java     # 套餐-菜品关联
│       │   ├── Orders.java          # 订单
│       │   ├── OrderDetail.java     # 订单明细
│       │   ├── ShoppingCart.java    # 购物车
│       │   ├── AddressBook.java     # 地址簿
│       │   └── User.java            # 用户（C 端）
│       ├── dto/                     # 数据传输对象
│       │   ├── EmployeeLoginDTO.java / EmployeeDTO.java / EmployeePageDTO.java
│       │   ├── CategoryPageDTO.java / CategoryDTO.java / CategoryListDTO.java / CategoryDeleteDTO.java
│       │   ├── DishDTO.java / DishPageDTO.java / DishListDTO.java
│       │   ├── SetmealDTO.java / SetmealPageDTO.java / SetmealListDTO.java
│       │   ├── OrderSubmitDTO.java / OrderSearchDTO.java / OrderConfirmDTO.java
│       │   ├── OrderRejectionDTO.java / OrderCancelDTO.java / OrderPaymentDTO.java / OrderHistoryDTO.java
│       │   ├── ShoppingCartDTO.java
│       │   ├── AddressBookDTO.java
│       │   ├── UserLoginDTO.java
│       │   └── ReportDTO.java
│       └── vo/                      # 视图对象
│           ├── EmployeeLoginVO.java / EmployeeVO.java
│           ├── CategoryVO.java
│           ├── DishVO.java / DishPageVO.java
│           ├── SetmealVO.java / SetmealPageVO.java / SetmealDishVO.java
│           ├── OrderSearchVO.java / OrderDetailsVO.java / OrderStatisticsVO.java
│           ├── OrderVO.java / OrderSubmitVO.java / OrderPaymentVO.java
│           ├── UserLoginVO.java
│           ├── WorkspaceBusinessVO.java / WorkspaceOrdersVO.java
│           ├── WorkspaceDishesVO.java / WorkspaceSetmealsVO.java
│           ├── ReportTurnoverVO.java / ReportUserVO.java
│           ├── ReportOrdersVO.java / ReportTop10VO.java
│           └── ...
└── take-out-server/                 # 服务模块（启动入口）
    └── src/main/
        ├── java/com/player32611/
        │   ├── TakeOutApplication.java     # 启动类
        │   ├── annotation/
        │   │   └── AutoFill.java           # 公共字段自动填充注解
        │   ├── aspect/
        │   │   └── AutoFillAspect.java     # AOP 实现自动填充
        │   ├── config/
        │   │   ├── CorsConfig.java         # CORS 跨域配置
        │   │   ├── OssConfiguration.java   # 阿里云 OSS 配置
        │   │   ├── RedisConfiguration.java # Redis 配置
        │   │   ├── WebMvcConfiguration.java# 拦截器注册
        │   │   └── WebSocketConfiguration.java
        │   ├── controller/
        │   │   ├── admin/                  # 管理端接口
        │   │   │   ├── EmployeeController.java  # /admin/employee/**
        │   │   │   ├── CategoryController.java  # /admin/category/**
        │   │   │   ├── DishController.java      # /admin/dish/**
        │   │   │   ├── SetmealController.java   # /admin/setmeal/**
        │   │   │   ├── OrderController.java     # /admin/order/**
        │   │   │   ├── ShopController.java      # /admin/shop/**
        │   │   │   ├── CommonController.java    # /admin/common/**
        │   │   │   ├── ReportController.java    # /admin/report/**
        │   │   │   └── WorkspaceController.java # /admin/workspace/**
        │   │   └── user/                  # 用户端接口
        │   │       ├── UserController.java      # /user/user/**
        │   │       ├── CategoryController.java  # /user/category/**
        │   │       ├── DishController.java      # /user/dish/**
        │   │       ├── SetmealController.java   # /user/setmeal/**
        │   │       ├── OrderController.java     # /user/order/**
        │   │       ├── ShoppingCartController.java # /user/shoppingCart/**
        │   │       ├── AddressBookController.java # /user/addressBook/**
        │   │       └── ShopController.java      # /user/shop/**
        │   ├── handler/
        │   │   └── GlobalExceptionHandler.java  # 全局异常处理
        │   ├── interceptor/
        │   │   ├── JwtTokenAdminInterceptor.java # 管理端 JWT 拦截
        │   │   └── JwtTokenUserInterceptor.java  # 用户端 JWT 拦截
        │   ├── mapper/                   # MyBatis Mapper 接口
        │   │   ├── EmployeeMapper.java
        │   │   ├── CategoryMapper.java
        │   │   ├── DishMapper.java
        │   │   ├── DishFlavorMapper.java
        │   │   ├── SetmealMapper.java
        │   │   ├── SetmealDishMapper.java
        │   │   ├── OrdersMapper.java
        │   │   ├── OrderDetailMapper.java
        │   │   ├── ShoppingCartMapper.java
        │   │   ├── AddressBookMapper.java
        │   │   └── UserMapper.java
        │   ├── service/                  # Service 接口与实现
        │   │   ├── EmployeeService.java / impl/
        │   │   ├── CategoryService.java / impl/
        │   │   ├── DishService.java / impl/
        │   │   ├── SetmealService.java / impl/
        │   │   ├── OrderService.java / impl/
        │   │   ├── ShoppingCartService.java / impl/
        │   │   ├── AddressBookService.java / impl/
        │   │   ├── UserService.java / impl/
        │   │   ├── ReportService.java / impl/
        │   │   └── WorkspaceService.java / impl/
        │   ├── task/
        │   │   └── OrderTask.java         # 定时任务
        │   └── websocket/
        │       └── WebSocketServer.java   # WebSocket 服务端
        └── resources/
            ├── application.yml            # 主配置
            ├── application-dev.yml        # 开发环境配置
            └── mapper/                    # MyBatis XML 映射
                ├── EmployeeMapper.xml
                ├── CategoryMapper.xml
                ├── DishMapper.xml
                ├── DishFlavorMapper.xml
                ├── SetmealMapper.xml
                ├── SetmealDishMapper.xml
                ├── OrdersMapper.xml
                ├── OrderDetailMapper.xml
                ├── ShoppingCartMapper.xml
                ├── AddressBookMapper.xml
                └── UserMapper.xml
```

## 功能模块

### 管理端 API（`/admin/**`）

所有管理端接口需在请求头携带 `token` 参数进行 JWT 鉴权（登录接口除外）。

| 模块 | 路径 | 功能 |
|------|------|------|
| **员工管理** | `/admin/employee` | 登录、登出、新增、分页查询、编辑、启用/禁用、根据 ID 查询 |
| **分类管理** | `/admin/category` | 分页查询、新增、修改、启用/禁用、删除、按类型列表查询 |
| **菜品管理** | `/admin/dish` | 新增、分页查询、批量删除、查询详情、修改、起售/停售 |
| **套餐管理** | `/admin/setmeal` | 新增、分页查询、起售/停售、查询详情、修改、批量删除 |
| **订单管理** | `/admin/order` | 条件搜索、派送、接单、拒单、完成、取消、统计、订单详情 |
| **店铺管理** | `/admin/shop` | 设置营业状态、获取营业状态（Redis 存储） |
| **通用接口** | `/admin/common` | 文件上传（阿里云 OSS） |
| **数据报表** | `/admin/report` | 营业额统计、用户统计、订单统计、销量 Top10、Excel 导出 |
| **工作台** | `/admin/workspace` | 今日运营数据、订单管理概览、菜品/套餐总览 |

### 用户端 API（`/user/**`）

用户端接口需在请求头携带 `Authorization` 参数进行 JWT 鉴权（登录和店铺状态接口除外）。

| 模块 | 路径 | 功能 |
|------|------|------|
| **用户登录** | `/user/user` | 微信登录 |
| **分类浏览** | `/user/category` | 分类列表查询 |
| **菜品浏览** | `/user/dish` | 根据分类 ID 查询菜品（Redis 缓存） |
| **套餐浏览** | `/user/setmeal` | 根据分类 ID 查询套餐、查看套餐内菜品（Redis 缓存） |
| **订单** | `/user/order` | 下单、历史订单、订单详情、支付、取消订单、再来一单、催单 |
| **购物车** | `/user/shoppingCart` | 添加、查看列表、清空、减少商品 |
| **地址簿** | `/user/addressBook` | 新增、列表、设置默认、查询详情、删除、修改、获取默认地址 |
| **店铺状态** | `/user/shop` | 获取营业状态 |

## 核心设计

### 统一响应格式

```json
{
  "code": 200,
  "msg": "success",
  "data": {}
}
```

### 分页响应

```json
{
  "total": 100,
  "records": []
}
```

### 鉴权机制

- 管理端和用户端分别使用独立的 JWT 密钥和 Token 名称
- 通过 `JwtTokenAdminInterceptor` 和 `JwtTokenUserInterceptor` 拦截器实现
- 解析成功的用户 ID 通过 `BaseContext`（ThreadLocal）在线程内传递

### 公共字段自动填充

通过 `@AutoFill` 注解 + AOP 切面自动填充实体的 `createTime`、`createUser`、`updateTime`、`updateUser` 字段，减少重复代码。

### 缓存策略

- 菜品/套餐列表使用 Spring Cache（Redis）缓存，按分类 ID 作为缓存 key
- 店铺营业状态存储在 Redis 中
- 增删改操作通过 `@CacheEvict` 自动清除相关缓存

### 实时通信

- 基于 WebSocket 实现订单状态推送（`/es/{sid}`）
- 管理端连接后可接收新订单提醒和催单通知
- 服务端通过 `WebSocketServer.sentToAllClient()` 广播消息

### 定时任务

- 每分钟检查超时未支付订单，15 分钟后自动取消
- 每天凌晨 1 点将配送中超过 60 分钟的订单自动设为已完成

### 文件存储

- 图片等文件上传到阿里云 OSS
- 返回可直接访问的文件 URL

### 报表导出

- 使用 Apache POI 生成 Excel 报表
- 支持营业额、用户、订单等维度的数据统计
- 支持表格数据导出下载

## 快速开始

### 环境要求

- JDK 26+
- Maven 3.8+
- MySQL 8.0+
- Redis 6+

### 配置

修改 `application-dev.yml` 中的配置：

```yaml
player32611:
  alioss:
    endpoint: oss-cn-{region}.aliyuncs.com
    access-key-id: your-access-key
    access-key-secret: your-secret
    bucket-name: your-bucket
  redis:
    host: localhost
    port: 6379
  wechat:
    appid: your-appid
    secret: your-secret
```

修改 `application.yml` 中的数据库连接：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/take_out_database
    username: root
    password: your-password
```

### 启动

```bash
mvn clean install -DskipTests
mvn -pl take-out-server -am spring-boot:run
```

服务默认运行在 `http://localhost:8080`。

## 数据库

数据库名：`take_out_database`

主要数据表：
- `employee` — 员工表
- `category` — 分类表
- `dish` — 菜品表
- `dish_flavor` — 菜品口味表
- `setmeal` — 套餐表
- `setmeal_dish` — 套餐菜品关联表
- `orders` — 订单表
- `order_detail` — 订单明细表
- `shopping_cart` — 购物车表
- `address_book` — 地址簿表
- `user` — 用户表