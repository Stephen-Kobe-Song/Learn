# Vue学习笔记

## 第一节：Vue.js简介

### 1、框架

任何编程语言在最初的时候都是没有框架的，后来随着在实际开发过程不断总结经验，发现很多**特定场景**下的**特定问题**总是可以有**固定的解决方案**。**成熟的固定方案**整合在一起就形成了**框架**。

- Java程序：框架就是**固定方案的jar包**，然后通过**配置文件**告诉框架做什么，极大地简化编程代码，提高开发效率。
- JavaScript：框架就是封装了**固定解决方案**的**JS**文件。

### 2、作者

在为AngularJS工作之后，Vue的作者**尤雨溪**开Vue.js。他声称自己的思路是提取Angular中自己喜欢的部分，构建出一款相当轻量的框架。Vue最早发布于2014年2月。作者在Hacker News、Echo JS与 Reddit的JavaScript版块发布了最早的版本。一天之内，Vue 就登上了这三个网站的首页。

## 第二节：Vue.js环境

对于Java程序来说，框架=jar包+配置文件。对于Vue来说，导入**Vue的外部js文件**就能够使用Vue框架了。

### 1、Vue框架的js文件获取

官网提供的下载地址：https://cdn.jsdelivr.net/npm/vue/dist/vue.js

### 2、创建本地Vue.js文件

将官网提供的vue.js文件的内容复制粘贴到本地vue.js文件中。在本地开发环境中创建一个文件夹，将官方的Vue.js复制到新建文件夹里面去。

### 3、创建HTML文档并引入Vue.js

~~~javascript
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>引入Vue.js</title>
</head>
<body>
        <!--重点部分！-->
    <script src="/xxx/script/Vue.js " type="text/javascript" charset="UTF-8"></script>
    <script type="text/javascript">
        
    </script>
</body>
</html>
~~~

## 第三节：Vue.js基本语法：声明式渲染

### 1、概念：

声明式是相当于编程式而言的。

- 声明式：告诉框架做什么，不自己编写代码。
- 编程式：自己编写代码完成指定的任务。

### 2、demo

- HTML代码：

~~~javascript
<!-- 使用{{}}格式，指定要被渲染的数据 -->
<div id="app">{{message}}</div>
~~~

- Vue代码

~~~javascript
// 1.创建一个JSON对象，作为new Vue时要使用的参数
var argumentJson = {
	
	// el用于指定Vue对象要关联的HTML元素。el就是element的缩写
	// 通过id属性值指定HTML元素时，语法格式是：#id
	"el":"#app",
	
	// data属性设置了Vue对象中保存的数据
	"data":{
		"message":"Hello Vue!"
	}
};

// 2.创建Vue对象，传入上面准备好的参数
var app = new Vue(argumentJson);
~~~

## 第四节：Vue.js的基本语法

### 1、绑定元素属性

- **单向绑定：V-bind：Value**

​		**HTML代码：**

- ~~~html
  <div id="app">
  	<!-- v-bind:value表示将value属性交给Vue来进行管理，也就是绑定到Vue对象 -->
  	<!-- vueValue是一个用来渲染属性值的表达式，相当于标签体中加{{}}的表达式 -->
  	<input type="text" v-bind:value="vueValue" />
  	
  	<!-- 同样的表达式，在标签体内通过{{}}告诉Vue这里需要渲染； -->
  	<!-- 在HTML标签的属性中，通过v-bind:属性名="表达式"的方式告诉Vue这里要渲染 -->
  	<p>{{vueValue}}</p>
  </div>
  ~~~

​		**Vue代码：**

- ~~~js
  // 创建Vue对象，挂载#app这个div标签
  var app = new Vue({
  	"el":"#app",
  	"data":{
  		"vueValue":"太阳当空照"
  	}
  });
  ~~~

- **双向数据绑定**

​	**HTML代码：**

~~~js
<div id="app">
	<!-- v-bind:属性名 效果是从Vue对象渲染到页面 -->
	<!-- v-model:属性名 效果不仅是从Vue对象渲染到页面，而且能够在页面上数据修改后反向修改Vue对象中的数据属性 -->
	<input type="text" v-model:value="vueValue" />
	
	<p>{{vueValue}}</p>
