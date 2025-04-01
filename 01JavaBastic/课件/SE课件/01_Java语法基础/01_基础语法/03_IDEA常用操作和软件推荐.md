###### <sub><font color = orange>JAVASE基础教程</font></sub><br />——<br /><sup><font color=white>卷1</font></sup><font color=white>Java基础语法</font><br/><sup><sub><font color=cyan>节3</font></sub><font color=cyan>IDEA常用操作和软件推荐</font></sup><br/><br/>	``#最新版本|V3.0#(purple) ``<br/>**长风**<br/>*COPYRIGHT ⓒ 2021. 王道版权所有*

[TOC]

# IDEA常用操作（重要）

> `>(green)`
>
> IDEA有很多操作是必须要知道的，这里先讲几个非常常用的。

## 导入和打开IDEA项目

> `>(green)`
>
> **对于已有的IDEA工程（例如老师每天传的代码）是可以直接用IDEA打开的，逐个点击其中的Java文件查看代码是很低效的！**
>
> 点击IDEA左上角，<span style=color:red;background:yellow>**File ---> Open ---> 选择目标工程文件**</span>，IDEA工程在选择打开时是有特殊图标的，如下图：
>
> <img src="https://hixiaodong123.oss-cn-hangzhou.aliyuncs.com/typora/202111051659217.png?align=center" alt="可以打开的idea工程" style="zoom:50%;" />
>
> <font color=red>**普通文件夹是没有这个黑色的小格子的，这是IDEA工程的标记！打开后，就可以正常查看Project中的代码，并且能够运行。**</font>
>
> 如果代码不能运行，可以找同学研究一下或者直接提问老师。

## 打开最近的Project

> `>(green)`
>
> 很多时候，我们需要打开之前已经打开过的Project，做法参考下面：
>
> 点击IDEA左上角，**File ---> Open Recent** ，即可选择打开最近的工程。如下图：
>
> <img src="https://hixiaodong123.oss-cn-hangzhou.aliyuncs.com/typora/202201221509299.png?align=center" alt="打开最近的Project" style="zoom:50%;" />
>
> 当然这个操作，只能打开你已经打开过的Project，如果想要打开还未打开的Project，请参考上一条。

## 文件的重命名和删除

> `>(green)`
>
> 如果有文件重命名和删除的需求，可以右键某个文件或者文件夹，参考以下图片，进行相关操作，也可以记下相关快捷键。
>
> <img src="https://hixiaodong123.oss-cn-hangzhou.aliyuncs.com/typora/202111091607826.png?align=center" alt="IDEA-删除和重命名" style="zoom:33%;" />
>
> 快捷键即重命名：**Shift + F6**  、删除：**Delete**

## 包路径折叠与打开

> `>(green)`
>
> 你可能还暂时不懂什么是`包`，那么现在你就**把包当成操作系统中的文件夹**就可以了。
>
> 在后面开发中，你肯定会经常见以下IDEA场景，如下图：
>
> <img src="https://hixiaodong123.oss-cn-hangzhou.aliyuncs.com/typora/202201221553782.png?align=center" alt="多级包目录" style="zoom:50%;" />
>
> 这就是一个具有**多级包目录**的Module。
>
> 实际开发中，这种**多级包目录**也是非常常见的，像上图中，是完全打开多级包目录，当然你也可以选择折叠其中的空包。
>
> 按照下图点击图中的螺丝图标：
>
> <img src="https://hixiaodong123.oss-cn-hangzhou.aliyuncs.com/typora/202201221601089.png?align=center" alt="折叠包目录按钮" style="zoom: 67%;" />
>
> 在打开的菜单中，点击：
>
> <img src="https://hixiaodong123.oss-cn-hangzhou.aliyuncs.com/typora/202201221602446.png?align=center" alt="折叠多级包目录" style="zoom:50%;" />
>
> 这样就可以折叠多级空包目录，效果如下：
>
> <img src="https://hixiaodong123.oss-cn-hangzhou.aliyuncs.com/typora/202201221603935.png?align=center" alt="折叠多级空包目录效果图" style="zoom:50%;" />
>
> **这个选项菜单中的其它按钮，你也可以试一试效果，总之多摸索摸索，熟悉了就好了。**

## 代码分屏

