# Servlet笔记

~~~java
public class AddServlet extends HttpServlet {
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //post方法下要设置字符编码；防止中文乱码；设置编码必须放在代码的第一行；
        //get方式下不需要设置编码格式；

        request.setCharacterEncoding("UTF-8");
        String fname = request.getParameter("fname/");
        String priceStr = request.getParameter("price");
        Integer price = Integer.parseInt(priceStr);
        String fcountStr = request.getParameter("fcount");
        Integer fcount = Integer.parseInt(fcountStr);
        String remark = request.getParameter("ramark");
        System.out.println("fname="+fname);
        System.out.println("price = " + price);
        System.out.println("fcount = "+fcount);
        System.out.println("remark = "+ remark);
        System.out.println("添加成功！");
    }
~~~

## 1、设置编码

Tomcat8之前，设置编码：

- get 请求方式：

  - get方式目前不需要设置编码（基于Tomcat8）

  - Tomcat8之前设置编码

    ~~~java
    String fname = request.getParameter("fname");
    // 1.将字符打散成字节数组
    byte[] byte = fname.getBytes("ISO-8859-1");
    // 2.将字节数组按照设定的编码格式重新组装成字符串
    fname = new String(byte,"UTF-8");
    ~~~

- psot请求方式：

  ~~~java 
  request.setCharacterEncoding("UTF-8");
  ~~~

tomcat8开始，设置编码，只需要针对post方式；

~~~java
request.setCharacterEncoding("UTF-8");
~~~

note：需要注意的是，设置编码（post）这一句代码必须放在所有获取参数之前；

## 2、Servlet继承关系

1.继承关系

- javax.servlet.Servlet接口
  - javax.servlet.GenericServlet抽象类
    - javax.servlet.http.HttpServlet

2.相关方法

javax.servlet.Servlet接口

- void init（） - 初始方法
- void service(request,response) - 服务方法
- void destory() - 销毁方法

javax.servlet.GenericServlet

- void service(request,response) - 任然是抽象方法

javax.servlet.http.HttpServlet

- void service（request，response）- 不是抽象方法
  1. String method = req.getMethod()
  2. 各种if判断，根据请求方式不同，决定调用方法
  3. 在HttpServlet这个抽象类中，do方法差不多

3.小结:

- 继承关系：HttpServlet -> GenericServlet -> servlet
- 新建Servlet时，我们才会考虑使用那个方法，决定去重写那个do方法；

## 3、Servlet生命周期

1. **生命周期：**从出生到死亡。对应Servlet中的三个方法，init（），service(request,response)，void destory() 
2. **默认情况下：**
   - 第一次请求时，这个Servlet会进行实例化、初始化、然后服务；
   - 从第二次开始，每一次都是服务；
   - 当Tomcat容器关闭时，调用销毁；
   - Servlet实例Tomcat只会创建一个，所有的请求都是这个实例去响应；

优点：提高系统的启动速度；

缺点：第一次请求时耗时太长；

因此如果要提高系统的启动速度，按照默认情况下实例化即可，如果要提高系统的响应速度，要设置Servlet初始化时机。

3. **初始化时机：**
   - web.xml`load_on_startup`设置Servlet启动先后顺序，数值最小为0；

4. **Servlet是单例的，线程不安全的；**

   - 单例：所有的请求都是同一个实例去响应的；

     尽量不在Servlet里面定义成员变量，非得要定义，不要在Servlet里面修改成员变量的值！

**错误：**

**200：正常响应；**

**404：找不到资源；**

**405：请求方式不支持；**

**500 ：服务器内部错误；**

## 4、Http协议

1. http称之为超文本传输协议
2. http是无状态的
3. http请求包含两个部分;请求和响应
   - 请求：
     - 请求包含三个部分，1、请求行；2、请求头；3、请求体；
       - 请求行包含三个信息，请求的方式，请求的URL，请求的协议
       - 请求头：包含客户端要告诉服务器的信息；
       - 请求体：三种情况，get方式，没有请求体，但是有一个queryString；post方式有请求体，json格式，有请求体，request payload；

