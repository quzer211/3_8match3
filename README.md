# 3_8match3(宝石迷阵)
1.项目介绍
--------

用以致敬经典的Java小程序“宝石迷阵”，当然，也可称为拾人牙慧之作。
实现了《宝石迷阵》的基本游戏功能，还有许多待优化之处。


2.使用方法
--------

将代码导入eclipse选取Main.class直接运行即可。
由于代码在eclipse环境中编写，尚未测试在其他IDE中的运行情况，估计没有太大问题。


3.游戏说明
--------

鼠标点选宝石，被选取的宝石会按鼠标运动方向（上下左右）与其上下左右位置的宝石交换。
交换后若至少有三个同类宝石处在同一条线便会消除，获得分数并增加时间。
当达到一定分数会进行升级，时间流逝变快，超过八级便会弹窗“God Like”判定胜利。


4.文件结构
--------

node mddir "../relative/path/"

5.类的简单介绍
--------

Grid：由于游戏是在平面上进行的，故我们创建了一个包含二维数组成员变量的类，用于
定义游戏所需矩形数量。
Win：游戏主要的图形界面，主要作用是按需求调用图片并显示在窗口中。
Winwin：当超过一定等级后弹出的祝贺窗口。
MyLabel：由grid的值调用宝石的图片，并且addMouseListener。
TollThread：计时器，同时规定了图像化计时条的画法。
Controller：这是最重要的类，它包含图形交换的实现，消除的判定，点击的判定，以及下落效果的实现。
Main：创建Controller对象并调用方法。

6.可能的问题
---------

如果汉字出现乱码，请尝试在UTF-8与简体中文之间转换。

## 最后，欢迎大家在此基础上拓展功能，进行进一步开发。