> `>(green)`
>
> 有时我们需要同时查看多个java文件，如果有扩展外屏固然是更好的，如果没有没有的话，就可以使用idea自带的分屏功能。
>
> 具体使用下来，可以任意找到一个打开的Java文件选项卡，右键单击，在弹出菜单中单击下列按钮即可实现分屏。
>
> <img src="https://hixiaodong123.oss-cn-hangzhou.aliyuncs.com/typora/202201221614443.png?align=center" alt="代码分屏按钮" style="zoom:50%;" />
>
> **IDEA支持代码文件，左右分屏和上下分屏。开发中，这也是一个不错的功能。**

# IDEA必要设置

> `>(green)`
>
> IDEA本身是一个非常棒的集成开发环境，为了提高IDEA的使用效率，有必要对默认设置做出一些修改
>
> - 可以积累自己的IDEA使用经验，积累自己的习惯，工具都是越使用越顺手
> - 这里给出一些我的使用习惯，仅作为作为<font color=red>**参考**</font>。
>
> 以下设置全部针对 **IDEA 2018.3**，首先仍然建议统一IDEA版本，使用其它版本的同学可能找不到设置的位置，请自行百度一下。

## 去掉代码提示的大小写限制

> `>(green)`
>
> 默认情况下，IDEA的**代码联想补全功能**会严格区分大小写。
>
> 例如：输入Sys，IDEA会联想出类System，相反输入sys则不行。
>
> 这显然是不方便的，代码联想补全实际上并不需要区分大小写。可以按照以下顺序操作取消：
>
> 点击IDEA 左上角的： **File**---->**Settings**----->**Editor** ----->**General**-----> **Code Completion**------>**Match Case**去掉勾。
>
> 如下图：
>
> <img src="https://hixiaodong123.oss-cn-hangzhou.aliyuncs.com/typora/202202241555389.png?align=center" alt="去掉代码提示的大小写限制" style="zoom: 35%;" />

## 单行注释相关设置

> `>(green)`
>
> 默认情况下，单行注释从每行代码的开头开始，而不是和代码缩进对齐，而且代码规范中还要求单行注释后面需要加一个空格。IDEA中都提供了类似设置完成这些功能，参考以下图片：
>
> 点击IDEA 左上角的： **File**---->**Settings** ---> 按照下图 **标号4** 一样进行设置勾选：
>
> <img src="https://hixiaodong123.oss-cn-hangzhou.aliyuncs.com/typora/IDEA设置-单行注释.png?align=center" alt="IDEA设置-单行注释" style="zoom: 33%;" />
>
> <font color=red>**注：这样设置单行注释是出于开发规范的要求，关于具体的开发规范，大家可以在后续学习中，一边学习一边积累经验。**</font>

## 修改IDEA内存大小

> `>(green)`
>
> IDEA默认分配的内存空间占用是比较小的，如果电脑的性能不错，可以考虑多给IDEA分配一些内存使用。实测后可以提升一些流畅度，也不会影响操作系统整体性能，如果电脑的内存大于 **8G** 建议修改一下。当然，不改也没多大影响，软件运行是否流畅，最终还要看电脑的CPU、硬盘速率等各种性能综合。
>
> 点击IDEA中上方的 **Help** -----> **Edit Custom VM Options** 打开IDEA的配置文件，如下图所示：
>
> <img src="https://hixiaodong123.oss-cn-hangzhou.aliyuncs.com/typora/202111091705736.png?align=center" alt="IDEA-全局参数配置" style="zoom:33%;" />
>
> **配置1**：设置IDEA启动时的初始可分配的最大内存大小，如果启动速度很慢可以适当增加该配置。
>
> **配置2**：设置IDEA最大可分配内存空间大小，如果需要打开多个IDEA窗口可以适当增加该配置。
>
> **配置3**：设置IDEA编译代码时最大代码缓存的大小，不能超过2GB大小。
>
> 总之，这三个配置在一定范围内越大越好，可以参考图片中的设置，也可以适当增加。

