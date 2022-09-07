# Linux学习笔记

## 第一节:Linux简介

Linux，全称GNU/Linux，是一种免费使用和自由传播的[类UNIX](https://baike.baidu.com/item/类UNIX/9032872)操作系统，其内核由[林纳斯·本纳第克特·托瓦兹](https://baike.baidu.com/item/林纳斯·本纳第克特·托瓦兹/1034429)于1991年10月5日首次发布，它主要受到[Minix](https://baike.baidu.com/item/Minix/7106045)和Unix思想的启发，是一个基于[POSIX](https://baike.baidu.com/item/POSIX/3792413)的多用户、[多任务](https://baike.baidu.com/item/多任务/1011764)、支持[多线程](https://baike.baidu.com/item/多线程/1190404)和多[CPU](https://baike.baidu.com/item/CPU/120556)的操作系统。它能运行主要的[Unix](https://baike.baidu.com/item/Unix/219943)工具软件、应用程序和网络协议。它支持[32位](https://baike.baidu.com/item/32位/5812218)和[64位](https://baike.baidu.com/item/64位/2262282)硬件。Linux继承了Unix以网络为核心的设计思想，是一个性能稳定的多用户网络操作系统。Linux有上百种不同的发行版，如基于社区开发的[debian](https://baike.baidu.com/item/debian/748667)、[archlinux](https://baike.baidu.com/item/archlinux/10857530)，和基于商业开发的[Red Hat Enterprise Linux](https://baike.baidu.com/item/Red Hat Enterprise Linux/10770503)、[SUSE](https://baike.baidu.com/item/SUSE/60409)、[Oracle Linux](https://baike.baidu.com/item/Oracle Linux/6876458)等。

Linux不仅系统性能稳定，而且是[开源软件](https://baike.baidu.com/item/开源软件/8105369)。其核心防火墙组件性能高效、配置简单，保证了系统的安全。在很多企业网络中，为了追求速度和安全，Linux不仅仅是被网络运维人员当作服务器使用，甚至当作网络防火墙，这是Linux的一大亮点。 [2] 

Linux具有[开放源码](https://baike.baidu.com/item/开放源码/7176422)、没有版权、技术社区用户多等特点，开放源码使得用户可以自由裁剪，灵活性高，功能强大，成本低。尤其系统中内嵌网络协议栈，经过适当的配置就可实现路由器的功能。这些特点使得Linux成为开发路由交换设备的理想开发平台。 [3] 

## 第二节：Linux安装

### 2.1 安装方式

- 物理机安装：直接将操作系统安装到服务器硬件上
- 虚拟机安装：通过虚拟机软件安装

**虚拟机：**通过软件模拟的具有完整硬件系统功能、运行在完全隔离的环境中的计算机系统；常用的虚拟机软件有**VMWare**，VirtualBox。VMLite WorkStation，Qemu，HopeddotVOS。最常用的就是VMWare。

使用VMware安装centos7，安装完成后点击重启，进入Linux操作系统，实现操作。

第一次由于启动服务器时没有加载网卡，导致IP地址初始化失败，使用`ip addr`查看ip地址。如果能够显示出来IP地址，就没啥事儿了，如果显示为`ff:ff:ff:ff:ff:ff`就说明没有初始化成功，需要进一步处理。

**处理办法：**

- `cd /` 	进入根目录
- `cd etc` 
- `cd sysconfig`
- `cd network-scripts`
- `vi ifcfg-ens33`    命令行编辑这个文件。
- 输入`i`进入编辑模式，将最后一个参数`ONBOOT=no`修改为`yes`。按键盘`esc`按钮推出编辑模式，输入`：wq`保存退出。

上述操作完成后。重启虚拟机，输入`ip addr`查看变化。

### 2.2安装SSH（Secure Shell）连接工具

SSH是建立在应用层基础上的安全协议。常用的SSH远程连接工具有Finalshell，xshell，MobaXterm等；

安装过程：傻瓜式安装就行！

### 2.3 Linux系统中的目录

- `/`是所有目录的顶点
- 目录结构像一颗倒挂的树

![](LINUX%E5%AD%A6%E4%B9%A0%E7%AC%94%E8%AE%B0.assets/Linux%E7%9B%AE%E5%BD%95%E7%BB%93%E6%9E%84.jpg)

## 第三节：Linux常用命令

### Linux初体验：几个常用的命令

| 序号 |       命令       |       对应英文       |           作用           |
| :--: | :--------------: | :------------------: | :----------------------: |
|  1   |        ls        |         list         |   查看当前目录下的内容   |
|  2   |       pwd        | print work directory |     查看当前所在目录     |
|  3   |   cd [目录名]    |   change directory   |         切换目录         |
|  4   |  touch [文件名]  |        touch         | 如果文件不存在就新建文件 |
|  5   |  mkdir [目录名]  |    make directory    |         创建目录         |
|  6   |   rm [文件名]    |        remove        |       删除指定文件       |
|  7   | rmdir [文件夹名] |   remove directory   |      删除一个文件夹      |



**Note:**如果控制台乱码的话：使用命令`echo 'LANG="en_US.UTF-8" ' >> /etc/profile`向配置文件里面添加内容，设置文字编码。再使用`source /etc/profile`让刚刚添加的内容立即生效。

### Linux初体验：Linux命令使用技巧

- `Tab`键自动补全
- 连续两次`Tab`，给出操作提升
- 使用上下箭头快速调出曾经使用过的命令
- 使用`clear`命令或者`Ctrl+l`快捷实现清屏

### Linux初体验：常用命令：

1. 文件目录操作命令：`cat`：用于显示文件内容；语法：`cat [-n] filename`。-n对行号进行编号；

2. `more`命令：以分页的方式显示文件内容；语法：`more filename`
   - 回车键：向下滚动一行
   - 空格键：向下滚动一个屏幕
   - b：返回上一个屏幕
   - q或者Crtl+c：退出more
   
3. `tail`命令：查看文件末尾的内容；语法：`tail [-f] filename` ,-f动态的读取文件末尾内容并显示。通常用于日志文件的显示；

4. `mkdir [-p] filename`:创建多层目录，如`mkdir -p 第一层/第二层/第三层`

5. `rmdir [-p] dirname:`删除空目录，`-p`：当子目录被删除后使得父目录为空的话，则一并删除父目录

6. `rm [-rf] name`:删除文件或者目录，`-r`：将目录及目录中的所有文件逐一删除，即递归删除；`-f`：无需确认，直接删除；

7. `cp [-r] source dest`:用于复制文件或者目录，`-r`：如果是复制目录的时候就需要，会将目录下面的子文件都拷贝，文件的话不需要加这个命令；

8. `mv source dest`:为文件或者目录改名、或者将文件或目录移动到其他位置；

9. `tar [-zcxvf] filename [files]`:对文件进行打包、解包、压缩、解压。包文件后缀`.tar`表示只是完成了打包，并没有压缩；包文件后缀为`.tar.gz`表示打包的同时还进行了压缩；-z：z代表的是gzip，通过gzip命令处理文件，gzip可以对文件压缩或者解压；-c：c代表的是create，即创建新的包文件；-x：x代表的是extract，实现从包文件中还原文件；-v：v代表的是verbose，显示命令的执行过程；-f：f代表的是file，用于指定文件的名称；
   - 举例：
     - `tar -cvf hello.tar ./*` : 将当前目录下所有文件进行打包，打包后的名称为hello.tar
     - `tar -zcvf hello.tar.gz ./*` :将当前目录下的所有文件进行打包并压缩，打包后的文件名称为hello.tar.gz
     - `tar -xvf hello.tar` : 将hello.tar这个文件解包，放在当前目录下
     - `tar -zxvf hello.tar.gz` : 将hello.tar.gz这个文件解压缩放在当前目录下
     - `tar -zxvf hello.tar.gz -C /usr/local` : 将这个文件进行解压缩并放到指定目录下
   
10. `vi filename` ：vi命令是Linux提供的一个文本编辑工具，可以对文件内容进行编辑；vim是从vi发展来的一个功能更加强大的文本编辑工具，在编辑文件的时候可以对文本内容进行着色，方便我们对文本内容进行编辑。要使用vim命令需要我们自己进行安装，可以使用`yum install vim`命令进行安装；

11. `vim filename` : 
    - 说明：在使用vim命令进行编辑的时候，如果指定文件存在则直接打开文件，如果不存在的话则新建文件。
    
    - 说明：vim在进行文本编辑的时候分三种模式。分别是命令模式（command mode）插入模式（insert mode）和底行模式（last line mode）。这三种模式可相互切换。
      - 命令模式：命令模式下可以查看文件内容、移动光标（**上下左右箭头、gg、G**），通过vim命令打开文件后默认为命令模式，其他两种模式必须先进入命令模式，才可以相互转换。
      - 插入模式：插入模式可以对文件进行编辑，通过键盘按下[i,a,o]任意一个，进入插入模式，下方会出现insert字样，在插入模式下按**ESC**按键，回到命令模式；
      - 底行模式：底行模式可以通过命令对文件内容进行查找、显示行号、退出等操作，在命令模式下按下`[:,/]`任意一个，可以进入底行模式，通过/进入底行模式后，可以对文件内容进行查找，通过：方式进入底行模式后，可以输入`wq`（保存并退出）`q！`（不保存退出）、`set nu`（显示行号）；
      
      **Note：**输入的时候一定要是英文输入法状态下；
    
12. `find dirname -option filename`:在指定目录下查找文件

    - `find . -name "*.java" :` 在当前目录下及其子目录下查找.java结尾的文件
    - `find /test -name "*.java"` : 在/test目录下及其子目录下查找.java结尾的文件

    **Note** :没有输出就没有找到

13. `grep word filename` : 从指定文件中查找指定的文本内容

    - `grep Hello helloworld.java` : 查找helloworld.javaz中出现hello字符串出现的位置
    - `grep hello *.java` : 查找当前目录中以.java结尾的文件中包含hello字符串的位置

## 第四节：软件安装

### 软件安装方式

- 二进制发布包安装：软件已经针对具体平台编译发布，只要要解压，修改配置即可
- rpm安装：软件已经按照Redhat的包管理规范进行打包，使用rpm命令进行安装，但是不能自行解决库依赖问题
- yum安装：一种在线软件安装方式，本质上还是rpm安装，自动下载安装包并安装，安装过程中自行解决库依赖问题
- 源码编译安装：软件以源码工程的形式发布，需要自己编译打包

### 安装jdk

操作步骤：

- 使用finalshell自带的上传功能将jdk的二进制发布包上传到Linux服务器上
- 解压安装包，命令为`tar -zxvf [二进制包名] -C/usr/loacl`,将二进制包解压到/local文件夹下；
- 配置环境变量，使用vim命令修改/etc/profile文件，在文件末尾添加如下配置
  - `JAVA_HOME=/usr/local/jdk1.8.0`
  - `PATH=$JAVA_HOME/bin:$PATH`
- 重新加载配置文件，使得更改的配置立即生效，命令为`source /etc/profile`
- 检查是否安装成功。命令为`java -version`

### 安装Tomcat

操作步骤：

- 使用finalshell上传二进制文件安装
- 解压安装包
- 进入Tomcat的bin目录启动服务，命令为`sh startup.sh`或者`./startup.sh`

验证Tomcat是否启动成功：

- 查看日志：使用`more /logs/catalina.out`或者`tail -20 /logs/catalina.out`
- 查看进程： `ps -ef | grep tomcat`
  - ps是Linux下非常强大的进程查看命令，通过`ps -ef` 可以查看当前运行的所有进程的详细信息
  - `|` 是Linux中的管道符，可以将前一个命令的结果输出作为后一个命令的输入
  - 使用ps命令查看进程的时候，经常配合管道符和查找命令grep一起使用，来查看特定的进程

防火墙操作：

- 查看防火墙状态：(`systemctl status firewalld` 、`firewall-cmd --state`)
- 暂时关闭防火墙：（`systemctl stop firewalld`）
- 永久关闭防火前:  (`systemctl disable firewalld`)
- 开启防火墙：(`systemctl start firewalld`)
- 开放指定端口：(`firewall-cmd --zone=public --add-port=8080/tcp --permanent`)
- 关闭指定端口：(`firewall-cmd --zone=public --remove-port=8080/tcp --permanent`)
- 立即生效：(`firewall-cmd --reload`)
- 查看开放的端口：(`firewall-cmd --zone=public --list-ports`)

Note:

- systemctl是管理Linux中 服务的命令，可以对服务进行启动、停止、重启、查看状态等操作
- firewalld-cmd是Linux中专门用来控制防火墙的命令
- 为了保证系统安全，服务器的防火墙不建议关闭

停止Tomcat服务方式：

- 运行Tomcat的bin目录下的shutdown.sh脚本文件 `sh shutdown.sh`，或者`./shutdown.sh`

- 结束Tomcat进程

  - 查看Tomcat进程，获得进程PID：`ps -ef | grep tomcat`
  - 执行命令：`kill -9 PID号`

  **Note：**kill命令是Linux提供的用于结束进程命令，-9表示强制结束；

### 安装MySQL

1. 检测当前系统中是否已经安装了MySQL数据库

   ```
   rpm -qa // 查询当前系统中安装的所有软件
   rpm -qa | grep mysql // 查询当前系统中是否安装了mysql
   rpm -qa |grep mariadb //// 查询当前系统中是否安装了mariadb
   ```

   **Note**:如果当前系统中安装了MySQL数据库的话，安装将失败，Centos7自带mariadb，与MySQL冲突；

2. 卸载冲突的软件

   ```
   rpm -e --nodeps 软件名称
   rpm -e --nodeps mariadb-libs-5.5.60-1.el7_5.x86_64
   ```

3. 将MySQL安装包上传到Linux服务器上并进行解压操作

   安装MySQL是有顺序的，否则有可能会安装失败,按照如下顺序进行安装

   ```
   rpm -ivh mysql-community-common-5.7.25-1.el7.x86_64.rpm
   rpm -ivh mysql-community-libs-5.7.25-1.el7.x86_64.rpm
   rpm -ivh mysql-community-devel-5.7.25-1.el7.x86_64.rpm
   rpm -ivh mysql-community-libs-compat-5.7.25-1.el7.x86_64.rpm
   rpm -ivh mysql-community-client-5.7.25-1.el7.x86_64.rpm
   yum install net-tools
   rpm -ivh mysql-community-server-5.7.25-1.el7.x86_64.rpm
   ```

   Note：

   - 安装过程中如果提升缺少net-tools依赖，使用yun安装；

   - 可以通过指定升级现有软件及系统内核 `yum update`，更新时间还是需要一两分钟的；

4. 启动MySQL服务

   ```
   systemctl status mysqld // 查看mysql服务状态
   systemctl start mysqld // 启动mysql服务
   ```

   可以设置开机自启动mysql，避免每次手动启动

   ```
   systemctl enable mysqld // 开机自启动
   netstat -tunlp // 查看已经启动的服务
   netstat -tunlp | grep mysqld
   
   ps -ef | grep mysqld // 查看mysql进程
   ```

5. 登录MySQL数据库，查阅临时密码

   ```
   cat /var/log/mysqld.log // 查看文件内容
   cat /var/log/mysqld.log | grep password // 查看文件中包含password的行信息
   ```

   **Note：**冒号后面是密码，注意空格

6. 登录MySQL，修改密码，开放访问权限

   ```
   mysql -urrot -p // 登录mysql
   #修改密码
   set global validate_password_length=4; // 设置密码长度
   set global validate_password_policy=LOW; // 设置密码安全等级，便于密码修改
   set password = password('root');
   #开启访问权限
   grant all on *.*to'root'@'%'identified by 'root';
   flush privileges;
   ```

### 安装lrzsz

**lrzsz是一个文件上传的工具，跟finalshell文件上传功能一样；**

操作步骤：

1. 搜索lrzsz安装包，命令为`yum list lrzsz`
2. 使用yum命令在线安装，命令为`yum install lrzsz.x86_64`

​       **Note:**Yum是一个在Fedora和Redhat以及centos中的shell前端软件包管理器。基于RPM包管理，能够从指定的服务器上自动下载包并进行安装，可以自动处理依赖关系，并且一次安装所有的依赖软件包，无需繁琐的一次次下载安装；

3. 在命令行输入`rz`后回车即可选择上传的文件

## 第五节：项目部署

### 手工部署项目

1. 将我们本地编写好的程序打成jar包，上传到Linux服务器上
2. 通过`java -jar [jar包名称]`运行项目，
3. 后台运行程序：nohup 命令：用于不挂断的运行指定命令，退出终端不会影响程序运行；
   - `nohup command [Arg...] [&]`,command:要执行的命令，arg：一些参数，可以指定输出文件，&：让命令后台运行；
   - 例子：`nohup java -jar springboot项目.jar &> hello.log &` 
4. 停止后台运行程序，通过啥进程的形式；`ps -ef | grep java -jar` ,通过`kill -9 PID号`杀掉进程

### 通过shell脚本自动部署项目

1. 在Linux中安装git，通过`yum list git` 和`yum install git`安装，将远程仓库里面的项目`clone`到服务器上

2. 在Linux中安装maven，将Maven压缩包上传服务器解压到指定文件夹下，修改配置信息

   ```
   vim /etc/profile
   # 插入如下内容
   export MAVEN_HOME=/usr/local/apache-maven-3.8.4
   exprot PATH=$JAVA_HOME/bin:$MAVEN_HOME/bin:$PATH
   
   source /etc/profile
   mvn -version
   vim /usr/local/apache-maven-3.8.4/conf/settings/xml
   #修改配置内容如下
   <localRepositiry>/usr/loacl/repo</localRepositiry>
   ```

3. 编写shell脚本（拉取代码、编译、打包、启动）

4. 为用户授予shell脚本的权限

   `chmod`命令是控制用户对文件的访问权限

   | #    | 权限     | rwx  |
   | ---- | -------- | ---- |
   | 7    | 读写执行 | rwx  |
   | 6    | 读和写   | rw-  |
   | 5    | 读和执行 | r-x  |
   | 4    | 只读     | -w-  |
   | 3    | 写和执行 | -wx  |
   | 2    | 只写     | -w-  |
   | 1    | 只执行   | --x  |
   | 0    | 无       | ---  |

   举例：`chmod 777 [文件名]`为所有用户授予读写执行权限

   **Note：**第一位代表文件拥有者权限，第二位代表同组用户的权限，第三位代表其他用户权限；

5. 执行shell脚本

6. 设置静态IP,修改文件`/etc/sysconfig/network-scripts/ifcfg-ens`

```
#设置相关字段
BOOTPROTO = "static"
IPADDR="访问地址"
NETMASK ="子网掩码"
GATWAY = "网关地址"
DNS1 = "DNS服务器"
```

7. 重启网络服务，命令为：`systemctl restart network`;
