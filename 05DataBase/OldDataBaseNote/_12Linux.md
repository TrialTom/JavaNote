# 一、简介

- Linux内核最初只是由芬兰人林纳斯·托瓦兹在赫尔辛基大学上学时出于个人爱好而编写的。
- 目前市面上比较知名的发行版有：Ubuntu、CentOS、RedHat

# 二、安装

## 1. 安装虚拟机

**1.1 工具**：VMWARE + Ubuntu18.04

## 2. 购买云服务

[腾讯云](https://cloud.tencent.com) 

# 三、命令操作

## 0. Linux文件结构

![文件](E:\javaEE program\05DataBase\_05DataBase笔记\文件.png)

> - **文件解释**：
>   - etc：存放配置文件
>   - home：用户的家目录
>   - root：超级管理员可以使用的命令
>   - usr：用于用户安装软件
>   - var：存放临时文件
> - **Linux中，一切皆文件**：
>   - 普通文件：图片、视频、文本
>   - 目录文件：目标文件就相当于windows中的文件夹
>   - 链接文件：好比windows中的快捷方式
>   - 设备文件：都存放在/dev的路径下，里面放的都是和设备交互的文件
>   - 管道文件：

## 1. 高频实用命令

- cd：进入指定路径
- pwd：显示当前所在目录
- ls：显示目录
  - -a：显示所有文件
  - -l：显示文件的详细信息

## 2. 目录文件

```shell
# 创建一个新文件夹
mkdir dirName

# 删除一个空的文件夹
rmdir dirName

# 复制文件或者文件夹
cp fileName newFileName
# -r:递归复制
# -f:强制复制

# 删除文件或者目录
rm fileName
# -rf:强制递归删除

# 移动(可写相对路径也可以写绝对路径)
mv source destination

# 查看各个命令的说明文档
ma···
```

## 3. 普通文件

**3.1 文件创建**：

```shell
# 创建文件(可创建任意类型文件)
touch a.text
```

**3.2 文件查看**：

- cat：直接显示文件内容，适合文件内容比较少的情况
- tail：

# 四、远程软件安装

# 五、Java环境搭建