- 响应：
  - 响应行：协议、响应状态码、响应状态；
  - 响应头：包含了服务器的信息；
  - 响应体：响应得到实际内容；

## 5、会话

Http是无状态：多次请求服务器无法判断是同一个客户端还是多个服务端发送过来的。

**无状态带来的现实问题：**

- 无法区分导致混乱；

**通过会话跟踪技术来解决无状态问题:**

- 客户端第一次给服务器发送请求，服务器获取`session`，获取不到，就会创建新的`session ID`，然后响应给服务器；
- 第二次客户端给服务器发送请求时，就会把`session ID`带给服务器；

```java 
request.getSession()//获取当前会话，没有就创建新的
request.getSession(true)//效果和第一条一样
request.getSession(false)//获取当前会话，没有也不会创建新的，返回null；

session.getId() //获取session ID 
session.isNew()//判断session是否为新的；
session.getMaxInactiveInterval()//session的非激活间隔时长，默认1800秒，半个小时！；
session.getMaxInactiveInterval()
session.invalidate()//强制会话失效！
```

**session保存作用域：**

- session保存的作用域是与一个session对应的
- 常用的Api：
  - void session.setAttribute(k,v)
  - Object session.getAttribute(k)
  - void removeAtrribute(k)

## 6、服务器内部转发以及客户端重定向

1、 服务器内部转发：request.getRequestDispatcher("....").forward(request,response)

- 一次请求响应的过程：客户端不知道内部跳转相信；
- 地址栏没有变化；

2、客户端重定向：response.sendRedirect("......");

- 两次请求，两次响应过程，客户端知道跳转详细信息；
- 地址栏有变化；

**302：表示重定向；**

## 7、Thymeleaf

**视图模板技术**

1. 添加thymeleaf的jar包；
2. 在web.xml中配置； 配置前缀 view-prefix
   - 配置后缀 view-suffix
3. 新建一个Servlet类ViewBaseServlet；
4. 使得Servlet继承ViewBaseServlet；
5. 使用thymeleaf标签
   - th:if
   - th：unless
   - th:each
   - th:text

## 8、保存作用域

原始情况下，保存作用域可以认为有四个：page（页面级别）、request（请求级别）、session（一次会话范围）、

- request：一次请求范围；
- session：一次会话范围有效；
- application：request.getServletContext(),一次应用程序范围有效。Tomcat一次启动；

路径问题：

- 相对路径；`../`表示上一级；
- 绝对路径；

## 9、Servlet初始化方法

Servlet生命周期器：实例化、初始化、服务、销毁；

Servlet初始化方法有两个：inti（），init（Servlet config）；

```java  
 public void init(ServletConfig config) throws ServletException {
        this.config = config;
        this.init();
    }
```

```java 
public void init() throws ServletException {
    }
```

若我们想要在Servlet初始化的时候做一些工作，就重写Servlet方法；

```java 
@Override
public void init() throws ServletException {
    ServletConfig config = getServletConfig();
    String initValue=config.getInitParameter("hello");
    System.out.println("initValue= " +initValue);
}
}
```

**注解：**

```java 
@WebServlet(urlPatterns = {"/demo01"},
            initParams = {
                @WebInitParam(name = "hello",value = "world!"),
                @WebInitParam(name="uname",value = "jim")
            })
```

**在web.xml中配置Servlet；**

```xml
<servlet>
    <servlet-name>Demo01</servlet-name>
    <servlet-class>com.song.sevlet.Demo01</servlet-class>
    <init-param>
        <param-name>hello</param-name>
        <param-value>world!</param-value>
    </init-param>
</servlet>
<servlet-mapping>
    <servlet-name>Demo01</servlet-name>
    <url-pattern>/Demo01</url-pattern>
</servlet-mapping>
```

