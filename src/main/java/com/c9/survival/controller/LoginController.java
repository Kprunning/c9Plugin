package com.c9.survival.controller;

import com.c9.survival.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
    LoginService loginService;
	/**
	 * 功能:开启游戏,输入账号密码,选择第一个角色和线路登入
	 */
	@RequestMapping(value="/firstRole",produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public String firstRole() {
		System.out.println("开始登陆游戏");
		loginService.start();
		return "第一个角色登陆成功";
	}
	
	@RequestMapping(value="/secondRole",produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public String second() {
		loginService.switchSecondRole();
		return "成功切换第二个角色";
	}
	
	
	
	
}
