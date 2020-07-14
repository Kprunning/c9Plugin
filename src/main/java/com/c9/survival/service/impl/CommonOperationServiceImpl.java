package com.c9.survival.service.impl;

import java.awt.event.KeyEvent;
import java.io.IOException;

import com.c9.common.base.Base;
import com.c9.common.base.Keyboard;
import com.c9.common.base.Mouse;
import com.c9.survival.service.CommonOperationService;
import org.springframework.stereotype.Service;


@Service
public class CommonOperationServiceImpl extends Base implements CommonOperationService {

	/**
	 * 完成退出副本操作 操作步骤： 1、按下ctrl 激活鼠标； 2、鼠标移动到右下角的确定按钮上进行点击，完成退出
	 */
	/**
	 *
	 */
	@Override
	public void exit() {
		// 1.按下enter按键
		robot.delay(500);
		Keyboard.pressKey(KeyEvent.VK_ENTER);

		// 2.鼠标移动到右下角的确定按钮上进行点击，完成退出
		robot.delay(300);
		Mouse.move(1247, 861);
		Mouse.clickLeft();
	}

	

	/**
	 * 功能：按下enter，接受组队邀请
	 */
	@Override
	public void acceptInviting() {
		// 1.按下enter按键
		robot.delay(1000);
		Keyboard.pressKey(KeyEvent.VK_ENTER);
	}
	
	/**
	 * 功能:关机
	 */
	@Override
	public void shutdown() {
		Runtime run = Runtime.getRuntime();
		try {

			run.exec("Shutdown.exe -s -t 60");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}




}
