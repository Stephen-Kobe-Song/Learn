# Springboot开发使用篇

## 1、热部署

### A: **概念**

当修改代码后，不需要重启服务器，也能够实现代码更新！

- 在pom文件中导入热部署依赖;

~~~xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
</dependency>
~~~

- 激活热部署：在idea中选择工具栏上的Build功能，快捷键`Ctrl + F9`;
- 关于热部署：
  - 重启（Restart）：自定义开发代码，包含类，页面，配置文件等，加载restart类加载器；
  - 重载（Reload）：jar包，加载位置base类加载器；

**Note：**热部署仅加载当前开发者自定义的开发资源，不加载jar资源！

### B: 自动热部署

idea中配置自动热部署：

- `File ===> Settings ===>Build,Execution,Deployment===>compile===>勾选Build project automatically`；
- `Ctrl+Alt+Shift+/` ===>` Registry `===>勾选`compiler.automake.allow.when.app.running`；

**Note：**当idea中没有第二步选项的时候，直接跳过，会自动进行部署；

### C: 热部署范围控制

设置不参与restart的文件！

~~~yml
spring:
 devtools:
     restart:
       additional-exclude: static/**,public/**,config/applicaton.yaml
~~~

### D: 关闭热部署

配置文件pom里面设置；

~~~yml
spring：
 devtools:
     restart:
       additional-exclude: static/**,public/**,config/applicaton.yaml
       enabled: false
~~~

设置高优先级属性的禁用热部署：

~~~java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SSMPApplication {

    public static void main(String[] args) {
        System.setProperty("spring.devtools.restart.enabled","false");
        SpringApplication.run(SSMPApplication.class, args);
    }

}
~~~

## 2、配置高级

### A:  注解@ConfigurationProperties

~~~java
package com.example.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "servers")
public class ServletConfig {
    private String ipAddress;
    private int port;
    private long timeout;
}
~~~

~~~yml
servers:
  ipAddress: 192.168.0.1
  port: 2345
  timeout: -1

~~~

~~~java
package com.example;

import com.example.config.ServletConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Demo08Application {

    public static void main(String[] args) {

        ConfigurableApplicationContext run = SpringApplication.run(Demo08Application.class, args);
        ServletConfig bean = run.getBean(ServletConfig.class);
        System.out.println(bean);
    }

}
~~~

使用`@ConfigurationProperties`为第三方bean绑定属性

~~~java
  @Bean
    @ConfigurationProperties(prefix = "datasource")
    public DruidDataSource dataSource(){
        DruidDataSource ds = new DruidDataSource();
//        ds.setDriverClassName("com.mysql.jdbc.driver");
        return ds;
    }
~~~

~~~yml
datasource:
  driver-class-name: com.mysql.jdbc.Deriver
~~~

使用`@EnableConfigurationProperties(ServletConfig.class)`开启对应的属性绑定;

**Note:**@EnableConfigurationProperties(ServletConfig.class)自动将对应类设置为Bean，跟类自身的@Component冲突，二者只选其一！

编程过程问题：显示`spring boot Configuration Annotation Processor not configured`

添加依赖：

~~~yml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-configuration-processor</artifactId>
</dependency>
~~~

### B:松散绑定

@ConfigurationProperties绑定属性支持宽松绑定；

**Note：** @Value不支持松散绑定

### C: 常用计量单位使用

~~~java
package com.example.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DataSizeUnit;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.stereotype.Component;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Component
@Data
@ConfigurationProperties(prefix = "servers")
public class ServletConfig {
    private String ipAddress;
    private int port;
    private long timeout;
    # 时间范围的
    @DurationUnit(ChronoUnit.HOURS)
    private Duration serverTimeOut;
    # 存储空间的
    @DataSizeUnit(DataUnit.MEGABYTES)
    private DataSize dataSize;
}
~~~

### D： bean属性数据校验

- pom引入相关依赖

~~~xml
<!--导入JSR303规范-->
<dependency>
    <groupId>javax.validation</groupId>
    <artifactId>validation-api</artifactId>
    <version>2.0.1.Final</version>
</dependency>
<!--导入校验器作为实现类-->
<dependency>
    <groupId>org.hibernate.validator</groupId>
    <artifactId>hibernate-validator</artifactId>
    <version>7.0.1.Final</version>
</dependency>
~~~

- 开启bean数据校验

~~~java
@Component
@Data
@ConfigurationProperties(prefix = "servers")
@Validated
public class ServletConfig {
    private String ipAddress;
    @Max(value = 9999,message = "最大值不能超过9999")
    private int port;
    private long timeout;
    @DurationUnit(ChronoUnit.HOURS)
    private Duration serverTimeOut;
    @DataSizeUnit(DataUnit.MEGABYTES)
    private DataSize dataSize;
}
~~~

- 使用具体的校验规则任意一个字段做设置

### E: 进制数据转换规则

**Note：**配置密码的纯数字的话需要用双引号包裹！！！

## 3、测试

### A: 加载测试相关属性

在启动测试环境时可以通过properties参数设置测试环境专用的属性

~~~java
package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
//properties属性可以为当前测试添加临时的属性配置
//使用args属性可以为当前测试用例添加临时的命令行参数
//@SpringBootTest(properties = {"test.prop = testValue1"})
@SpringBootTest(args = {"--test.prop =testValue2"})
public class PropertiesAndArgsTest {
    @Value("${test.prop}")
    private String msg;
    @Test
    void testProperties(){
        System.out.println(msg);
    }

}
~~~

优点：比多环境开发中的测试环境影响范围更小，仅对当前测试类有效！

### B: 加载测试专用配置

使用@Import注解为当前测试用例类导入专用的配置

~~~java
@SpringBootTest
@Import({MsConfig.class})
public class ConfigurationTest {
    @Autowired
    private String msg;
    @Test
    void testConfiguration(){
        System.out.println(msg);
    }
}
~~~

### C: 测试类中启动web环境

~~~java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebTest {
    @Test
    void test(){

    }
}
~~~

- 模拟虚拟请求

~~~java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//开启虚拟MVC调用
@AutoConfigureMockMvc
public class WebTest {
    @Test
    void test(@Autowired MockMvc mvc) throws Exception {
        //http://licalhost:8080/books
        //创建虚拟请求，当前访问/books
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/books");
        mvc.perform(builder);
    }
}
~~~

- 匹配执行状态

~~~java
@Test
void testStatus(@Autowired MockMvc mvc) throws Exception {
    //http://licalhost:8080/books
    //创建虚拟请求，当前访问/books
    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/books");
    ResultActions actions = mvc.perform(builder);
    // 设定预期值 与真实值比较！
    // 定义本次调用的预期值
    StatusResultMatchers status = MockMvcResultMatchers.status();
    // 预计本次调用是成功的：状态200
    ResultMatcher ok = status.isOk();
    // 添加预计值到本次调用过程中进行匹配
    actions.andExpect(ok);

}
~~~

- 匹配响应体

~~~java
@Test
void testBody(@Autowired MockMvc mvc) throws Exception {
    //http://licalhost:8080/books
    //创建虚拟请求，当前访问/books
    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/books");
    ResultActions actions = mvc.perform(builder);
    // 设定预期值 与真实值比较！
    // 定义本次调用的预期值
    ContentResultMatchers content = MockMvcResultMatchers.content();
    ResultMatcher result = content.string("springboot");
    // 添加预计值到本次调用过程中进行匹配
    actions.andExpect(result);

}
~~~

- 匹配响应体（Json）

~~~java 
@Test
    void testJson(@Autowired MockMvc mvc) throws Exception {
        //http://licalhost:8080/books
        //创建虚拟请求，当前访问/books
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/books");
        ResultActions actions = mvc.perform(builder);
        // 设定预期值 与真实值比较！
        // 定义本次调用的预期值
        ContentResultMatchers content = MockMvcResultMatchers.content();
        ResultMatcher result = content.json("{\"id\":1,\"name\":\"springboot\",\"type\":\"springboot\",\"description\":\"springboot\"}");
        // 添加预计值到本次调用过程中进行匹配
        actions.andExpect(result);

    }
}
~~~

- 匹配响应头

~~~java
@Test
    void testContentType(@Autowired MockMvc mvc) throws Exception {
        //http://licalhost:8080/books
        //创建虚拟请求，当前访问/books
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/books");
        ResultActions actions = mvc.perform(builder);
        // 设定预期值 与真实值比较！
        // 定义本次调用的预期值
        HeaderResultMatchers header = MockMvcResultMatchers.header();
        ResultMatcher string = header.string("Content-Type", "application/json");
        // 添加预计值到本次调用过程中进行匹配
        actions.andExpect(string);

    }
~~~

- 合并

~~~java 
    @Test
    void testGetById(@Autowired MockMvc mvc) throws Exception {

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/books");
        ResultActions actions = mvc.perform(builder);
        HeaderResultMatchers header = MockMvcResultMatchers.header();
        ResultMatcher string = header.string("Content-Type", "application/json");
        actions.andExpect(string);

        ContentResultMatchers content = MockMvcResultMatchers.content();
        ResultMatcher result = content.json("{\"id\":1,\"name\":\"springboot\",\"type\":\"springboot\",\"description\":\"springboot\"}");
        actions.andExpect(result);

        StatusResultMatchers status = MockMvcResultMatchers.status();
        ResultMatcher ok = status.isOk();
        actions.andExpect(ok);

    }
~~~

```java 
@Transactional注解能够实现在test的时候不往数据库里面添加东西 @Rollback配合使用，默认为true
    
```

- 测试用例设置随机值

## ![image-20220507111437282](Springboot%E5%BC%80%E5%8F%91%E4%BD%BF%E7%94%A8%E7%AF%87.assets/image-20220507111437282.png)4、数据层解决方案

### A: SQL关系型数据库解决方案

SpringBoot提供了三种内嵌的数据源对象公开发者使用

- HikariCp
- Tomcat提供DataSource
- commons DBCPD

~~~yml
spring :
 datasource:
  driver-class-name: com.mysql.cj.jdbc.Driver
  url: jdbc:mysql://localhost:3308/ssm?serverTimezone=UTC
  username: root
  password: root	
~~~

使用场景：

- HikariCp：默认内置数据源对象
- Tomact提供的Datasource:HikariCP不可用情况下，且在iweb环境中。将Tomcat服务器配置的数据源对象
- Commons DBCP：Tomcat数据源不可用，Hikari也不可用，将使用dbcp数据源

### B: NoSQL解决方案 

市面上常见的NoSQL解决方案

- Redis
- MongoDB
- ES

Redis是一款key-value存储结构的内存级NoSQL数据库

- 支持多种数据存储格式
- 支持持久化
- 支持集群

### C: springboot导入redis

- 导入Redis对应的starter

~~~xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
~~~

- 配置

~~~yml
spring:
  redis:
    host: localhost
    port: 6379
~~~

- 操作实例

~~~java
package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
class Demo11ApplicationTests {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void set() {
        ValueOperations ops = redisTemplate.opsForValue();
        ops.set("age",41);

    }
    @Test
    void get(){
        ValueOperations ops = redisTemplate.opsForValue();
        Object age = ops.get("age");
        System.out.println(age);
    }

    @Test
    void hset() {
        HashOperations ops = redisTemplate.opsForHash();
        ops.put("info","a","songyinzhong");
    }
    @Test
    void hget(){
        HashOperations ops = redisTemplate.opsForHash();
        Object val = ops.get("info", "a");
        System.out.println(val);
    }

}
~~~

### D: MongoDB

- MongoDB是一个开源的、高性能、无模式的文档型数据库。NoSQL数据库产品中的一种，是最像关系型数据库的非关系型数据库。
- 可视化客户端：Robo 3T

springboot整合MongoDB：

- pom导入相关坐标

~~~xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-mongodb</artifactId>
</dependency>
~~~

- 配置`application.yml`文件

~~~xml
spring：  
 data:
    mongodb:
      uri: mongodb://localhost/song
~~~

### E: es

## 5、 整合第三方技术

### A: 缓存

- 导入pom中的依赖坐标;

~~~xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-cache</artifactId>
</dependency>
~~~

- 使用`@EnableCaching`开启缓存,在方法上使用`@Cacheable（value="cahcheSpace",key="#id"）`注解标记使用注解；





