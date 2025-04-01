[TOC]



# 一、JavaScript

## 1. 概述

- js是一种轻量级的编程语言

- js是可插入html页面的编程代码

- js是一种弱类型语言

  > 只有类型只有number一种，用var声明

- 主要用来向html页面添加交互行为

- 跨平台性，绝大多数浏览器都支持

  >- 常用测试：
  > - alert()：弹窗
  > - console.log()：浏览器控制台
  >- 注释语句：
  > - //

## 2. HTML中引入JS代码

1. 方式一：直接写在HTML页面的script标签里面

2. 方式二：导入外部的js文件

   > ```html
   > <script src="js路径"></script>
   > ```

## 3. JS的类型声明

> JavaScript在声明时统一使用无类型（untyped）的“var”关键字
>
> JavaScript并没有避开数据类型，它的数据类型是根据所赋值的类型来确定的
>
> JavaScript对大小写敏感，变量命名规范：只能由英语字母，数字，下划线，美元符号构成，并不能以数字开头，并且不能是JavaScript保留字

## 4. 变量定义

```JavaScript
// JavaScript中只要是个数，那么就是数值类型
var x = 2;

// boolean
var x = true;

// 数组
var arr = new Array()
var  cars = new Array("array","b")
var car = ["a","b","c"]
```

## 5. 函数

1. **概述**：函数就是包裹在花括号中的代码块，前面使用了关键词function

> ```javascript
> function functionname(parm){
>  // 执行代码
> }
> ```

2. **常用函数**：

   - toString()：以字符串返回数值
   - toFixed(parm)：切割数字返回字符串值，它包含了指定位数小数的数字（保留几位小数）
   - toPercision()：切割方法，切割数字返回字符串值，从整数开始
   - parseInt()：字符串转为数字，允许空格，只返回首个数字（取整）
   - parseFloat()：字符串返回数字

   ---

   - length属性返回字符串的长度
   - indexOf（parm）方法返回字符串parm中指定文本首次出现的索引
   - slice(begin，final)：提取字符串
   - splict（begin，final）：切割成数组

   ---

   - toString()：将数组转换为字符串
   - pop()：将数组最后一个元素删除，返回被删除的元素
   - push()：添加一个元素，返回数组长度
   - splice(begin，number)：删除，返回数组
   - splice(begin, number, parm)：在begin的位置不删除，添加parm
   - sort()：对数组进行排序
   - reserve()：反序

   ---

   - Math.ceil(x)：返回大于x的最小整数
   - Math.floor(x)：返回小于x的最小整数
   - Math.random()：返回0~1之间的随机数
   - Math.round(x)：四舍五入取整
   - Math.max(x,y,z...)：返回最大值

# 二、DOM

## 1. 概述

文档对象模型（document object model）,DOM将html视为树结构

## 2. DOM的加载顺序（DOM解析）

浏览器发起请求，获得一份html代码之后，直到页面显示的过程

- 解析HTML结构（开始解析的时候已经开始构建dom数）
- 加载外部结构和样式表文件（引入外部的js和css文件，异步加载）
- 解析外部脚本和样式表文件（执行加载的外部的js和css文件）
- 构造html DOM模型（DOM数构建完毕，立即开始显示）
- 加载图片等外部文件
- 页面加载完毕

## 3. 获取DOM树中的结点

- `getElementtById()` ：id唯一
- `getElementsByName()` ：name不唯一
- `getElementsByTagName()` ：标签名

## 4. 添加结点

关键代码

```html
<script>
	functtion addli(){
        // 获得用户的输入结点
        var inputNode = document.getElementById("inputstr")
        // 获得ul结点
        var ulNode = document.getElementById("ul1")
        // 创建一个文本结点
        var textNode = document.createTextNode(inputNode.value)
        // 创建一个li结点
        var liNode = document.createElement("li")
        
        liNode.appendChild(textNode)
        
        ulNode.appendChild(liNode)
        
        inputNode.value = ""
    }
</script>
```



## 5. 删除结点

关键代码：

```html
<script>
    function removeli(){
        // 获得用户的输入结点
        var inputValue = document.getElementById("inputstr").value
        // 获得ul结点
        var ulNode = document.getElementById("ul1")
        // 获得某个结点的孩子
        var childNodes = ulNode.childNodes;
        // 参数是要删除的孩子
        ulNode.removeChild(childNodes[inputValue]);
    }
</script>
```

## 6. 替换结点

```html
// 获得用户要替换的内容
var inputStr = document.getElementById("inputstr")
// 获得要替换的下标
var inputTag = documen.getElementById("inputtag")
// 获得ul
var ulNode = document.getElementById("ul1")
// 获得某个需要替换的li结点
var liNode = ulNode.childNodes[inputTag.value]
// 创建一个用来替换的文本结点
var textNode = document.createTextNode(inputStr.value)

// 参数一：替换的新结点
// 参数二：替换的旧结点
liNode.replaceChild(textNode,liNode.childNodes[0])
```

## 7. innerText, innerHtml

```html
var divNode = document.getElementById("div1")
// 如此替换
divNode.innerTest = "需要替换的内容"
```

- 区别
  - innerHTML：替换文本中内容可以带标签，标签可以解释，innerTest不可解释标签