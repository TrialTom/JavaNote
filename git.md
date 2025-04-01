```shell
git init//使该目录变成git管理的仓库
    
git add filename//添加文件到暂存区
git commit -m "注释"//将暂存区的文件提交到本地仓库中
    
git status//查看仓库状态
git log//查看提交的版本记录
git reflog//查看对仓库的操作日志
    
git diff [HEAD/HEAD^^... /  版本号]//查看当前工作区的代码与最后一次版本（或者前几个版本）的比较，仅git diff默认为最后一次提交的版本
git checkout filename//把当前版本库中最新的代码覆盖当前的，放弃对工作区代码的修改，可以回退版本再覆盖
git reset --hard HEAD^//回退版本，多个^代表回退几个版本   
git reset --hard 版本号//这里也可以是版本号，用git log/reflog可以查看
git reset HEAD filename//从暂存区撤销
git rm filename 后git commit -m //先删除再把当前版本提交，就从版本库中删了，相当于提交了一份没有该文件的版本

git branch//查看分支
git branch 分支名//创建分支
git checkout 分支名//切换分支
git checkout -b 分支名//创建并切换到该分支
git branch -d 分支名//删除分支,不能删除当前所处分支，切换到其它分支再删除
git merge 分支名//合并某个分支到当前分支，合并时可能产生冲突，需要解决冲突，自己vim改代码就行了
git stash//保护现场，当前工作区有代码修改了，是不能切换到其他分支，可以先保存现场，再切换
git stash list//列出所有保存的现场信息 

ssh-keygen -t rsa -C "su@126.com"//生成通信密钥,生成的公钥在/home/stu/.ssh/下
    
//测试与github或者gitee(码云)有没有连通：
ssh -T git@github.com//测试github 的命令
ssh -T git@gitee.com//测试gitee 也就是码云的命令 
    
git clone 项目地址//克隆项目
//首先得把自己的仓库有东西，然后再拉取服务器最新的更新到本地，不然会提交失败
git pull origin 分支名//拉取远程服务器上的分支更新到本地，分支名不写默认master
git push -u origin 分支名//提交分支到远程仓库,并跟踪分支
git push origin 分支名//提交分支到远程仓库，如果改同一个文件，提交后，然后git不知道用哪个，都用，还是都不用，还是分别用哪个，所以提交到仓库中，别人看的时候，需要解决冲突，自己改代码


ssh -T git@github.com  //可以查看有没有联通
    
//然后就git clone 自己GitHub上的SSH，然后就正常输命令了
//git pull origin master
//git add filename
//git status     git log      git reflog
//git commit -m ""
//git push origin master

```

# 问题

## 443连接超时问题

1. VPN环境下，代理的端口不正确

   ```shell
   // 配置git，使用代理
   git config --global http.proxy http://127.0.0:1234
   git config --global https.proxy http://127.0.0:1234
   
   // 验证端口是否生效
   git config --global -l
   
   // 刷新DNS缓存（极其重要）
   ipconfig /flushdns
   ```

2. 非VPN环境下

   ```shell
   // 取消代理
   git config --global --unset http.proxy
   git config --global --unset https.proxy
   
   // 验证
   git config --global -l
   ```

   

