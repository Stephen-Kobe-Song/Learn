# maven学习笔记

## 第一节：学习Maven的优点

省去自己手动导入jar包的过程，在一个项目中，可能需要导入上百个jar包，手动导入非常的繁琐。而使用maven开发工具可以使将导包的过程交给maven去处理，我们只需要配置相关的坐标，maven会自动帮我们在网络上下载，方便快捷又，规范大大减少了我们寻找jar包和导入jar包的时间。将精力更多地专注到具体业务的实现中去。

例如：

~~~xml
   <!-- Nacos 服务注册发现启动器 -->
    <dependency>
        <groupId>com.alibaba.cloud</groupId>
        <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
    </dependency>

    <!-- web启动器依赖 -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- 视图模板技术 thymeleaf -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>
~~~

note：你可以不使用 Maven，但是构建必须要做。当我们使用 IDEA 进行开发时，构建是 IDEA 替我们做的。

## 第二节：Maven核心程序解压与配置

### 1、Maven官网下载

首页：

[Maven – Welcome to Apache Maven](https://maven.apache.org/)

下载页面：

[Maven – Download Apache Maven](https://maven.apache.org/download.cgi)

### 2、解压下载过的压缩包

核心程序压缩包：apache-maven-3.8.4-bin.zip，解压到**非中文、没有空格**的目录。

在解压目录中，我们需要着重关注 Maven 的核心配置文件：**conf/settings.xml**

### 3、修改配置文件

**在conf/settings.xml文件中修改相关属性**

- 修改本地仓库位置：

~~~xml
<!-- localRepository
   | The path to the local repository maven will use to store artifacts.
   |
   | Default: ${user.home}/.m2/repository
  <localRepository>/path/to/local/repo</localRepository>
  -->
<localRepository>D:\JAVA\envs\apache-maven-3.8.4\maven-repo</localRepository>
~~~

**Note：**一定要把` localRepository` 标签**从注释中拿出来**。本地仓库本身也需要使用一个**非中文、没有空格**的目录。

- 配置国内阿里云镜像，提高下载速度

~~~xml
<mirror>
    <id>nexus-aliyun</id>
    <mirrorOf>central</mirrorOf>
    <name>Nexus aliyun</name>
    <url>http://maven.aliyun.com/nexus/content/groups/public</url>
</mirror>
~~~

Maven 下载 jar 包默认访问境外的中央仓库，而国外网站速度很慢。改成阿里云提供的镜像仓库，**访问国内网站**，可以让 Maven 下载 jar 包的时候速度更快。

- 配置Maven工程的基础JDK版本

这一步配置与否问题不大，可以配置可以不用配置，根据开发环境来就行。

~~~xml
<profile>
	  <id>jdk-1.8</id>
	  <activation>
		<activeByDefault>true</activeByDefault>
		<jdk>1.8</jdk>
	  </activation>
	  <properties>
		<maven.compiler.source>1.8</maven.compiler.source>
		<maven.compiler.target>1.8</maven.compiler.target>
		<maven.compiler.compilerVersion>1.8</maven.compiler.compilerVersion>
	  </properties>
	</profile>
~~~

### 4、配置相关环境变量

#### 1、检查 JAVA_HOME 配置是否正确

Maven 是一个用 Java 语言开发的程序，它必须基于 JDK 来运行，需要通过 JAVA_HOME 来找到 JDK 的安装位置。

检查本地Java环境是否正确

~~~xml
// 通过命令行工具进行检查
C:\Users\o水歌儿>java -version
java version "1.8.0_261"
Java(TM) SE Runtime Environment (build 1.8.0_261-b12)
Java HotSpot(TM) 64-Bit Server VM (build 25.261-b12, mixed mode)
~~~

#### 2、配置 MAVEN_HOME

- 新建系统变量：`MAVEN_HOME`,变量值为本地解压的Maven文件夹`D:\JAVA\envs\apache-maven-3.8.4`。

- 配置**PATH**:编辑环境变量，新建`%MAVEN_HOME%\bin`。

**note：**配置环境变量的规律：

- XXX_HOME 通常指向的是 bin 目录的上一级

- PATH 指向的是 bin 目录

#### 3、验证配置是否成功

~~~xml
C:\Users\o水歌儿>mvn -v
Apache Maven 3.8.4 (9b656c72d54e5bacbed989b64718c159fe39b537)
Maven home: D:\JAVA\envs\apache-maven-3.8.4
Java version: 1.8.0_261, vendor: Oracle Corporation, runtime: C:\Program Files\Java\jdk1.8.0_261\jre
Default locale: zh_CN, platform encoding: GBK
OS name: "windows 10", version: "10.0", arch: "amd64", family: "windows"
~~~

## 第三节：Maven关键术语

### 1、Maven中坐标

使用三个**向量**在**Maven的仓库**中**唯一**的定位到一个**jar**包。

- **groupId**：公司或组织的 id
- **artifactId**：一个项目或者是项目中的一个模块的 id
- **version**：版本号

三个向量的取值方式：

- groupId：公司或组织域名的倒序，通常也会加上项目名称
  - 例如：com.atguigu.maven
- artifactId：模块的名称，将来作为 Maven 工程的工程名
- version：模块的版本号，根据自己的需要设定
  - 例如：SNAPSHOT 表示快照版本，正在迭代过程中，不稳定的版本
  - 例如：RELEASE 表示正式版本

**Note：所有下载的jar包都保存在自己在配置文件中配置的本地仓库中**

### 2、Maven核心概率：POM

- **概念：**POM：**P**roject **O**bject **M**odel，项目对象模型。和 POM 类似的是：DOM（Document Object Model），文档对象模型。它们都是模型化思想的具体体现
- **模块化思想：**POM 表示将工程抽象为一个模型，再用程序中的对象来描述这个模型。这样我们就可以用程序来管理项目了。我们在开发过程中，最基本的做法就是将现实生活中的事物抽象为模型，然后封装模型相关的数据作为一个对象，这样就可以在程序中计算与现实事物相关的数据。
- **对应配置文件：**POM 理念集中体现在 Maven 工程根目录下 **pom.xml** 这个配置文件中。所以这个 pom.xml 配置文件就是 Maven 工程的核心配置文件。其实学习 Maven 就是学这个文件怎么配置，各个配置有什么用。

**note：**Maven 对于目录结构这个问题，没有采用配置的方式，而是基于约定。这样会让我们在开发过程中非常方便。如果每次创建 Maven 工程后，还需要针对各个目录的位置进行详细的配置，那肯定非常麻烦。

目前开发领域的技术发展趋势就是：约定大于配置，配置大于编码。

## 第四节：执行Maven构建命令

### 1、要求

运行 Maven 中和构建操作相关的命令时，必须进入到 pom.xml 所在的目录。如果没有在 pom.xml 所在的目录运行 Maven 的构建命令，那么会看到下面的错误信息：

~~~java
The goal you specified requires a project to execute but there is no POM in this directory
~~~

### 2、清理操作

mvn clean

效果：删除 target 目录

### 3、编译操作

主程序编译：mvn compile

测试程序编译：mvn test-compile

主体程序编译结果存放的目录：target/classes

测试程序编译结果存放的目录：target/test-classes

### 4、测试操作

mvn test

测试的报告存放的目录：target/surefire-reports

### 5、打包操作

mvn package

打包的结果——jar 包，存放的目录：target

### 6、安装操作

mvn install

~~~java 
[INFO] Installing D:\maven-workspace\space201026\pro01-maven-java\target\pro01-maven-java-1.0-SNAPSHOT.jar to D:\maven-rep1026\com\atguigu\maven\pro01-maven-java\1.0-SNAPSHOT\pro01-maven-java-1.0-SNAPSHOT.jar
[INFO] Installing D:\maven-workspace\space201026\pro01-maven-java\pom.xml to D:\maven-rep1026\com\atguigu\maven\pro01-maven-java\1.0-SNAPSHOT\pro01-maven-java-1.0-SNAPSHOT.pom
~~~

安装的效果是将本地构建过程中生成的 jar 包存入 Maven 本地仓库。这个 jar 包在 Maven 仓库中的路径是根据它的坐标生成的。另外，安装操作还会将 pom.xml 文件转换为 XXX.pom 文件一起存入本地仓库。所以我们在 Maven 的本地仓库中想看一个 jar 包原始的 pom.xml 文件时，查看对应 XXX.pom 文件即可，它们是名字发生了改变，本质上是同一个文件。

## 第五节：继承

### 1、概念

Maven工程之间，A 工程继承 B 工程

- B 工程：父工程
- A 工程：子工程

本质上是 A 工程的 pom.xml 中的配置继承了 B 工程中 pom.xml 的配置。

### 2、作用

在父工程中统一管理项目中的依赖信息，具体来说是管理依赖信息的版本。

它的背景是：

- 对一个比较大型的项目进行了模块拆分。
- 一个 project 下面，创建了很多个 module。
- 每一个 module 都需要配置自己的依赖信息。

它背后的需求是：

- 在每一个 module 中各自维护各自的依赖信息很容易发生出入，不易统一管理。
- 使用同一个框架内的不同 jar 包，它们应该是同一个版本，所以整个项目中使用的框架版本需要统一。
- 使用框架时所需要的 jar 包组合（或者说依赖信息组合）需要经过长期摸索和反复调试，最终确定一个可用组合。这个耗费很大精力总结出来的方案不应该在新的项目中重新摸索。

通过在父工程中为整个项目维护依赖信息的组合既**保证了整个项目使用规范、准确的 jar 包**；又能够将**以往的经验沉淀**下来，节约时间和精力。

### 3、查看被添加新内容的父工程 pom.xml

下面 modules 和 module 标签是聚合功能的配置

这其实就是聚合，将子模块聚合到总模块上！

~~~xml
<modules>  
	<module>pro04-maven-module</module>
	<module>pro05-maven-module</module>
	<module>pro06-maven-module</module>
</modules>
~~~

### 4、解读子工程的pom.xml

~~~xml
<!-- 使用parent标签指定当前工程的父工程 -->
<parent>
	<!-- 父工程的坐标 -->
	<groupId>com.atguigu.maven</groupId>
	<artifactId>pro03-maven-parent</artifactId>
	<version>1.0-SNAPSHOT</version>
</parent>

<!-- 子工程的坐标 -->
<!-- 如果子工程坐标中的groupId和version与父工程一致，那么可以省略 -->
<!-- <groupId>com.atguigu.maven</groupId> -->
<artifactId>pro04-maven-module</artifactId>
<!-- <version>1.0-SNAPSHOT</version> -->
~~~

### 5、在父工程中配置依赖的统一管理

~~~xml
<!-- 使用dependencyManagement标签配置对依赖的管理 -->
<!-- 被管理的依赖并没有真正被引入到工程 -->
<dependencyManagement>
	<dependencies>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-core</artifactId>
			<version>4.0.0.RELEASE</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-beans</artifactId>
			<version>4.0.0.RELEASE</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-context</artifactId>
			<version>4.0.0.RELEASE</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-expression</artifactId>
			<version>4.0.0.RELEASE</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-aop</artifactId>
			<version>4.0.0.RELEASE</version>
		</dependency>
	</dependencies>
</dependencyManagement>
~~~

### 6、子工程中引用那些被父工程管理的依赖

**关键点：省略版本号**

~~~xml
<!-- 子工程引用父工程中的依赖信息时，可以把版本号去掉。	-->
<!-- 把版本号去掉就表示子工程中这个依赖的版本由父工程决定。 -->
<!-- 具体来说是由父工程的dependencyManagement来决定。 -->
<dependencies>
	<dependency>
		<groupId>org.springframework</groupId>
		<artifactId>spring-core</artifactId>
	</dependency>
	<dependency>
		<groupId>org.springframework</groupId>
		<artifactId>spring-beans</artifactId>
	</dependency>
	<dependency>
		<groupId>org.springframework</groupId>
		<artifactId>spring-context</artifactId>
	</dependency>
	<dependency>
		<groupId>org.springframework</groupId>
		<artifactId>spring-expression</artifactId>
	</dependency>
	<dependency>
		<groupId>org.springframework</groupId>
		<artifactId>spring-aop</artifactId>
	</dependency>
</dependencies>
~~~

### 7、在父工程中声明自定义属性

~~~xml
<!-- 通过自定义属性，统一指定Spring的版本 -->
<properties>
	<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
	
	<!-- 自定义标签，维护Spring版本数据 -->
	<atguigu.spring.version>4.3.6.RELEASE</atguigu.spring.version>
</properties>
~~~

在需要的地方使用`${}`的形式来引用自定义的属性名：

~~~xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-core</artifactId>
    <version>${atguigu.spring.version}</version>
</dependency>
~~~

**note：**如果 A 工程依赖 B 工程，B 工程依赖 C 工程，C 工程又反过来依赖 A 工程，那么在执行构建操作时会报下面的错误：

## 第六节：在IDEA中使用Maven

### step 1：

打开idea---> file菜单选项--->点击new--->点击project---> 点击maven

### step 2：

纯手工新建，不勾选create from arch type选项，一路next，注意选择自己的maven环境，点击finish完成创建；

### step 3：

在项目文件夹上右键并选择add Framework Support选项

- 勾选spring选项，建议勾选create empty spring-config.xml选项，会新建一个空的spring配置文件，选择这种方式需要将生成的spring-config.xml文件移动到resources文件夹下面；
  - 当然也可以不用，选择自己新建一个spring配置文件，在resources文件右键新建一个xml文件选择spring-config选项。
  - 如果以前下载过spring的相关文件（jar），建议使用use library选项，节省本地内存。
  - 第一次使用则勾选download选项，系统会自动从网上下载spring所有的环境。

### step 4：

添加webapp文件夹：

- 在mian文件夹下面新建一个webapp文件夹；
- 点击file菜单---> 选择project structure选项--->点击modules--->点击`+`按钮；
- 选择下拉选项中`web`选项；
- 配置web resource directories ，选择文件目录到新建的webapp文件上；
- 配置deployment dscriptors， 点击`-`按钮删除已存在的，再点击`+`按钮新建一个Type，配置路径到webapp上；
- 选择vision版本为3.0或者以上版本即可，最好选择3.0或4.0；

**注意：第一次运行项目时，若出现找不到主类的情况**

- 点击idea右侧`maven`按钮，点击项目下面的Lifecycle文件夹下的`clean`按钮；
- 再点击`install`按钮；