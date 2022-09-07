# JavaWeb

## 1、基本概念

### 1.1、前言

web开发：

- web：网页的意思，www.baidu.com
- 静态web
  - html，css
  - 提供给所有人看的数据始终不会发生变化！

- 动态web
  - 提供给所有人看的数据始终会发生变化，每个人在不同的时间、不同的地点看到的信息是不一样的！
  - 技术栈：Servlet/JSP，ASP,PHP

在Java中，动态web资源开发的技术统称为JavaWeb；

### 1.2、web应用程序

web应用程序：可以提供浏览器访问的程序；

- a.html。b.html....对个web资源，这些资源可以被外界访问，对外界提供服务；
- 能访问到的任何一个页面，都存在在这个世界上的一天计算机上；
- URL；
- 这个统一的web资源会被放在同一个文件夹下面，web应用程序--->Tomcat:服务器；
- 一个web程序由多部分组成（静态web、动态web）
  - html，css，js
  - jsp，servlet
  - Java程序
  - jar包
  - 配置文件

web应用程序编写完成后，若想提供给外界访问需要一个服务器来统一进行管理；

### 1.3、静态web

- *.htm,如果服务器上存在这些东西，就可以直接进行读取；

![image-20220303180731024](D:\Typora\JavaWeb.assets\image-20220303180731024.png)

- 静态web存在的缺点

  - web页面无法动态更新；

    - 轮播图，点击特效，伪特效

    - JavaScript

    - VBScript

  - 无法和数据库实现交互，数据无法持久化；

    ​	

### 1.4、动态web

![image-20220303181557463](D:\Typora\JavaWeb.assets\image-20220303181557463.png)

缺点

- 一旦服务器动态资源web资源出现了错误，需要重新编写**后台程序**，重新发布；

  - 停机维护

    

 优点：

- web页面可以动态更新
- 可以与数据库实现交互，数据持久化；

![image-20220303182251188](D:\Typora\JavaWeb.assets\image-20220303182251188.png)

## 2、web服务器

### 2.1相关技术

**ASP：**

- 微软：国内最早流行的就是ASp；
- 在HTML中嵌入VB的脚本，ASP + COM；
- 在ASP开发中，基本一个页面，都有几千行的业务代码
- 维护成本高；
- C#

**PHP:**

- PHP开发速度很快，功能强大，跨平台，代码简单；
- 无法承载大访问量的情况；

**JSP/Servlet；**

- sun公司主推的B/S架构；
- 基于Java语言；
- 可以承载三高问题带来的影响；
- 语法像ASP;

### 2.2、 web服务器

服务器是一种被动的操作，用来处理用户的一些请求和给用户的一些响应信息

**Tomcat**

面向百度编程；

## 3、Tomcat

### 3.1、安装

