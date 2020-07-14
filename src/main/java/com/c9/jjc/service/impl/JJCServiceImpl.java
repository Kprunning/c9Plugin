package com.c9.jjc.service.impl;

import com.c9.common.base.*;
import com.c9.common.rpc.client.Client;
import com.c9.jjc.service.JJCService;
import com.fasterxml.jackson.databind.ser.impl.StringCollectionSerializer;
import com.sun.source.doctree.AttributeTree;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.net.InetSocketAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

@Service
public class JJCServiceImpl extends Base implements JJCService {
    // 加载JJC坐标文件
    private static Properties jjcPro = new LoadProperty().loadPro("src/main/resources/jjc/jjc.properties");
    // 加载RPC文件
    private static Properties rpcPro = new LoadProperty().loadPro("src/main/resources/rpc/rpcConfig.properties");
    // 获取主从机
    private String machineRole = jjcPro.getProperty("机器");
    // 获取主从IP
    private String masterIP = rpcPro.getProperty("masterIP");
    private String slaveIP = rpcPro.getProperty("slaveIP");
    // 获取主从端口
    private Integer mPort = Integer.parseInt(rpcPro.getProperty("masterPort"));
    private Integer sPort = Integer.parseInt(rpcPro.getProperty("slavePort"));
    // 用来隔几次发送广告
    private int count = 0;


    /**
     * 竞技场开始方法
     */
    public void start() throws ClassNotFoundException {
        // jdbc:mysql@localhost:3306/
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/c9");
//            PreparedStatement pSql = con.prepareStatement("insert into jjc_auto_log (start, end, cost_time, times) values (?,?,?,?);");
//            // 记录开始时间
//            Date start = new Date();
//            pSql.setDate(1, (java.sql.Date)start);
//

        JJCServiceImpl jjc = new JJCServiceImpl();
        JJCService jjcSlave = null;
        // 房间准备,进入游戏
        if (machineRole.equals("主机")) {
            // 通过RPC调用从机准备(主机调用从机端口)
            jjcSlave = Client.getRemoteProxyObj(Class.forName("com.c9.jjc.service.JJCService"), new InetSocketAddress(slaveIP, sPort));
            // 隔一定次数发送广告
            int round = 5;
            while (true) {
                // 主机准备,等待从机确认
                jjc.ready();
                count++;
//                if (count % round == 0) {
//                    sendADs();
//                }

                // 打印次数
                if (count % 10 == 0) {
                    System.out.println("竞技场已经执行 " + count + " 次!");
                }
                jjcSlave.ready();

                // 主机开始执行跳水
                for (int i = 0; i < 4; i++) {
                    jjc.racing();
                }
                // 通知从机进行跳水
                for (int i = 0; i < 5; i++) {
                    jjcSlave.racing();
                }
                // 等待40s返回大厅
                robot.delay(13000);
            }
        }
//        // 结束时间
//            Date end = new Date();
//            pSql.setDate(2, (java.sql.Date)end);
//        pSql.setDate(3,end.);
//        }catch (Exception e){
//            e.printStackTrace();
//        }

    }

    /**
     * 发送广告
     */
    public void sendADs() {
        // 主机在从机执行跳水任务时可以发送广告
        Keyboard.pressKey(KeyEvent.VK_ENTER);
        String ads = jjcPro.getProperty("广告");
        // 将配置文件的广告复制到剪贴板
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(ads), null);
        // 按下ctrl+v进行粘贴
        Keyboard.pressKeyWithCtrl(KeyEvent.VK_V);
        // 按下enter发送
        Keyboard.pressKey(KeyEvent.VK_ENTER);
    }

    /**
     * 大厅界面,主机勾选自动开始,从机点准备直接进入地图
     */
    public void ready() {
        // 获取配置文件中机器角色,选择执行策略
        if (machineRole.equals("主机")) {
            // 主机(房主)角度编程
            // 大厅界面,勾选右下角自动开始
            robot.delay(5000 + lTimeRandom);
            Mouse.moveStringLocation(jjcPro.getProperty("自动开始"));
            robot.delay(1000 + sTimeRandom);
            Mouse.clickLeft();
            // 通知从机准备有两种方法:从机延时准备,RPC远程调用

        } else {
            // 从机(队员角度编程)
            // 这里采用从机延时准备
            robot.delay(1000 + sTimeRandom);
            Mouse.moveStringLocation(jjcPro.getProperty("开始or就绪"));
            robot.delay(1000 + sTimeRandom);
            Mouse.clickLeft();

            // 载入画面等待
            robot.delay(15000 + lTimeRandom);
        }

        // 等待队员点准备进入游戏
        // 检测主机是否更换,观察队员界面

    }

    /**
     * 进入沃特福德竞赛地图
     * 跳水程序,不分主从机,
     * 主机设定为一定时间后执行,从机接受主机执行完毕信号后开始执行
     */
    public void racing() {
        robot.delay(2000 + lTimeRandom);
        // 按下A到左边
        robot.keyPress(KeyEvent.VK_A);
        robot.delay(1200 + sTimeRandom);
        robot.keyRelease(KeyEvent.VK_A);
        // 按下W跳水
        robot.keyPress(KeyEvent.VK_W);
        robot.delay(2500 + sTimeRandom);
        robot.keyRelease(KeyEvent.VK_W);
        robot.delay(5000 + sTimeRandom);
    }


    /**
     * 改进:不作为跳水次数依据,而是作为辅助检测进行状况,进行程序报错和终止
     *
     * @param ballNum 输入要检查球的编号
     */
    public void checkBallColor(int ballNum) {
        String ballName = "球" + ballNum;
        // 加载属性文件
        Properties pro = new LoadProperty().loadPro("resources/jjc/jjc.properties");
        // 获取小球坐标
        String ballLocation = pro.getProperty(ballName);
        // 根据小球坐标检查小球颜色
        int color = ColorHelper.getRGBBySC(ballLocation);
        int abs = Math.abs(color - Integer.parseInt(pro.getProperty("")));
        // 判断小球颜色是否和配置文件相符
        if (abs > 10000) {
            // 误差大于10000颜色异常
            System.out.println("脚本执行异常,请注意!");
            // 出现异常进行QQ通知和关机处理
            //Runtime.getRuntime().exec("shutdown /s /t 60");
        }
    }

    /**
     * 测试rpc效果
     */
    public void sayHi(String name) {
        System.out.println("Hello " + name + " !");

    }

    /**
     * 在主机意外断线的情况下,从机退出房间
     */
    @Override
    public void exit() {
        Mouse.moveStringLocation(jjcPro.getProperty("退出"));
        Mouse.clickLeft();
        robot.delay(4000 + lTimeRandom);
    }

    @RequestMapping("jjc/enterRoom/{num}")
    @Override
    public void enterRoom(@PathVariable String num) {
        // 点进组队赛进行筛选
        Mouse.moveStringLocation(jjcPro.getProperty("组队赛"));
        Mouse.clickLeft();

        // 选择房间
        Mouse.moveStringLocation(jjcPro.getProperty("房间" + num));
        Mouse.clickLeft();
        Mouse.clickLeft();
        Mouse.moveStringLocation(jjcPro.getProperty("密码输入位置"));
        Mouse.clickLeft();
        // 输入密码:5689
        Keyboard.pressKey(KeyEvent.VK_5);
        Keyboard.pressKey(KeyEvent.VK_6);
        Keyboard.pressKey(KeyEvent.VK_8);
        Keyboard.pressKey(KeyEvent.VK_9);

        Mouse.moveStringLocation(jjcPro.getProperty("确认密码"));
        Mouse.clickLeft();
    }

}
