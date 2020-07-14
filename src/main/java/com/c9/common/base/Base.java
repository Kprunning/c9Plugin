package com.c9.common.base;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.Random;


/**
 * 	1.提供鼠标键盘模拟输入机器人 robot
 * 	2.提供随机变量 random，模拟输入点击随机延时
 * @author 12425
 *
 */
public class Base {
	protected static Robot robot;
    /**位置随机数,范围0~3*/
	protected static int locRandom = new Random().nextInt(3);
	/**较长时间随机数,用于界面转化的随机数添加,范围1000~2000ms*/
	protected static int lTimeRandom = new Random().nextInt(1000) + 1000;
    /**短时间随机数,用于按键随机等,80~150ms*/
	protected static int sTimeRandom = new Random().nextInt(100);
    /**旧的遗留随机数,有时间请替换成其它随机数*/
    protected static int random = new Random().nextInt(10);

	// 在静态代码块中对异常变量进行初始化
	static {
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	
}
