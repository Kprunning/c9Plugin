package com.c9.common.utils;


import com.c9.common.base.Base;
import com.c9.common.base.Mouse;

/**
 * 功能:在后台进行工匠制作时,每50个材料制作完成,自动切换到游戏窗口
 * 注意:每次开始前重新定位C9窗口位置
 * @author 张文
 *
 */
public class Timer extends Base {
	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 3; i++) {
			robot.delay(60 * 1000);
		}
		robot.delay(30 * 1000);
		Mouse.move(369,843);
		Mouse.clickLeft();
	}
}
