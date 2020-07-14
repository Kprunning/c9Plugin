package com.c9.survival.controller;

import com.c9.survival.service.CommonOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@RequestMapping("/common")
public class CommonOperationController {
	
	@Autowired
	private CommonOperationService mkService;
	
	/**
	 * 	功能：点击右下角确定键，完成退出副本功能
	 * @return 提示退出副本成功
	 */
	@RequestMapping(value="/doExit",produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public String clickRightBottom() {
		mkService.exit();
		return "退出副本成功！";
	}
	
	/**
	 * 	功能：接受组队邀请,produces="application/text"
	 */
	@RequestMapping(value="/acceptInviting",produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public String acceptInviting() {
		mkService.acceptInviting();
		return "接受组队成功";
	}
	
	/**
	 * 功能：关机
	 */
	@RequestMapping(value="/shutdown",produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public String shutdown() {
		mkService.shutdown();
		return "shutdown succeed";
	}
}