> `>(red)`
>
> **附打开IDEA内存统计：**
>
> > 打开IDEA左上角 **File ---> Setting ---> 按照下图设置勾选：**
>
> <img src="https://hixiaodong123.oss-cn-hangzhou.aliyuncs.com/typora/202111091758847.png?align=center" alt="IDEA-显示当前内存占用" style="zoom: 33%;" />
>
> 这样就可以在 **IDEA主窗口** 的右下角显示当前IDEA占用内存空间，这样也更方便随时根据需求增加IDEA最大内存空间。
>
> <img src="https://hixiaodong123.oss-cn-hangzhou.aliyuncs.com/typora/202111100929950.png?align=center" alt="IDEA-当前占有内存大小" style="zoom: 33%;" />
>
> **以上，关于IDEA占用内存相关的设置就完成了。**

## 设置编码格式为UTF-8

> `>(green)`
>
> 中文乱码问题一直是一个比较困扰的问题，究其原因大多是字符编码格式不匹配导致。这里建议将软件的编码格式都设置为UTF-8能避免绝大多数乱码问题。尤其是很多同学之前使用过eclipse，默认编码都是GBK（本地中文编码），转移代码的时候经常会中文乱码问题。
>
> 1. 打开IDEA左上角 File ---> Setting --->  **参考如下图全部设置为UTF-8**
>
> <img src="https://hixiaodong123.oss-cn-hangzhou.aliyuncs.com/typora/202111100957136.png?align=center" alt="IDEA-设置编码格式-1" style="zoom:33%;" />
>
> <font color=red>**注：**</font>
>
> 这里额外说一下，上图钟最后一行选项 **BOM for new UTF-8 files** ：首先你要知道**BOM** 这个概念，简单来说它是一种标记或者签名，当编辑器读取到这个签名时，就知道该文件是采用 **UTF-8格式** 编码的，当然这是Windows操作系统的做法，但其它操作系统平台（比如Mac、Linux等）是不支持的。**BOM** 这个签名虽然不直接显示在文本中，但仍然是有输出的。这就意味着：<font color=red>**带BOM的Java文件在Windows平台下是可以编译成功的，但是在 Linux等平台下带BOM文件就会编译失败。**</font>这严重破坏了Java语言的跨平台性，所以请不要创建带BOM的Java文件：<span style=color:red;background:yellow>**不要设置 Create UTF-8 Files with BOM！！请按照图片中进行设置！**</span>
>
> 2. 参考1中设置，将设置同步到 **File ---> Other Setting ---> Setting for New Projects** ，这样新建Project也会是UTF-8编码格式
> 3. 设置完以上两点后，基本上乱码问题就能够解决大部分了。如果仍然出现乱码问题，IDEA还有一些其它涉及到编码的设置，可以自行百度一下进行设置修改。

# IDEA设置杂项（了解）

> `>(red)`
>
> 这些杂项配置，你可能一时半会还难以理解它的作用。但这不重要，可以先看看，知道和了解一下，后续学习的时间还长，慢慢去领悟这些东西。
>
> 这些配置都不做具体要求，大家可以跟着配置一下，也可以什么都不做，看一看。

> `>(green)`
>
> 1. <font color=red>**设置换行符格式为类Unix系统格式. **</font>

