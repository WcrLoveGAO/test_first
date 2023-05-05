package com.msb.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

//GamePanel继承了JPanel以后，才具备了面板的功能，才成为一个面板
public class GamePanel extends JPanel {

    //paintComponent这个方法比较特殊，这个方法就属于图形版的main方法
   // 自动调用
    @Override
    protected void printComponent(Graphics g) {
        super.printComponent(g);
        //填充背景颜色
        this.setBackground(new Color(156, 13, 213));

    }

    //定义两个数组
    //蛇的长度
    int length;
    //一个数组，专门存储蛇的x轴坐标
    int[] snakeX = new int[200];
    //一个数组，专门存储蛇的y轴坐标
    int[] snakeY = new int[200];
    //游戏只有两个状态，开始，暂停
    boolean isStart = false;//默认游戏是暂停效果
    //加入一个定时器
    Timer timer;
    //定义蛇的行走方向
    String direction;
    //定义食物的x,y轴坐标
    int foodX;
    int foodY;
    //定义一个积分
    int score;
    //加入一个变量，判断小蛇的死亡状态
    boolean isDie=false;//默认情况下小蛇没有死亡
    public void init(){
        //初始化蛇的长度
        length = 3;
        //初始化蛇头坐标
        snakeX[0] = 175;
        snakeY[0] = 275;
        //初始化第一节身子坐标
        snakeX[1] = 150;
        snakeY[1] = 275;
        //初始化第二节身子坐标
        snakeX[2] = 125;
        snakeY[2] = 275;
        //初始化蛇头的方向
        direction = "R";
        //初始化食物的坐标
        foodX=300;
        foodY=200;

    }
    public GamePanel(){

        init();
        //将焦点定位在当前操作的面板上
        this.setFocusable(true);
        //加入监听
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {//监听键盘按键的按下操作
                super.keyPressed(e);
                int keyCode = e.getKeyCode();
                System.out.println(keyCode);
                if(keyCode == KeyEvent.VK_SPACE){
                 if(isDie) {
                     //全部恢复到初始化状态
                     score = 0;
                     init();
                     isDie=false;
                 }else{//小蛇没有死亡的情况下
                     isStart=!isStart;
                     repaint();//重绘动作
                 }
                }
                //监听向上箭头
                if(keyCode == KeyEvent.VK_UP){
                    direction = "U";
                }
                //监听向下箭头
                 if(keyCode == KeyEvent.VK_DOWN){
                     direction = "D";
                 }
                //监听向左箭头
                if(keyCode == KeyEvent.VK_LEFT){
                    direction = "L";
                }
                //监听向右箭头
                if(keyCode == KeyEvent.VK_RIGHT){
                    direction = "R";
                }
            }
        });
//对计时器进行初始化操作
        timer = new Timer(100, new ActionListener() {
            //ActionListener是事件监听
            //相当于每100ms监听一下你是否发生了一个动作
            //具体的动作放入actionPerformed
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isStart&&isDie==false) { //游戏是开始状态的时候，蛇才动
                    //后一节身子走到前一节身子的位置上
                    for (int i=length-1;i>0;i--) {
                        snakeX[i]=snakeX[i-1];
                        snakeY[i]=snakeY[i-1];
                    }
                    //动头
                    if("R".equals(direction)){
                    snakeX[0]+=25;
                    }
                    if("L".equals(direction)){
                        snakeX[0]-=25;
                    }
                    if("U".equals(direction)){
                        snakeY[0]-=25;
                    }
                    if("D".equals(direction)){
                        snakeY[0]+=25;
                    }
                    //防止蛇超出边界
                    if(snakeX[0]>750){
                        snakeX[0]=25;
                    }
                    if(snakeX[0]<25){
                        snakeX[0]=750;
                    }
                    if(snakeY[0]<100){
                        snakeY[0]=725;
                    }
                    if(snakeY[0]>725){
                        snakeY[0]=100;
                    }
                    //检测碰撞的动作
                    //食物的坐标和蛇头的坐标一样的时候，才是碰撞了
                    if(snakeX[0]==foodX&&snakeY[0]==foodY){
                        //蛇长度+1
                        length++;
                        //食物坐标改变，随机生成坐标   细节：坐标必须是25的倍数
                        foodX=((int)(Math.random()*30)+1)*25;
                        foodY=(new Random().nextInt(26)+4)*25;
                        //吃上食物以后，积分加十分
                       score+=10;
                    }
                    //死亡判定
                    for(int i=1;i<length;i++){
                        if(snakeX[0]==snakeX[i]&&snakeY[0]==snakeY[i]){
                            //将死亡状态改成true

                            isDie=true;


                        }

                    }
                    repaint();//重绘
                }
            }
        });
        //定时器必须要启动
        timer.start();
    }
//paintComponent这个方法比较特殊，这个方法就属于图形版的main方法
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //填充背景颜色
        this.setBackground(new Color(243, 8, 90, 245));
        //画头部图片
        //paintIcon四个参数：this指的是当前面板  g：指的是使用的画笔   x，y对应的坐标
        Images.headerImg.paintIcon(this,g,80,20);
        //调画笔颜色
        g.setColor(new Color(176, 16, 194));
        //画一个矩形
        g.fillRect(10,70,770,685);


        //画小蛇
        //画蛇头
        if ("R".equals(direction)){
            Images.rightImg.paintIcon(this,g,snakeX[0],snakeY[0] );
        }
        if ("L".equals(direction)){
            Images.leftImg.paintIcon(this,g,snakeX[0],snakeY[0] );
        }
        if ("U".equals(direction)){
            Images.upImg.paintIcon(this,g,snakeX[0],snakeY[0] );
        }
        if ("D".equals(direction)){
            Images.downImg.paintIcon(this,g,snakeX[0],snakeY[0] );
        }

//        //画第一节身子
//        Images.bodyImg.paintIcon(this,g,snakeX[1],snakeY[1] );
//        //画第二节身子
//        Images.bodyImg.paintIcon(this,g,snakeX[2],snakeY[2] );
        //优化为循环画蛇的身子
        for (int i = 1; i < length ; i++) {
            Images.bodyImg.paintIcon(this,g,snakeX[i],snakeY[i] );
        }

        //如果游戏是暂停的，界面中间就应该有一句提示语
        if (isStart == false) {
        //画一个文字

            g.setColor(new Color(74, 12, 99));
            //三个参数
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            //画文字  三个参数  文字内容  x轴坐标  y轴坐标
            g.drawString("点击空格开始游戏",250,330);
        }

        //画食物
        Images.foodImg.paintIcon(this,g,foodX,foodY);
        //画积分
        g.setColor(new Color(19, 206, 45));

        g.setFont(new Font("微软雅黑",Font.BOLD,20));

        g.drawString("积分"+score,620,40);


        //画入死亡状态
        if(isDie){
            g.setColor(new Color(255, 82, 68));

            g.setFont(new Font("微软雅黑",Font.BOLD,20));

            g.drawString("小蛇死亡，游戏停止，按下空格重新开始游戏 积分为"+score,200,330);

        }
    }


    }