官网：[Apache Tomcat® - Welcome!](https://tomcat.apache.org/)

下载Windows64位；

### 3.2、Tomcat启动和配置

**文件夹作用：**

![image-20220304140320987](D:\Typora\JavaWeb.assets\image-20220304140320987.png)

**启动、关闭Tomcat：**

启动：点击bin文件夹下startup文件；

关闭：直接叉掉或者命令工具；

![image-20220304140616028](D:\Typora\JavaWeb.assets\image-20220304140616028.png)

访问测试：http://localhost:8080/

可能遇到的问题：

- Java环境变量没有配置；
- 闪退问题：需要配置兼容性；
- 乱码问题：配置文件中设置；

### 3.3、配置

配置核心文件:

![image-20220304141728136](D:\Typora\JavaWeb.assets\image-20220304141728136.png)

### 高难度面试题：

请你谈谈网站是如何进行访问的？

1、输入域名，回车；

2、检查本机的配置文件下有没有这个域名映射；

- 有；直接返回这个IP地址，可以直接访问；
- 没有，去DNS服务器上面找，找到就返回地址，找不到就返回找不到；

![image-20220304171205772](D:\Typora\JavaWeb.assets\image-20220304171205772.png)

### 3.4、发布一个web网站

- 将自己写的网站，放在服务器（Tomcat）中web指定文件夹（webapps）下；

![image-20220304172332346](D:\Typora\JavaWeb.assets\image-20220304172332346.png)

HTTP协议：面试

Maven：构建工具

- Maven安装包

Servlet入门

- Helloworld
- Servlet配置
- 原理

## 4、Http

### 4.1、什么是HTTP

HTTP（超文本传输协议）是一个简单的请求-响应协议，通常在tcp上；

- 文本：html....
- 超文本：图片，音乐，视频，定位，地图....
- 端口80

HTTPS:安全的

- 端口443

### 4.2、 两个时代

- http1.0
  - HTTP/1.0:客户端与web服务器连接后，只能获得一个web资源，断开连接；
- http2.0
  - HTTP/1.1：客户端与web服务器连接后，可以获得多个web资源；

### 4.3、HTTP请求

- 客户端---发送请求---服务器

![image-20220304232301890](D:\Typora\JavaWeb.assets\image-20220304232301890.png)

### ![image-20220304232844129](D:\Typora\JavaWeb.assets\image-20220304232844129.png)4.4、HTTP响应

- 服务器--响应--客户端

![image-20220304232249133](D:\Typora\JavaWeb.assets\image-20220304232249133.png)

#### 1、响应体

![image-20220304233135782](D:\Typora\JavaWeb.assets\image-20220304233135782.png)

#### 2、响应状态码（重点）

200：请求响应成功；

3**：请求重定向；

404：找不到资源；

4**：找不到资源；

- 资源不存在；

5**：服务器代码错误 500 502：网关错误

## 5、Maven

**为什么学习这个技术？**

- 自动导入配置和jar包；

### 5.1 Maven架构管理工具

Maven的核心思想：**约定大于配置**

- 约束，不要去违反；

### 5.2 下载安装Maven

官网：[Maven – Welcome to Apache Maven](https://maven.apache.org/)

### 5.3 配置环境变量

在系统环境变量中配置如下配置

- M2_HOME ：maven下的bin目录
- MAVEN_HOME：maven目录
- 在系统的path里面配置MAVEN_HOME:%MAVEN_HOME%\bin

### 5.4 配置阿里云镜像

相对简单，网上教程一大堆。

~~~xml
<mirror>
    <id>nexus-aliyun</id>
    <mirrorOf>central</mirrorOf>
    <name>Nexus aliyun</name>
    <url>http://maven.aliyun.com/nexus/content/groups/public</url>
	</mirror>
~~~

### 5.5 本地仓库

建立一个本地仓库，远程仓库；localRepository

~~~xml
<localRepository>D:\Java\apache-maven-3.8.4\maven-repo</localRepository>
~~~

### 5.6 在idea中配置Maven

![image-20220310133544871](D:\Typora\JavaWeb.assets\image-20220310133544871.png)

![image-20220310133815201](D:\Typora\JavaWeb.assets\image-20220310133815201.png)

等待项目初始化完毕

### 5.7 、在idea中配置Tomcat

![image-20220310143251551](D:\Typora\JavaWeb.assets\image-20220310143251551.png)

#### 5.8、解决问题

1、idea导入不了maven

- 尝试多个版本

2、idea每次都要重复配置maven

- 在idea中的全局默认配置进行配置

## 6、Servlet

### 6.1、Servlet简介

- Servlet就是sun公司开发动态效果的一项技术
- sun在这些API中提供一个接口：Servlet，如果你想开发一个Servlet程序，只需要完成两个步骤：
  - 编写一个类，实现Servlet接口；
  - 把开发好的Java类部署到web服务器中；

**把实现servlet接口的Java程序叫做Servlet；**

### 6.2、HelloServlet

1、创建一个普通的maven项目，删掉里面的src目录；

2、关于maven父子工程理解

3、重写相关方法

4、编写Servlet映射

​	为什么需要映射：我们编写的Java程序，但是要通过兰兰器访问，而浏览器需要连接web服务器，所以我们需要在web服务器上注册我们写的Servlet，还需要给他一个浏览器能够访问你的路径。

5、配置Tomcat

​	注意：配置项目发布的路径；

6、启动测试

###  6.3、Servlet原理

 ![image-20220314160029126](D:\Typora\JavaWeb.assets\image-20220314160029126.png)



### 6.4、Mapping问题

1、一个Servlet请求可以制定一个映射路径

2、一个Servlet请求可以制定一个映射路径

3、一个Servlet请求可以制定通用映射路径

### 6.5、 ServletContext

web容器在启动的时候，他都会为每个web程序创建一个对应的ServletContext对象，他代表了当前了web应用；

- 共享数据

  我在这个Servlet中保存的数据，可以咋另外一个Servlet中拿到；

- 
