package com.c9.common.utils;

import com.c9.common.base.Base;
import com.c9.common.base.ColorHelper;
import com.c9.common.base.Mouse;

import java.awt.*;
import java.util.Arrays;

public class GetColor extends Base {
    public static void main(String[] args) {
        robot.delay(3000);
        int rgb = ColorHelper.getRGBBySC(Mouse.getLocation());
        System.out.println("当前鼠标位置颜色:" + rgb);
        //int r2 = ColorHelper.getRGBBySC("455, 650");
        //System.out.println("指定坐标的颜色为:" + r2);
       // ColorHelper.saveScreenCapture();

    }
}
