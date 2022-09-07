# SpringBoot运维实用篇

## 1、打包与运行

**将程序打包到服务器上运行**

### A: Windows环境打包

- idea中打开Maven选中当前项目 ===> 打开`Lifecycle` ===> 选中Maven工具栏中**闪电**按钮，提示`Toggle Skip Tests Mode`跳过**test** ===>点击执行`package`;
- 执行完毕在`target`目录下会生成一个以项目名称开头的jar包，后面跟着版本号；
- 在文件夹中查看对应的jar包，在当前文件夹中地址栏输入`CMD`打开命令行工具。使用 `java -jar 项目名称`  运行该jar包；

**Note:jar支持的命令行启动需要依赖maven插件支持，确保项目pom.xml文件中导入了`spring-boot-maven-plugin`坐标；**

**相关问题：**

1. 命令行启动jar显示没有主清单属性：

   原因：打包过程出现问题，pom.xml中没有引入maven插件；

2. 端口占用

   原因：其他程序占用了要使用的端口

   解决：使用`netstat -ano`查看所有端口实用信息，查看具体的端口号占用情况：`netstat -ano | findstr "端口号"` ;

   查看具体的端口被谁使用，通过`PID`查看具体任务，使用`tasklist | findstr "PID号"` ；

   杀掉进程：使用`taskkill -f -pid "PID号"` ; `taskkill -f -im "进程名称"` 后一种方法不常用，存在不安全问题。

### B: Linux环境打包

**Linux实现一般部署**：

- idea中打开Maven选中当前项目 ===> 打开`Lifecycle` ===> 选中Maven工具栏中**闪电**按钮，提示`Toggle Skip Tests Mode`跳过**test** ===>点击执行`package`;
- 执行完毕在`target`目录下会生成一个以项目名称开头的jar包，后面跟着版本号；
- 使用Linux虚拟机，打开虚拟机，使用连接工具`finalShell`连接虚拟机；
- 使用cd命令进入到`usr/local`文件夹中，使用`mkdir app`创建一个新文件夹；上传idea中打好的jar包到新文件夹下；
- 使用Navicat数据库工具连接虚拟机，实现虚拟机数据库的构建。需要虚拟机已经安装了MySQL数据库；
- 查看虚拟机java版本`java -version`; 使用 `java -jar 项目名称`  运行该jar包；使用`ctrl + c`停止运行；

**服务器后台启动**：

- 使用命令`nohup java -jar 项目名称 > server.log 2 > &1 &` 开启后台启动,并指定日志存放位置；
- 停止后台运行，使用`ps -ef | grep "java -jar"`查看进程PID号，方便后面杀掉进程；
- 使用`kill -9 PID号`杀对应的进程；通过`cat server.log` 查看运行日志；

## 2、配置高级

### A: 临时属性

1. 运行修改端口号：使用`java -jar 项目名称 --server.port=修改后的端口号`；
2. 修改多个属性，不同属性中间使用空格分割属性；
3. 临时属性必须是当前boot工程中支持的属性，否则设置无效；

**Note：**注意属性加载优先级，去官方springboot文档中查看属性加载优先级；

### B: 临时属性在idea中测试

**配置临时属性：**

1. 选择springboot主运行文件（XXXXApplication类），配置相关属性。
2. 点击`Edit configuration`配置运行时的相关参数，在`Program argruments`中填写相关参数；

**配置文件四级分类：**

1. 在`resources`目录下新建一个文件夹`config`,将新的配置文件放入这个文件夹中，保持新的配置文件和原来的配置文件名称一致；
2. 在jar包同层目录下的配置文件会替换掉工程里面的配置文件；
3. 在项目工程jar包的同层目录下，新建一个`config`文件夹，在里面新建一个配置文件，属于最高级别的配置文件；

|      级别      |              文件地址              |
| :------------: | :--------------------------------: |
| 一级(最高级别) |   file : config/application.yml    |
|      二级      |       file ：application/yml       |
|      三级      | classpath : config/application.yml |
| 四级(最低级别) |    classpath ：application.yml     |

Note:

- 一级和二级留作系统打包后设置通用属性。一级常用于运维经理进行线上整体项目部署的方案调控；
- 三级和四级用于系统开发阶段设置通用属性，三级常用于项目经理进行整体项目属性调控；

**更改配置文件名：**

- 第一种方法：使用`Program agruments`设置配置文件：`--spring.config.name = XXXX`;没有配置文件扩展名；
- 第二种方法：使用`Program agruments`设置配置文件：`--spring.config.location = classpath:/XXX.yml`;

## 3、多环境开发

