package com.c9.app;

import com.c9.common.base.Base;
import com.c9.common.base.ColorHelper;
import com.c9.common.base.Mouse;
import com.c9.jjc.service.impl.JJCServiceImpl;

import java.util.Date;

public class StartJJC extends Base {
    public static void main(String[] args) {
        JJCServiceImpl jjc = new JJCServiceImpl();
        // 房间准备,进入游戏
        //jjc.ready();
        // 主机开始执行跳水
//        for (int i = 0; i < 1; i++) {
//            jjc.racing();
//        }
        while(true){
            long start = new Date().getTime();
            // 每100ms检测一次,左侧坐标的颜色,来确认是否走到了可以跳水的位置
//            robot.delay(50);
            int leftRiver = ColorHelper.getRGBBySC("455, 650");
            System.out.println(leftRiver);
            int rightRiver = ColorHelper.getRGBBySC("1078, 612");
//            if(leftRiver == Integer.parseInt(pro.getProperty("河流颜色") ) &&
//                    rightRiver == Integer.parseInt(pro.getProperty("河流颜色"))){
//                // 如果左边或者右边颜色和配置文件一致,跳出循环
//                break;
//            }
            // 因为颜色很难保证完全一致,所以差距范围在10000内都认定为同一种颜色
            int leftBound = Math.abs(leftRiver - ColorHelper.getRGBBySC(Mouse.getLocation()));
            System.out.println(leftBound);
            int rightBound = Math.abs(rightRiver - ColorHelper.getRGBBySC(Mouse.getLocation()));
            long end = new Date().getTime();
            System.out.println("一次循环耗时:" + (end - start) + "ms");
            if(leftBound < 10000 && rightBound < 10000){
                // 如果颜色在误差范围内,则认为河流出现,跳出循环
                break;
            }
        }

    }
}
