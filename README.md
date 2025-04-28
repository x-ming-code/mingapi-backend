项目技术栈：SSM + Spring Boot、Spring Cloud Gateway、Dubbo、Nacos、MySQL、Redis、MyBatis-Plus、Hutool 工具库。

Spring Boot：用于快速构建基础的后端项目，只需要修改配置文件，就能轻松整合 SSM、MySQL、Redis 等依赖。

Dubbo：分布式 RPC 框架，实现项目中不同模块的高性能相互调用，比如网关服务集中统计接口调用次数时通过 Dubbo 调用接口服务完成次数扣减。kvopHxsaBjshRHsJ4Tk5mOny3jelJ7KAu2+PBVvyBww=FByUIeT+/nhffGbzJC8zZ7ZXk+Ut8KWUcCEZvBZb18Y=

Gateway：作为 API 网关，集中接受客户端的请求，并执行统一的安全认证、请求转发、流量控制、请求日志、公共业务等操作。

API 开放平台分为 5 个子项目（核心模块），分别为：

api-backend：核心业务后端，负责用户和接口管理等核心业务功能
api-gateway：API 网关服务，负责集中的路由转发、统一鉴权、统一业务处理、访问控制等
api-common：公共模块，包括各其他模块中需要复用的方法、工具类、实体类、全局异常等
api-client-sdk：客户端 SDK，封装了对各 API 接口的调用方法，降低开发者的使用成本。
api-interface：提供模拟 API 接口

交互流程
首先管理员创建接口后通过核心业务后端（api-backend）保存到数据库中。用户调用某个接口时，在自己的项目中引入客户端 SDK（api-client-sdk）并通过一行代码发起调用，请求路径先会被网关配置的路由监听转发，让后进行用户的鉴权和接口调用统计（过程中会使用Dubbo进行远程调用接口进行鉴权，如ak，sk是否合法等操作），无误后会将请求转发到实际的 API 接口（api-interface）。

