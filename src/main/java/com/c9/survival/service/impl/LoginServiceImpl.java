package com.c9.survival.service.impl;

import java.awt.event.KeyEvent;

import com.c9.common.base.Base;
import com.c9.common.base.Keyboard;
import com.c9.common.base.Mouse;
import com.c9.survival.service.LoginService;
import org.springframework.stereotype.Service;


@Service
public class LoginServiceImpl extends Base implements LoginService {
	/**
	 * 功能:实现启动游戏,登陆第一个角色
	 */
	@Override
	public void start() {
		openClient();

		enterPassword();

	}

	/**
	 * 功能:在城镇中,完成第一个角色退出,切换为第二个角色
	 */
	@Override
	public void switchSecondRole() {
		// 按下ESC
		Keyboard.pressKey(KeyEvent.VK_ESCAPE);
		// 鼠标移至角色选择按钮,左键点击,enter确认
		Mouse.move(939, 479);
		Mouse.clickLeft();
		Keyboard.pressKey(KeyEvent.VK_ENTER);

		// 等待3s,进入选择角色界面,鼠标移至第2个角色进行单击
		robot.delay(8000);
		Mouse.move(1217, 293);
		Mouse.clickLeft();
		robot.delay(2000);

		// 按下enter键跳出频道选择,鼠标移动,选择第二频道
		Keyboard.pressKey(KeyEvent.VK_ENTER);
		robot.delay(2000);
		Mouse.move(876, 475);
		Mouse.clickLeft();
		Keyboard.pressKey(KeyEvent.VK_ENTER);

		// 按下W行走,避免弹窗干扰
		robot.delay(20000);
		Mouse.clickLeft();
		Keyboard.pressKey(KeyEvent.VK_W);

		System.out.println("角色2成功进入游戏");

	}

    /**
     * 从桌面打开客户端
     */
    @Override
    public void openClient() {
        // 移动到任务栏的C9进行点击
        robot.delay(3000);
        Mouse.move(222, 1060);
        Mouse.clickLeft();
        robot.delay(5000);

        // 移动到客户端的"开始游戏",进行点击
        Mouse.move(1231, 724);
        Mouse.clickLeft();
        robot.delay(50000); // 等待客户端启动50s
    }

    /**
     * 输入密码进行登录
     */
    @Override
    public void enterPassword() {
        // 移动到密码输入框,进行左键点击,复制密码,按下enter确认,
        Mouse.move(917, 625);
        Mouse.clickLeft();
        robot.delay(1000);
        Keyboard.pressKey(KeyEvent.VK_2);
        Keyboard.pressKey(KeyEvent.VK_6);
        Keyboard.pressKey(KeyEvent.VK_6);
        Keyboard.pressKey(KeyEvent.VK_3);
        Keyboard.pressKey(KeyEvent.VK_3);
        Keyboard.pressKey(KeyEvent.VK_8);
        Keyboard.pressKey(KeyEvent.VK_9);
        Keyboard.pressKey(KeyEvent.VK_A);
        robot.delay(1000);
        Keyboard.pressKey(KeyEvent.VK_ENTER);

        // 等待3s,在服务器界面按下enter
        robot.delay(8000);
        Mouse.move(910,638);
        Mouse.clickLeft();
        Mouse.clickLeft();

        // 等待3s,进入选择角色界面,鼠标移至第1个角色进行单击
        robot.delay(8000);
        Mouse.move(1200, 226);
        Mouse.clickLeft();
        robot.delay(2000);

        // 按下enter键跳出频道选择,鼠标移动,选择第二频道
        Keyboard.pressKey(KeyEvent.VK_ENTER);
        robot.delay(2000);
        Mouse.move(876, 475);
        Mouse.clickLeft();
        Keyboard.pressKey(KeyEvent.VK_ENTER);

        // 按下W行走,避免弹窗干扰
        robot.delay(20000);
        Mouse.clickLeft();
        Keyboard.pressKey(KeyEvent.VK_W);

        System.out.println("成功进入游戏");
    }




}
