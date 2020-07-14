package com.c9.common.base;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;

public class Keyboard extends Base {

	/**
	 * 模拟单独按下某个键位
	 */
	public static void pressKey(int key) {
		// 1.按下enter按键
		robot.delay(300 + random);
		robot.keyPress(key);
		robot.delay(50 + random);
		robot.keyRelease(key);
		robot.delay(100 + random);
	}

	/**
	 * 通过键盘赋值字符串, 1.目前用于用户和密码的输入
	 */
	public static void pressString(String str) {
		// 获取剪切板
		Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable tText = new StringSelection(str);
		// 设置剪切板内容
		clip.setContents(tText, null);
		Mouse.clickLeft();
		System.out.println("准备粘贴");
		pressKeyWithCtrl(KeyEvent.VK_V);//粘贴
        robot.delay(100);

	}
	
	/**
	 * 模拟ctrl + 按键的组合输入
	 * @param key
	 */
	public static void pressKeyWithCtrl(int key) {
		robot.delay(100);
		robot.keyPress(KeyEvent.VK_CONTROL);
        robot.delay(50);
        robot.keyPress(key);
        robot.delay(50);
		robot.keyRelease(key);
        robot.delay(50);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.delay(100);
	}
}
