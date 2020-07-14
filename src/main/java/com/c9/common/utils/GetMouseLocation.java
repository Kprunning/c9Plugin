package com.c9.common.utils;

import com.c9.common.base.Base;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;


public class GetMouseLocation extends Base {
	public static void main(String[] args) {
		System.out.println(random);
		robot.delay(5000 + random);
		PointerInfo pointerInfo = MouseInfo.getPointerInfo();
		Point location = pointerInfo.getLocation();
		int x = (int)location.getX();
		int y = (int)location.getY();
		
		System.out.println("" + x + "," + y );
	}
	
}
