## school

##git命令
准备工作
1.注册github账号
2.下载安装git客户端
3.在github上创建一个仓库（项目）

正文
Ⅰ.打开bash
在项目文件所在目录，选中当前项目目录，右键 点击【Git Bash Here】进入git命令行

Ⅱ.命令行指令
1.git config --global user.name “username”	#输入用户名
2.git config --global user.email "youremail@mail.com"	#输入注册邮箱
3.git init #初始化
4.git add .
5.git commit -m “initial commit”
6.git remote add origin git@github.com:username/project.git	#项目的SSH地址：git@github.com:username/project.git
7.git push origin master
8.如果commit错了，想要重新commit,就用 git commit --amend --no-edit

##资料
[github OAuth](https://developer.github.com/apps/building-oauth-apps/creating-an-oauth-app/)