package com.c9.common.utils;

import com.c9.common.base.Base;
import com.c9.common.base.Mouse;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;


/**
 * 用于自动开启工匠箱子
 * 使用步骤:
 * 1.分辨率调整为最低,窗口模式
 * 2.鼠标定位到背包栏最左边,然后开始执行
 * 3.最好保证工匠箱子下面无其他东西,防止误开
 * 
 * 鼠标定位:
 * 背包状态:工匠位于左上角第一个:
 * 背包格子左1:886,524 左2:926,524.格子间隔为:40,
 * 背包向下翻一行定位:1110,652
 * 
 * @author 张文
 *
 */
public class AutoOpenBoxes extends Base {
	public static void main(String[] args) {
		// 格子定位变量
		int x = 886;
		int y = 524;
		// 设定箱子行数
		int boxCount = 8;
		
		
		robot.delay(5000);
		System.out.println("程序启动");
		for(int i = 0;i < boxCount; i ++) {
			// 移动到背包指定格子
			for(int j = 0;j < 6 ;j ++) {
				Mouse.move(x, y);
				openBox();
				x = x + 40;
			}
			// 跳转下一行
			Mouse.move(1110,652);
			Mouse.clickLeft();
			// 重置x坐标
			x = 886;
		}
		System.out.println("程序结束");
	}

	private static void openBox() {
		// 先点击物品然后放下,避免直接ctrl+右键出错
		Mouse.clickLeft();
		robot.delay(521);
		Mouse.clickLeft();
		robot.delay(583);
		// 执行ctrl + 右键,箱子连续开启
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.delay(329);
		robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
		robot.delay(131);
		robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
		robot.delay(393);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.delay(564);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.delay(81);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.delay(11530);
		
	}
	
}
