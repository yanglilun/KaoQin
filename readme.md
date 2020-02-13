[toc]

---

## 预览网站：

 http://by-shadow.cn/kaoqin/ 

# 学生考勤管理系统

## **1.1** **项目开发背景**

辅导员期末制作专业迟到总表，需要花费大量的时间来统计每周每个班每个人的迟到时长，又要按要求制作表格，费事费力， 

 Q:为什么不直接用Excel自带的数据统计？

A:在手上的只有厚厚的一扎A4纸，没有数据何来的统计？所以主要就在于如何把纸质文件快速的录入到计算机中，最好还带统计功能。 

大量的纸质文件，一个专业6个班，每个班都有17周的迟到记录文件，总共102张A4纸。以往都是两个人合作，一个人统计本周 某学生 迟到多少次，另一个人录入Excel。

费时费力！

统计慢：人工计数+数据量大，数完一个学生还要回头数第二个学生迟到次数，统计完这个班的学生之后，本周还有另外五个班要统计。统计完另外五个班以后，还有16周等着你统计！

录入慢：把数据输入到电脑，对于打字快的学生来说没问题，但是问题在于要找位置，插数据，用上快捷键也需要半天。

为了解决迟到旷到不便记录和需要大量时间统计录入和制表的问题，现在基于BS架构设计以专业为中心.辅导员为主要参与人员，班委为辅助人员，跨专业为通用化标准

开发一款学生考勤管理软件。

## **1.2** **开发目标**

### 1.目标陈述：

​    对于老师来说，本款软件是一个基于Internet的移动工具，该程序为广大辅导员提供便捷的考勤管理功能，通过给班委发布任务即可解决大部分的平日繁琐的考勤管理任务。

​    整个项目强调分类以及通用性：

班委只能对管辖班级学生进行管理，录入考勤，查询信息。

教师只能对管辖专业的班级，学生进行管理，录入考勤，以及查询信息，

严格按照学校的院，专业，班级、进行分类。

### 2.主要功能特性

​    2.1-辅导员可以自行录入，也可以设置某个学生权限，让学生辅助老师录入每日（某日）的考勤信息。

​    2.2-录入时只需输入学生所在班级中的序号即可（学号末尾两位），方便快捷录入

​    2.3-申请补签，上传凭证，教师审批补签，自动操作考勤

​    2.4查看，本专业，本班，某周，某日的考勤表

​    2.5直接生成学院所需考勤材料

## 设计原则

运行环境：移动端

平台开发：eclipse

数据库：MYSQL

过程记录：teambition在线项目管理平台

 

## **需求分析**

### **2.1****需求陈述**

应用主要包括两大界面：

1-  班委界面

2-  老师界面

主要功能：

1-  录入信息

班委或者老师选择相应的周次和星期，输入当天未到学生的编号即可录入信息

2-  补签

班委选择相应的周次和星期，输入补签学生的编号以及补签原因，上传凭据，即可申请补签。并由老师进行审批

3-  查看信息

班委可以查看本班考勤信息，看可以查看负责专业的所有考勤信息

### **2.2** **操作用例**

**班委：**

提交申请：填写信息，申请补签

录入信息：选择班级，选择周次，录入信息

查看记录：查看当前出勤表信息

申请补签：提交补签申请，上传凭证

**教师：**

教师登陆：教师登录，记住密码，找回密码

管理班级：添加，删除，修改，导入，学生信息

审批补签：查看申请，审批申请

查询信息：查看课程签到情况。查看某人信息。

期末总表制作：制作期末出勤总表小工具

 

## 总体设计与实现

**3.1** **系统框架**

本系统采用B/S 浏览器/服务器  模式

B/S架构即浏览器和服务器架构模式，是随着Internet技术的兴起，对C/S架构的一种变化或者改进的架构。在这种架构下，用户工作界面是通过WWW浏览器来实现，极少部分事务逻辑在前端（Browser）实现，但是主要事务逻辑在服务器端(Server)实现