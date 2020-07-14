package com.c9.common.base;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.InputEvent;

/**
 * 鼠标位置获取和移动测试
 * 
 * @author 12425
 *
 */
public class Mouse extends Base {


    /**
     * 模拟鼠标左键单击
     */
    public static void clickLeft() {
        robot.delay(50 + random);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.delay(50 + random);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        robot.delay(50 + random);
    }

    /**
     * 模拟鼠标移动到提供点位
     */
    public static void move(int x, int y) {
        robot.delay(500 + random);
        robot.mouseMove(x, y);
        robot.delay(100 + random);
    }

    /**
     * 获取鼠标当前位置
     */
    public static int[] getLocation() {
        PointerInfo pointerInfo = MouseInfo.getPointerInfo();
        Point location = pointerInfo.getLocation();
        int x = (int)location.getX();
        int y = (int)location.getY();
        int[] xy = new int[] {x,y};


        return xy;
    }

    /**
     * 鼠标移动到字符串坐标 , 此坐标会添加随机数0-2
     * 格式:502,699
     * @return
     */
    public static void moveStringLocation(String mouseLocation) {
        String[] xy = mouseLocation.split(",");
        int x = Integer.parseInt(xy[0]);
        int y = Integer.parseInt(xy[1]);
        Mouse.move(x + locRandom, y +locRandom );
    }

    /**
     * 精准移动到输入坐标
     * @param mouseLocation
     */
    public static void moveExactStringLocation(String mouseLocation) {
        String[] xy = mouseLocation.split(",");
        int x = Integer.parseInt(xy[0]);
        int y = Integer.parseInt(xy[1]);
        Mouse.move(x , y );
    }

}