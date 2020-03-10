#### 项目分为两个moudel

#### server端

利用derby数据库和jetty内嵌服务器构建

##### 启动derby服务：

java -jar D:\Java\jdk1.8.0_91\db\lib\derbyrun.jar  server start

运行**ServerApplication**,监听端口8080



##### client端

利用springboot创建，使用springboot内置tomcat服务器

运行 **ClientApplication**,监听端口8888