> [-] 关于换行符
>
> `>(green)`
>
> > 空格，换行这些看起来是"空"的，什么都没有的东西，实际上都是一个字符，称之为`控制字符`。换行字符在不同操作系统中，显示的符号是不一样的，参考下表：
> >
> > ###### 不同操作系下的换行符
> >
> > | 操作系统 |       换行符       |
> > | :------: | :----------------: |
> > | Windows  | `\r\n`，回车加换行 |
> > | 类 Unix  |     `\n`，换行     |
> > |   Mac    |     `\n`，换行     |
> >
> > 注：
> >
> > 1. `\r` 表示回车，即 CR （carriage return）`\n `表示换行，即 LF （linefeed）
> > 2. Mac也是类Unix操作系统，之所以单列出来因为早期Mac上的换行符是`\r`（OS 9和之前），OS X之后统一为`\n`，当然你现在能见到和使用到的非古董Mac肯定都是Mac X系统的。
>
> <font color=red>**因为不同操作系统换行符的不同，在一个系统下编辑的文件放到另一个系统下时，可能会显示不正常。**</font>常见的有以下两种情况：
>
> 1. 在类 Unix 系统或 Mac 系统中编辑的文件，在 Windows 记事本中多行文字会变成一行**（有些智能编辑器会自动识别不同系统的换行符，并进行转换，如 Sublime、Notepad++等，所以这种情况不多见）。**
>
> 2. 在 Windows 记事本中编辑的文件，在其它系统中莫名其妙多显示一个控制字符 **^M**（在 Linux 中，回车符 `\r`会作为控制字符 **^M** 显示，这种情况十分常见）。
>
>    例如下图：
>
>    <img src="https://hixiaodong123.oss-cn-hangzhou.aliyuncs.com/typora/202201221640695.png?align=center" alt="Linux下的换行符" style="zoom: 67%;" />
>
> ---
>
> 总之为了避免这种因为换行符导致的问题，最常见的做法还是统一换行符格式，IDEA提供了相关的设置。需要说明的是，由于Java代码最终几乎一定会在Linux服务器上执行，所以换行符应该统一为 **类 Unix** 操作系统的换行符。
>
> 打开IDEA左上角 **File ---> Setting ---> 按照下图设置：**
>
> <img src="https://hixiaodong123.oss-cn-hangzhou.aliyuncs.com/typora/202111101101518.png?align=center" alt="IDEA-设置换行符格式" style="zoom:33%;" />
>
> 实际上，在很多公司的代码规范（比如阿里）中，也都强制要求程序员要么直接用类Unix系统开发代码，要么使用Windows开发必须设置换行符格式为类Unix格式。

> `>(green)`
>
> 2. <font color=red>**设置 Tab 制表符为4个空格. **</font>

