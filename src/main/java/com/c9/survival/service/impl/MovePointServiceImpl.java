package com.c9.survival.service.impl;

import java.awt.event.KeyEvent;

import com.c9.common.base.Base;
import com.c9.common.base.Keyboard;
import com.c9.common.base.Mouse;
import com.c9.survival.service.MovePointService;
import org.springframework.stereotype.Service;


@Service
public class MovePointServiceImpl extends Base implements MovePointService {

	/**
	 * 功能：自动飞跃到一大陆生存地点 操作步骤： 1.按下M按键； 2.鼠标移动到生存点位置（826.0,510.0）进行点击完成飞跃
	 */
	@Override
	public void moveShengCun1() {

		// 1.按下M按键
		robot.delay(1000);
		Keyboard.pressKey(KeyEvent.VK_M);

		// 2.鼠标移动到右下角的确定按钮上进行点击，完成退出
		robot.delay(2000);
		Mouse.move(826, 510);
		Mouse.clickLeft();
	}
}
