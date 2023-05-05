package com.msb.game;

import javax.swing.*;
import java.net.URL;

//Images这个类，专门用来获取游戏中所涉及的图片
public class Images {
    //因为是面向对象的思维和语言，要将图片进行封装，封装为一个对象，这样在程序中才可以通过操纵这个对象来操纵图片
    //讲图片的路径封装为一个对象
    public static URL bodyURL = Images.class.getResource("/images/body.jpg");
    //将这个图片封装为程序中的一个对象
    public static ImageIcon bodyImg = new ImageIcon(bodyURL);
    public static URL downURL = Images.class.getResource("/images/down.jpg");

    public static ImageIcon downImg = new ImageIcon(downURL);
    public static URL foodURL = Images.class.getResource("/images/food.jpg");

    public static ImageIcon foodImg = new ImageIcon(foodURL);
    public static URL headerURL = Images.class.getResource("/images/header.jpg");

    public static ImageIcon headerImg = new ImageIcon(headerURL);
    public static URL leftURL = Images.class.getResource("/images/left.jpg");

    public static ImageIcon leftImg = new ImageIcon(leftURL);
    public static URL rightURL = Images.class.getResource("/images/right.jpg");

    public static ImageIcon rightImg = new ImageIcon(rightURL);

    public static URL upURL = Images.class.getResource("/images/up.jpg");

    public static ImageIcon upImg = new ImageIcon(upURL);
}
