[TOC]



# 1. 概述

文件上传至服务器上需要借助HTTP请求报文，请求体，伴随着HTTP请求报文传输到服务器，会将HTTP请求报文解析封装到request对象。与开发者而言，借助request.getInputStream()来获取请求体部分数据，将文件存储到本地磁盘。

# 2. 实例

```html
<!-- enctype="multipart/form-data"加上可以上传二进制类型文件，否则只能上传文本文件 -->
<form action="" enctype="multipart/form-data" method="post">
    <input type="file"name="image">
    <input type="submit">
</form>
```

```java
// 获取请求体
ServletInputStream inputStream = request.getInputStream();
// 需要将数据保存到硬盘, 保存到应用更目录下impage目录中
ServletContext servletContext = getServletContext();
String realPath = servletContext.getRealPath("image/1.jpeg");
File file = new File(realPath);
if(!file.getParentFile().exists()){
    // 父级目录不存在
    file.getParentFile().mkdirs();
}
FilOutpStream out = new FileOutputStream(file);
int length = 0;
byte[] bytes = new byte[1024];
while((length = inputStream.read(bytes)) != -1){
    out.write(bytes, 0, length);
}
out.close();
inputStream.close();
```

**2.2 注意**：

1. 表单数据也同样进入到了文件中

2. FormBoundary作用就是用来进行分割不同的表单项的，

   > 在没有引入文件上传之前，，如果数据是key-value类型，那么分隔符是&
   >
   > 引入文件上传之后，那么肯定是不能使用使用key-value类型来提交文件数据。此时的分隔符也由&变成了WebKitFormBoundaryBhBklRidBoGJpLB，如果这些分割字符没有提交，直接传入二进制文件中，就会导致文件损坏。

**2.3 改进**：

```java
// Check that we have a file upload request
boolean isMultipart = ServletFileUpload.isMultipartContent(request);

// Create a factory for disk-based file items
DiskFileItemFactory factory = new DiskFileItemFactory();

// Configure a repository (to ensure a secure temp location is used)
// 如果上传文件较大，会利用一个临时目录来充当缓存的位置，采用边缓存边上传的方式
ServletContext servletContext = this.getServletConfig().getServletContext();
// 每个应用被加载到tomcat服务器之后，tomcat会给每个应用设置一个临时目录，并且把该目录的位置放入每个应用对应的context域中
File repository = (File) servletContext.getAttribute("jakarta.servlet.context.tempdir"); // Or "javax.servlet.context.tempdir" for javax
factory.setRepository(repository);

// Create a new file upload handler
// 获取处理文件上传业务逻辑的处理器
ServletFileUpload upload = new ServletFileUpload(factory);
JakartaServletDiskFileUpload upload = new JakartaServletDiskFileUpload(factory);

// Parse the request
// 每当有一个iput提交，那么就会对应一个FileItem
List<DiskFileItem> items = upload.parseRequest(request);
for(FileItem item : items){
    // 判断上传的是表单数据还是上传的文件
    if(item.isFormField()){
        processFormField(item);
    }else{
        processUploadFile(item);
    }
}
// 该方法用来处理表单的业务逻辑
processFormField(){
    String name = item.getFieldName();
    String value = item.getString();
}
// 该方法用于处理文件上传的业务逻辑
processUploadFile(){
    // 属性名
    String fieldName = item.getFieldName();
    String fileName = item.getName();
    String contentType = item.getContentType();
    boolean isInMemory = item.isInMemory();
    long sizeInBytes = item.getSize();
    // 将文件保存应用根目录下
    item.write(file);
}
```

# 3 中文乱码问题

```java
// 解决文件名中文乱码问题，但无法解决表单中文乱码
request.setCharacterEncoding("utf-8");

// 手动输入表单的编码格式
String value = item.getString("utf-8");
```

# 4 设置

**4.1 设置上传文件的大小**：

```java
ServletFileUpload upload = new ServletFileUpload(factory);
// 单位：byte
upload.setFileSizeMax(1024);
```

# 5. 封装数据到JavaBean

**5.1 具体要求**：

- 文件上传保存到user对象中，此时保存的应该是相对硬盘路径。

**5.2 代码实例**：

```java
// map里面装填了数据
Map<String,String> params = new Map();

User user = new User();
BeanUtils.populate(user, params);
```

# 6. 文件重名问题

- 完全随机名字

  ```java
  String fileName = item.getName();
  // 改名
  String uuid = UUID.randomUUTI().toString();
  fileName = uuid + "-" + filename;
  ```

  

# 7. 文件夹中文件数过多

- 散列的思想实现分发

  ```java
  int hashCode = fileName.hashCode();
  // 转换为二进制字符串
  String hexString = Integer.toHexString(hashCode);
  char[] chars = hexString.toCharrArray();
  String besePath = "image"
  for(char aChar : chars){
      basePath = basePath + "/" + achar;
  }
  // 保存文件的目录
  String realPaht = basePaht + "/" + fileName;
  ```

  