Servlet中的ServletContext 和`<context-param>`

1. 获取ServletContext，有很多方法：
   - 在初始化方法中：`ServletContext servletContext= getServletContext();`
   - 在服务方法中：可以通过request对象获取；`req.getServletContext();`
   - 也可以通过session获取：`req.getSession().getServletContext();`

2. 获取初始化值：

   `tring contextConfigLoction= servletContext.getInitParameter("contextConfigLoction");`

**业务层：**

1. MVC：Model（模型）、View（视图）、controller（控制器）；

- 视图层：用于做数据展示和用户交互的界面；
- 控制层：接受客户端的请求，具体的业务功能还是要借助model来实现；
- 模型：模型分很多种，有比较简单的pojo/vo（value object）有业务模型组件，有数据访问层组件；
  - pojo/vo:值对象
  - DAO：数据访问对象
  - BO：业务对象

区分业务对象和数据访问对象：

- DAO中的方法都是单精度方法：一个方法只考虑一个操作或一件事情；因此属于细粒度的；
- BO中的方法是业务方法，也实际的业务是比较复杂的，因此业务方法是粗粒度的；

注册这个功能属于业务功能，也就是说注册这个方法属于业务方法，因此包含很多DAO方法，也就是说注册这个业务需要调用很多个DAO方法，从而完成注册功能的开发。

**IOC:**

1. 耦合/依赖：在软件系统中，层与层之间存在依赖，也称之为耦合；系统架构或者设计的一个原则是：高内聚，低耦合；层内部的组成应该是高度聚合的，而层与层之间的关系是低耦合的，最理想化的情况是0耦合；

IOC: 控制反转，/DI 依赖注入

## 10、Filter

1. filter也属于Servlet规范
2. filter开发步骤：新建类实现Filter接口，然后实现其中的三个方法：init、doFilter、destory
   - 配置Filter可以使用注释@webFilter 和web.xml；

3. filter在配置时，和Servlet一样也可以配置通配符；
4. 过滤器链：
   - 多个filter执行的顺序：如果采取的是注解的方式，那么filter的执行方式是按照全类名先后顺序排序的
   - 如果采取的是web.xml方式进行配置的，那么按照配置的先后顺序执行。

## 11、事务管理：

- ThreadLocal

  - get（），set（）

  - ThreadLocal称之为本地线程，可以通过set方法在当前线程上存储数据。通过get方法在当前线程上获取数据；

  - set（）：

    ~~~java
    private T setInitialValue() {
        T value = initialValue();
        Thread t = Thread.currentThread();
        ThreadLocalMap map = getMap(t);
        if (map != null)
            map.set(this, value);
        else
            createMap(t, value);
        return value;
    }
    ~~~

- get（）：

  ~~~java
  public T get() {
      Thread t = Thread.currentThread();
      ThreadLocalMap map = getMap(t);
      if (map != null) {
          ThreadLocalMap.Entry e = map.getEntry(this);
          if (e != null) {
              @SuppressWarnings("unchecked")
              T result = (T)e.value;
              return result;
          }
      }
      return setInitialValue();
  }
  ~~~

  ## 12、监听器

  1. ServletContextListener:监听ServletContext对象的创建和销毁的过程；

  2. HttpSessionListener：监听Session对象的创建和销毁的过程；

  3. ServletRequestListener：监听Request对象的创建和销毁的过程

     

  4. ServletContextAttributeListener：监听ServletContext的保存作用域的改动；

  5. HttpSessionAttributeListener：监听HttpSession的保存作用域的改动；

  6. ServletRequestAttributeListener：监听ServletRequest的保存作用域的改动；

     

  7. httpSessionBindingListener：监听某个对象在session域里面创建和移除；

  8. HttpSessionActivationListener：监听某个对象在session域中的序列化与反序列化；