> `>(green)`
>
> 和换行符一样，Tab键的使用效果虽然看起来像加了很多空格，但它实际上也是一个控制字符。制表符 Tab 有一个比较大的问题就是，在不同操作系统或者不同编辑器下，可能会出现长度不一的情况。这会影响代码的美观度，也有可能引入一些bug。为了避免这种问题，建议将 Tab 制表符直接设置为4个空格的间隔。
>
> **IDEA在默认情况下，Tab键就是4个空格，**设置参考下图：
>
> <img src="https://hixiaodong123.oss-cn-hangzhou.aliyuncs.com/typora/202111101121732.png?align=center" alt="IDEA-设置Tab为4个空格" style="zoom:33%;" />
>
> 关于制表符Tab相关的一些问题，可以参考：[Intellij IDEA如何设置tab键为4个空格-百度经验 (baidu.com)](https://jingyan.baidu.com/article/148a1921cbaaf04d71c3b1ee.html)

# IDEA插件（了解）

> `>(green)`
>
> IDEA是可以装插件的，尤其随着学习进度的推进，有些插件是必须的。这里就不给大家推荐暂时用不到的，或者花里胡哨的一些插件。仅推荐一个插件：**Translation，即翻译插件。**
>
> 打开IDEA左上角 File ---> Setting ---> Plugins ---> 在 **Marketplace** 中下搜索：
>
> 1. <font color=red>**Translation，翻译插件，安装重启IDEA后，可以在IDEA中使用快捷键 CTRL+SHIFT+Y 选词翻译！**</font>效果大致如下图：
>
>    <img src="https://hixiaodong123.oss-cn-hangzhou.aliyuncs.com/typora/202202241601151.png?align=center" alt="Translation插件" style="zoom:50%;" />
>
> 2. 其次，两个不推荐装的插件：
>
>    1. <span style=color:red;background:yellow>**任何中文化插件，都禁止使用。**</span>主要是因为：
>
>       - 英语是程序员的基本素质，不至于说IDEA的一点点英文都不认识，使用中文插件可能会让人觉得你水平不高。
>       - 并不是任何内容都是能够准确中文翻译，所以看英语原文会更好。
>       - 网络上大家交流的都是英文版本，使用中文翻译版不仅是异类，也不容易找到教程。
>
>    2. <span style=color:red;background:yellow>**阿里巴巴规范插件，不推荐使用。**</span>对于初学者而言，连语法和代码结构最基本的东西都还没搞明白，还要去遵守各种规范，未免有点**"不会走，就想跑"**了。安装这个插件后，会让代码"红一块，黄一块"非常难看。
>
>       <font color=red>**建议等积累一定代码量，比较熟悉Java后再使用，或者工作后根据公司的安排选择使用。**</font>
>
>

> `>(red)`
>
> 最后，花里胡哨的尽头都是返璞归真，如无必要不要给IDEA装过多插件。有时候插件会拖慢IDEA运行速度，严重的还可能会产生bug，甚至IDEA直接打不开都是有可能的。

# IDEA快捷键（重要）

> [-] 关于快捷键的说明
>
> `>(green)`
>
> > 软件用的熟不熟，快不快，很多时候都要看快捷键的使用，IDEA也不例外。我们作为Java开发者而言，IDEA的常用快捷键是需要知道且熟练使用的。在使用快捷键时，比较常见的问题就是：计算机的全局快捷键和IDEA快捷键冲突，这个时候IDEA快捷键会不起作用。以我的经验来看，以下软件的全局快捷键经常和IDEA冲突：
> >
> > 1. QQ音乐，建议在设置中直接关闭全局快捷键（听个歌要啥快捷键）
> > 2. 网易云音乐（同上）
> > 3. QQ
> > 4. 百度网盘
> > 5. Intel集显驱动（比较冷门，但是碰到过）
> > 6. ...
> >
> > 如果碰到在IDEA使用某个快捷键不生效，就需要立刻考虑是否是某个软件的全局快捷键冲突了，并且 <span style=color:red;background:yellow>**这个时候肯定要以"吃饭的家伙"IDEA为大。**</span>**（坦白来说，全局快捷键这个东西很坑，任何软件如无绝对必要都建议屏蔽它的全局快捷键！）**
>
> 了解快捷键的基础情况后，来看一下IDEA中最常用的一些快捷键：
>
> ###### IDEA的常用快捷键介绍
>
> | 快捷键                | 效果介绍                                                     |
> | --------------------- | ------------------------------------------------------------ |
> | **Alt + Enter**       | 快速修复光标位置的错误，光标放在的位置不同提示的结果也不同 `（必备）` |
> | **Ctrl + Alt + L**    | 格式化代码，强迫症必备，建议每写几行代码下意识的按一下该快捷键`（必备）` |
> | **Ctrl + Y**          | 删除光标所在行 或 删除选中的行 `（必备）`                    |
> | **Ctrl + D**          | 复制光标所在行 或 复制选择内容，并把复制内容插入光标位置下面`（必备）` |
> | Ctrl + W              | 递进式选择代码块，会逐步选择某行代码、结构体，方法直至整个类 |
> | Ctrl + O              | 选择可重写的方法                                             |
> | **Ctrl + /**          | 注释光标所在行代码，会根据当前不同文件类型使用不同的注释符号 `（必备）` |
> | **Ctrl + Shift + /**  | 多行注释`（必备）`                                           |
> | **Alt + Insert**      | 代码自动生成，比如构造方法，get/set方法等等`（必备）`        |
> | Ctrl + Alt + 左方向键 | 回到上一个光标所在位置 `（Debug模式必备）`                   |
> | Ctrl + Alt + 右方向键 | 前进到上一个光标的位置 `（Debug模式必备）`                   |
> | Ctrl + Shift + Z      | 取消撤销 `（必备）`                                          |
> | Ctrl + Alt + V        | 快速补全变量                                                 |
> | F2                    | 跳转到下一个高亮错误 或 警告位置 `（必备）`                  |
> | ...                   | ...                                                          |
>
> 以上都是**默认情况下，Windows操作系统下**，IDEA的常用快捷键。如果是mac用户，或者有其它快捷键需求，请自行查找。在以后的学习工作中，这些快捷键都有被使用的机会，尤其加黑的几个是特别常用的。
>
> 最后，提供一个我个人经常修改的快捷键：main方法在Java中作为入口方法，是经常被启动的，但默认的main方法启动快捷键比较复杂的，建议将它修改成更方便的快捷键。这里推荐 <font color=red>**F1**</font> 。按照以下操作执行：
>
> 打开IDEA左上角 **File ---> Setting ---> Keymap ---> 搜索框中搜索 Run context configuration** ---> 参考图片设置
>
> <img src="https://hixiaodong123.oss-cn-hangzhou.aliyuncs.com/typora/202110152154162.png?align=center" alt="IDEA-main方法启动快捷键设置" style="zoom: 33%;" />
>
> 当然改不改随你，理论上来说用鼠标点Run也没慢多少。

# 附：常用软件分享

> `>(green)`
>
> 鉴于很多同学经常会提问："老师，你用的xxx是什么软件？"之类的问题。所以这里直接给大家分享出来，多数软件都是跨平台的，有些则是Windows平台专属的。
>
> 注：链接失效可以联系老师获取。
>
> 「 **好马配好鞍，武器要趁手。分享一些软件~**」

## "最优秀的截图软件"

> [-] 关于Snipaste
>
> `>(green)`
>
> > **Snipaste** 取自 "snip" 和 "paste" 两个单词的组合，即该软件的核心功能是 "截图" 和 "贴图"。  不解释了，非常好用的一个软件，Windows和Mac平台皆有，并且提供了收费专业版（免费版已经够用了）。使用这个软件后建议屏蔽掉微信和QQ的截图全局快捷键（因为它们太垃圾了）
>
> 下载方式：
>
> 1. 直接官网下载，根据操作系统选择版本下载后安装
>
>    - https://zh.snipaste.com/ 或者 https://www.snipaste.com/
> 2. 百度云下载（没有mac版本，推荐直接去官网下载）                                                                                                                                   
>
> > 百度网盘：
> >
> > 链接：https://pan.baidu.com/s/12Sn7H7g043ABPfFgl-annw 
> > 提取码：565r 
>
> 使用效果：
>
> 非常常见的场景是，在做作业的时候将题目贴在IDEA边上或者将查的资料贴在边上，如下：
>
> <img src="https://hixiaodong123.oss-cn-hangzhou.aliyuncs.com/typora/202111111112826.png?align=center" alt="Snipaste软件使用效果" style="zoom:33%;" />
>
> **当然，等大家成为真正的开发后，谁还没几个扩展的显示器呢？**到那时候，贴图功能一般就不太常用了。

## Wox + Everything 

> `>(green)`
>
> 使用 **Wox + Everything** 可以实现 **本地文件快速全局检索** 功能，配合 **Wox** 的其它插件还能实现诸如翻译，百度搜索，dos指令等功能（自己探索一下，不赘述）。呼出 **Wox** 命令窗口的快捷键默认是 **Alt + 空格**。 Mac本身就自带类似的聚点搜索（Spotlight）功能，所以这一套组合目前仅针对Winodws平台。
>
> 下载方式：
>
> 1. 官网下载，推荐下载安装包后安装，即使用安装版，尽量不要用便携版。
>
>    - Everything：https://www.voidtools.com/zh-cn/
>    - Wox：https://github.com/Wox-launcher/Wox/releases
>
>    注：Wox的下载在Github，可能需要科学上网。如果连不上可以使用后面百度云下载~
>
> 2. 百度云下载（可能不是最新版，需要更新）                                                                                                                                                                                                                    
>
> > 百度网盘：
> >
> > 链接：https://pan.baidu.com/s/12Sn7H7g043ABPfFgl-annw 
> > 提取码：565r 
>
> 使用效果：
>
> <img src="https://hixiaodong123.oss-cn-hangzhou.aliyuncs.com/typora/202111111158229.png?align=center" alt="Wox+everything使用效果" style="zoom:33%;" />
>
> <font color=red>**这套组合真的很好用，强烈建议Windows同学装一下。**</font>

## 其它

> `>(green)`
>
> 1. 翻译兼顾单词本的软件：**有道词典**，也可以用网页版谷歌翻译或者百度翻译替代（但是没有单词本功能）
> 2. 录制屏幕软件：**Bandicam** （我看B站很多up主也用这个）
>
> > 下载链接百度云盘自取：
> >
> > 链接：https://pan.baidu.com/s/1Vq563s5FoSOsagfNcBpY-A 
> >
> > 提取码：273o 
>
> 3. 安全软件：**火绒**，可作为各种管家、杀毒的替代品。可以用来拦截弹窗。
> 4. 下载软件：**Internet Download Manager**，简称 **IDM**，相当好用的下载器。
>
> > `>(green)`
> > 下载链接百度云盘自取：
> >
> > 链接：https://pan.baidu.com/s/1eAxOxPDAZF6fryK9ALNxNA 
> >
> > 提取码：ih2g 
>

###### The End
