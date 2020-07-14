package com.c9.run;

import com.c9.common.base.Base;
import com.c9.jjc.service.impl.JJCServiceImpl;

import java.util.Date;

public class RunJJC extends Base {
    public static void main(String[] args) throws ClassNotFoundException, InterruptedException {
        JJCServiceImpl jjcService = new JJCServiceImpl();
//        jjcService.start();
        // 设定多少分钟喊一次话
        int circleTime = 5;
        System.out.println(Math.round(circleTime * 0.8));
        while (true) {
            for (int i = 0; i < circleTime; i++) {
                robot.delay(60000);
                if (i == Math.round(circleTime * 0.8 - 1)){
                    System.out.println("即将发送广告 " + new Date());
                }
            }
            jjcService.sendADs();
            System.out.println("广告发送完毕!" + new Date());
            System.out.println();
        }
    }
}


