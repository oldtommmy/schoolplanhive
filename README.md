## 项目环境搭建

### 1. 数据库

- Mysql 8 
- 使用压缩包下的 schoolplanDB.sql 初始化，可用navicat、datagrip等数据库管理工具，以navicat为例如下图导入

<img src="/Users/tommychan/Library/Application Support/typora-user-images/image-20230726131446918.png" alt="image-20230726131446918"  />



### 2. 运行前准备

- 集成开发环境：IDEA 
- jdk 1.8 + springboot 2.7（刷新MAVEN会自动导入相关依赖）
- 修改项目结构

![image-20230726133023319](/Users/tommychan/Library/Application Support/typora-user-images/image-20230726133023319.png)

![image-20230726133109032](/Users/tommychan/Library/Application Support/typora-user-images/image-20230726133109032.png)

- Maven下载依赖

![image-20230726131250060](/Users/tommychan/Library/Application Support/typora-user-images/image-20230726131250060.png)

- 修改项目配置文件 application.properties 和 log4j.properties

`application.properties `

![application.properties](/Users/tommychan/Library/Application Support/typora-user-images/image-20230726131820934.png)

`log4j.properties`

![log4j.properties](/Users/tommychan/Library/Application Support/typora-user-images/image-20230726132030496.png)

- 修改完成后运行即可
- 登录页面 http://localhost:8081/user/toLogin