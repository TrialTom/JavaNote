# git使用指南

1. ## 本地仓库删除文件，同步到远程仓库

   - `git rm -r --cached filename`  :删除仓库文件filename
   - ` git commit -m delete ` ；提交到远程仓库
   - `git push`；更新远程仓库

2. ## 如果需要通过 `.gitignore` 文件来屏蔽文件，如 `.idea` ，

   ~~~git
   touch .gitignore              创建.gitignore文件，然后在文件中输入需要屏蔽的文件或文件夹
   
   git rm -r --cached .idea		删除远程仓库中的文件
   
   git add .					将.gitignore文件加入暂存区，注意不要使用git add *
   
   git commit -m 'change'		提交到本地仓库
   
   git push origin master		提交到远程仓库
   
   ~~~

3. ## 更新本地仓库

   ```git
   // 将最新的更改下载到本地
   git fetch
   
   // 将本地代码和最新代码合并到一起
   git merge
   
   // 撤销更改并还原到先前的版本
   git reset
   ```

4. ## 全局配置

   ```GIT
   git config --global user.email "you@example.com"
   git config --global user.name "Your Name"
   ```

   
