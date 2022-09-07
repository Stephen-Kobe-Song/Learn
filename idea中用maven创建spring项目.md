# idea中用maven创建spring项目

## step 1：

打开idea---> file菜单选项--->点击new--->点击project---> 点击maven

## step 2：

纯手工新建，不勾选create from arch type选项，一路next，注意选择自己的maven环境，点击finish完成创建；

## step 3：

在项目文件夹上右键并选择add Framework Support选项

- 勾选spring选项，建议勾选create empty spring-config.xml选项，会新建一个空的spring配置文件，选择这种方式需要将生成的spring-config.xml文件移动到resources文件夹下面；
  - 当然也可以不用，选择自己新建一个spring配置文件，在resources文件右键新建一个xml文件选择spring-config选项。
  - 如果以前下载过spring的相关文件（jar），建议使用use library选项，节省本地内存。
  - 第一次使用则勾选download选项，系统会自动从网上下载spring所有的环境。

## step 4：

添加webapp文件夹：

- 在mian文件夹下面新建一个webapp文件夹；
- 点击file菜单---> 选择project structure选项--->点击modules--->点击`+`按钮；
- 选择下拉选项中`web`选项；
- 配置web resource directories ，选择文件目录到新建的webapp文件上；
- 配置deployment dscriptors， 点击`-`按钮删除已存在的，再点击`+`按钮新建一个Type，配置路径到webapp上；
- 选择vision版本为3.0或者以上版本即可，最好选择3.0或4.0；

## 注意：第一次运行项目时，若出现找不到主类的情况

- 点击idea右侧`maven`按钮，点击项目下面的Lifecycle文件夹下的`clean`按钮；
- 再点击`install`按钮；