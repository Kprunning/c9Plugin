package com.c9.jjc.service;

import com.c9.common.base.ColorHelper;
import com.c9.common.base.LoadProperty;
import com.c9.common.base.Mouse;
import com.c9.jjc.service.impl.JJCServiceImpl;

import java.awt.event.KeyEvent;
import java.util.Properties;
import java.util.Random;

public interface JJCService {
    void start() throws ClassNotFoundException;

    void ready();

    /**
     * 进入沃特福德竞赛地图
     * 跳水程序,不分主从机,
     * 主机设定为一定时间后执行,从机接受主机执行完毕信号后开始执行
     */
    void racing();


    /**
     * 改进:不作为跳水次数依据,而是作为辅助检测进行状况,进行程序报错和终止
     * @param ballNum 输入要检查球的编号
     */
   void checkBallColor(int ballNum);

   void sayHi(String name);

    /**
     * 退出竞技场
     */
   void exit();

    /**
     * 寻找房间并进入
     */
    void enterRoom(String num);

}