### A： 单文件yaml版

在项目同一个配置文件里面设置不同的环境，通过`---`分割不同的环境；

~~~yml
#应用环境,公共环境
spring:
 profiles:
  active: pro
---
#设置环境，过时的格式
#生产环境
spring：
 prifiles: pro
server:
 port: 80
---
#开发环境
spring:
 profiles; dev
server:
 port: 81
---
#测试环境,新版格式！
spring:
 config:
  activate:
   on-profile: test
server:
 port: 82
~~~

### B: 多文件yaml版本

多个配置文件用`-XXX`区分，**一定是按照这种写法，**如`application-dev，application-pro，application-test`;

在主配置文件里面决定使用哪一个配置文件`application.yml`;

**Note：**主配置文件配置公共属性！

### C：properties版本

主配置文件`application.properties`中使用`spring.profiles.active = XXX`,决定使用那一个环境；

### D: 多环境分组管理

根据功能对配置文件进行拆分，制作成独立的配置文件！命名规则如下：

- application-devDb.yml
- application-devRedis.yml
- application-devMVC.yml

~~~yml
spring：
 profiles:
  active: dev
  include: XXX,XXX
~~~

使用`include`关键字包含其他配置模块！后加载配置属性会覆盖前面配置的相同属性！

**Note：**这一种写法并不适合实际开发；

**spring2.4以后将`include `关键字换成`group`关键字**

```yml
spring：
 profiles:
  active: dev
  group: 
   "dev": XXX,XXX
   "pro": XXX,XXX
```

### E: 多环境开发控制

pom.xml文件中设置多环境依赖，

~~~xml
<profiles>
	<prifile>
        <id>env_dev</id>
        <properties>
             <profile.active>
                dev
            </profile.active>
        </properties>
        <activation>
        	<activeByDefault>
                true
            </activeByDefault>
        </activation>
    </prifile>
    <prifile>
        <id>env_prod</id>
        <properties>
            <profile.active>
                pro
            </profile.active>
        </properties>
    </prifile>
</profiles>
~~~

yml中读取pom文件中的配置

~~~yml
spring：
 profiles:
  active: @profile.active@
  group: 
   "dev": XXX,XXX
   "pro": XXX,XXX
~~~

**Note：**当boot与Maven里面的配置产生冲突时，让boot使用Maven里面的配置。idea中可能存在的bug，修改了pom文件但是idea并没有引用，需要点击Maven里面的`compile`按钮进行手工编译。

## 4、日志

### A: 日志基础

为某一个类添加日志信息

~~~java
import org.slf4j.Logger;
@RestController
@RequestMapper("/books")
public class BookContrller{
    //创建记录日志的对象
    private static final Logger log = LoggerFactory.getLogger(BookContrller.class);
    @GetMapping
    public String getById(){
        System.out.println("springboot is running ")
            log.info("info");
        	log.debug("debug");
        	log.warn("warn");
        	log.error("error");
            return "springboot is running "
    }
    
}
~~~

在配置文件中配置log信息级别

~~~yml
logging:
   # 设置分组，对某个组进行日志级别
 group:
  ebank: com.example.controller,com.example.service,com.example.dao
  iservice: cpm.alibaba
 level:
  root: info
  # 设置某个包的日志级别
  com.example.controller:debug
  # 对某个组进行日志级别
  ebank: warn

~~~

### B: 快速创建日志对象

创建一个日志父类，让其他类继承这个类！

~~~java
public class BaseClass{
    private Class clazz;
    private static Logger log;
    public BaseClass(){
        clazz = this.getClass();
        log = LoggerFactory.getLogger(clazz);
    }
}
~~~

使用**lombok**注解方式简化开发！

~~~java
import org.slf4j.Logger;
@Slf4j
@RestController
@RequestMapper("/books")
public class BookContrller{
    //创建记录日志的对象
    private static final Logger log = LoggerFactory.getLogger(BookContrller.class);
    @GetMapping
    public String getById(){
        System.out.println("springboot is running ")
            log.info("info");
        	log.debug("debug");
        	log.warn("warn");
        	log.error("error");
            return "springboot is running "
    }
    
}
~~~

### C: 日志输出格式控制

~~~yml
logging:
 pattern:
  console: "%d - %m %n "
  console: "%d %clr(%5p) --- [%t] %clr(%-40.40c){red} : %m %n"
~~~

### D: 文件记录日志

~~~yml
logging:
 file:
  name: server.log
 logback: 
  rollingpolicy:
   max-file-size: 10MB
   file-name-pattern: server.%d{yyyy-MM-dd}.%i.log
  
~~~