</div>
~~~

 **Vue代码**

~~~js
// 创建Vue对象，挂载#app这个div标签
var app = new Vue({
	"el":"#app",
	"data":{
		"vueValue":"太阳当空照"
	}
});
~~~

**note：**

- value可以省略。
- .trim修饰符：可以去除掉前后空格。

~~~js
<input type="text" v-model="vueValue" />
~~~

~~~js
<input type="text" v-model.trim="vueValue" />
// Vue会帮助我们在文本框失去焦点时自动去除前后空格。
~~~

## 第五节：Vue.js基本语法：条件渲染

根据vue对象中，数据属性的值来判断是否对HTML页面内容进行渲染

### 1、v-if

**HTML代码：**

~~~js
<div id="app">
	<h3>if</h3>
	<img v-if="flag" src="/pro03-vue/./images/one.jpg" />
	<img v-if="!flag" src="/pro03-vue/./images/two.jpg" />
</div>
~~~

**Vue代码：**

~~~js
	var app = new Vue({
			"el":"#app",
			"data":{
				"flag":true
			}
		});
~~~

### 2、v-if和v-else

**HTML代码：**

~~~js
<div id="app02">
	<h3>if/else</h3>
	<img v-if="flag" src="/pro03-vue/./images/one.jpg" />
	<img v-else="flag" src="/pro03-vue/./images/two.jpg" />
</div>
// v-if和v-else中间不跟有其他节点。
~~~

**Vue代码：**

~~~js
	var app02 = new Vue({
			"el":"#app02",
			"data":{
				"flag":true
			}
		});
~~~

### 3、v-show

**HTML代码：**

~~~js
<div id="app03">
	<h3>v-show</h3>
	<img v-show="flag" src="/pro03-vue/./images/mi.jpg" />
</div>
~~~

**Vue代码：**

~~~js
var app03 = new Vue({
			"el":"#app03",
			"data":{
				"flag":true
			}
		});
~~~

## 第六节：Vue基本语法：列表渲染

### 1、迭代一个简单的数组

**HTML代码：**

~~~js
<div id="app01">
	<ul>
		<!-- 使用v-for语法遍历数组 -->
		<!-- v-for的值是语法格式是：引用数组元素的变量名 in Vue对象中的数组属性名 -->
		<!-- 在文本标签体中使用{{引用数组元素的变量名}}渲染每一个数组元素 -->
		<li v-for="fruit in fruitList">{{fruit}}</li>
	</ul>
</div>
~~~

**Vue代码：**

~~~js
var app01 = new Vue({
	"el":"#app01",
	"data":{
		"fruitList": [
			"apple",
			"banana",
			"orange",
			"grape",
			"dragonfruit"
		]
	}
});
~~~

### 2、迭代一个对象数组

**HTML代码：**

~~~js
<div id="app">
	<table>
		<tr>
			<th>编号</th>
			<th>姓名</th>
			<th>年龄</th>
			<th>专业</th>
		</tr>
		<tr v-for="employee in employeeList">
			<td>{{employee.empId}}</td>
			<td>{{employee.empName}}</td>
			<td>{{employee.empAge}}</td>
			<td>{{employee.empSubject}}</td>
		</tr>
	</table>
</div>
~~~

**Vue代码：**

~~~js
var app = new Vue({
	"el":"#app",
	"data":{
		"employeeList":[
			{
				"empId":11,
				"empName":"tom",
				"empAge":111,
				"empSubject":"java"
			},
			{
				"empId":22,
				"empName":"jerry",
				"empAge":222,
				"empSubject":"php"
			},
			{
				"empId":33,
				"empName":"bob",
				"empAge":333,
				"empSubject":"python"
			}
		]
	}
});
~~~

## 第七节：Vue.js基本语法：事件驱动

### 1、demo：字符串顺序反转

**HTML代码：**

~~~js
<div id="app">
	<p>{{message}}</p>
	
	<!-- v-on:事件类型="事件响应函数的函数名" -->
	<button v-on:click="reverseMessage">Click me,reverse message</button>
