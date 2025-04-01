[TOC]



# 一、Vue

## 1. 概述

- 当下前端生态：

  > 三个基础：html，css，js
  >
  > 三个框架：angular，reactnative，vue

- Vue.js：渐进式框架，与其他重量级框架不同的是，Vue采用自底层向上增量开发的设计，Vue的核心库只关注视图层。另一方面，Vue完全有能力驱动采用单文件组件和Vue生态系统支持开发复杂页应用

  > 渐进式：插件多，从核心到完备的全家桶
  >
  > 增量：从少到多，从简单到复杂
  >
  > 单文件组件：一个文件描述一个组件
  >
  > 单页应用：经过打包生成一个单页html文件和一些js文件

## 2. 插值表达式

**前提**：

>  vue.js的导入:`<script src"vue.js的路径"></script>` 

**示例代码及解释**：

```html
<body>
    // {{}}：在Vue中是插值表达式，表达式内部引用的数据要去对应的Vue对象中查找
<div id="root">
    {{msg}}
    </div>
    
    <script>
        // new Vue根据Vue语法创建一个Vue对象
        // 这个Vue对象一旦创建会立即检测自己的el属性
        // 根据el属性标记的id，在dom树上立即找一个对应的结点
        // 若找到了对应的结点，则将这个结点重新按照Vue的语法重新生成
        new Vue({
            el:"#root",
            // data值是Vue固定的自定义参数的位置
            data:{
                msg:"zs"
            }
        })
    </script>
</body>
```

## 3. v指令

- `V-bind`：单向绑定，可以把Vue对象中的参数，绑定到对应作用域中html标签的属性上
- `V-model`：双向绑定，只能用在表单元素的value上

---

- `V-text`，`V-html`：在一个标签内部出入文本或标签，类似于innertest和innerHtml

---

- `V-show`：可以根据条件隐藏和显示内容

  > `<div v-show="booleanValue"></div>`

---

- `V-if`：分支语句

  > v-if和v-show的区别：v-if若不满足条件不显示并且不会在dom树上构建结点，v-show它的虽然不会显示，但依旧会在dom树上构建结点，只不过通过css隐藏了

- `V-for`：循环语句

  > **注意1**：v-for循环渲染，会得到多个v-for指令所在的标签
  >
  > **注意2**：我们通过v-for遍历出的每个标签，都应该有一个唯一的key
>
  > **注意3**：v-for遍历时可用in或of

  **示例代码**：

  ```html
  <div id="root">
      <ul>
          <li v-for="item in list" v-bind:key="item">{{item}}</li>
      </ul>
  </div>
  
  <script>
      new Vue({
          el:"#root",
          data:{
              list:["1","2","3"]
          }
      })
  </script>
  ```

---

- `v-on`：事件监听，触发到vue对象中去

  - 代码示例：

    ```html
    <div id="root">
        <div v-show="bool1">
            1111
        </div>
        <button v-on:click="changebool">
            隐藏
        </button>
    </div>
    <script>
    	new Vue({
            el:"#root",
            data:{
                bool1:true
            },
            methods:{
                changebool:function(){
                    this.bool1 = !this.bool1
                }
            }
        })
    </script>
    ```


---

- v-pre：阻止预编译，标签中加v-pre可以阻止插值表达式的解析

- v-once：只编译一次

  > setTimeout（”f()“ 5000）:将f()方法延迟5秒后执行

- v-cloak：延迟加载

## 4. 计算属性

computed：指的是一个属性依赖于其他的属性存在而存在，是通过其他属性计算而来

## 5. 侦听器/监听器

watch：监听一个属性的改变触发一个事件

> ```html
> <script>
> 	new Vue({
>         el:"#root",
>         data:{
>             msg:"",
>             num:0
>         },
>         watch:{
>             // 监听那个属性，方法名就是哪个属性
>             msg:function(){
>                 this.num++;
>             }
>         }
>     })
> </script>
> ```
>
> 

## 6. 模板

1. 概述：

   > template：一个字符串作为Vue实例的标识使用，模板将会 替换 换挂载的元素，挂载元素的内容将被忽略
   >
   > template所得到的字符串会替换掉与Vue对象建立联系的标签
   
2. 示例代码：

   ```html
   <div id="root">
       
   </div>
   
   <script>
   	new Vue({
           el:"#root",
           data:{
               msg:"123"
           },
           template:"<div>{{msg}}<div>"
       })
   </script>
   ```

   

## 7. 组件（components）

1. 概述：组件化：在Vue中，所谓组件就是Vue对象，一个页面可以通过多个组件构建，一个页面可用通过多个Vue对象构成

2. 示例代码

   ```html
   <div id="root">
       
   </div>
   
   <script>
   	var son1 = {
           template:"<div class="son1"></div>"
       }
       
       new Vue({
           el:"#root",
           // 引入
           template:"<div><aaa></aaa></div>",
           components:{
               aaa:son1
           }
       })
   </script>
   
   // 注意：标准的data写法
   data(){
   return{
   	msg:"123",
   }
   }
   ```

## 8.生命周期

1. 概述：一个Vue对象从创建到销毁的过程

2. 详情：

   - beforeCreate

   - created

   - beforeMount

   - Mounted

   - beforeUpdate

   - Update

   - beforeDestroy

   - destroyed

     > 浏览器用：root.$destroy()

## 9. 组件传值

- **父组件传值操作**：

  ```html
  <aaa v-bind:username="user.name"></aaa>
  ```

- **子组件接收值**：props：是专门用来接收父组件传值的，可以当作data看待（只不过props里的数据来自父组件，语法要求不能修改）

  ```html
  preps:["username"]
  ```

- **子组件向父组件传值**：子组件抛出方法