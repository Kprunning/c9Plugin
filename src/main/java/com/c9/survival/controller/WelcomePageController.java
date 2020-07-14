package com.c9.survival.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class WelcomePageController {
	@RequestMapping("doIndexUI")
	public String doIndexUI(){
		return "starter";//starter.html
	}

}
