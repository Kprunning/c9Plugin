package com.c9.survival.controller;

import com.c9.survival.service.MovePointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/movePoint")
public class MovePointController {
	@Autowired
	private MovePointService movePointServic;
	
	/**
	 * 功能:移动到1大陆生存点
	 */
	@RequestMapping(value="/shengCun1",produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public String flyToShengCunPoint() {
		movePointServic.moveShengCun1();
		return "成功飞跃到生存点";
	} 
}