</div>
~~~

**Vue代码：**

~~~js
var app = new Vue({
	"el":"#app",
	"data":{
		"message":"Hello Vue!"				
	},
	"methods":{
		"reverseMessage":function(){
			this.message = this.message.split("").reverse().join("");
		}
	}
});
~~~

### 2、demo：获取鼠标移动坐标信息

**HTML代码：**

~~~js
<div id="app">
	<div id="area" v-on:mousemove="recordPosition"></div>
	<p id="showPosition">{{position}}</p>
</div>
~~~

**Vue代码：**

~~~js
var app = new Vue({
	"el":"#app",
	"data":{
		"position":"暂时没有获取到鼠标的位置信息"
	},
	"methods":{
		"recordPosition":function(event){
			this.position = event.clientX + " " + event.clientY;
		}
	}
});
~~~

### 第八节：Vue.js基本语法：侦听属性

### 1、提出需求

~~~js
<div id="app">
	<p>尊姓：{{firstName}}</p>
	<p>大名：{{lastName}}</p>
	尊姓：<input type="text" v-model="firstName" /><br/>
	大名：<input type="text" v-model="lastName" /><br/>
	<p>全名：{{fullName}}</p>
</div>
~~~

在上面代码的基础上，我们希望firstName或lastName属性发生变化时，修改fullName属性。此时需要对firstName或lastName属性进行**侦听**。

具体来说，所谓**侦听**就是对message属性进行监控，当firstName或lastName属性的值发生变化时，调用我们准备好的函数。

### 2、Vue代码

在watch属性中声明对firstName和lastName属性进行**侦听**的函数。

~~~js
var app = new Vue({
	"el":"#app",
	"data":{
		"firstName":"jim",
		"lastName":"green",
		"fullName":"jim green"
	},
	"watch":{
		"firstName":function(inputValue){
			this.fullName = inputValue + " " + this.lastName;
		},
		"lastName":function(inputValue){
			this.fullName = this.firstName + " " + inputValue;
		}
	}
});
~~~

## 第八节：Vue.js基本语法：简化写法

### 1、v-bind简化

~~~js
// 正常写法
<input type="text" v-bind:value="message" />
 // 简化写法
<input type="text" :value="message" />
~~~

### 2、v-on简化写法

~~~js
// 正常写法
<button v-on:click="sayHello">SayHello</button>
// 简化写法
<button @click="sayHello">SayHello</button>
~~~

## 第九节：vue的生命周期

生命周期：一个对象从创建、初始化、工作再到释放、清理和销毁，会经历很多环节的演变。

**Vue允许我们在特定的生命周期环节中通过钩子函数来加入我们的代码**。

**HTML代码：**

~~~js
<div id="app">
	<p id="content">{{message}}</p>
	<button @click="changeValue">点我</button>
</div>
~~~

**Vue代码：**

~~~js
new Vue({
	"el":"#app",
	"data":{
		"message":"hello"
	},
	"methods":{
		"changeValue":function(){
			this.message = "new hello";
		}
	},
	
	// 1.实例创建之前
	"beforeCreate":function(){
		console.log("beforeCreate:"+this.message);
	},
	
	// 2.实例创建完成
	"created":function(){
		console.log("created:"+this.message);
	},
	
	// 3.数据挂载前
	"beforeMount":function(){
		console.log("beforeMount:"+document.getElementById("content").innerText);
	},
	
	// 4.数据已经挂载
	"mounted":function(){
		console.log("mounted:"+document.getElementById("content").innerText);
	},
	
	// 5.数据更新前
	"beforeUpdate":function(){
		console.log("beforeUpdate:"+document.getElementById("content").innerText);
	},
	
	// 6.数据更新之后
	"updated":function(){
		console.log("updated:"+document.getElementById("content").innerText);
	}
});
~~~

本文参考：[第四章 Vue.js | 代码重工 (gitee.io)](https://heavy_code_industry.gitee.io/code_heavy_industry/pro001-javaweb/lecture/chapter04/)