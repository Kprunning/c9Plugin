package com.c9.common.base;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;

public class TestKeybo {

        /**
         * @param args
         * @throws AWTException
         */
        public static void main(String[] args) throws Exception {
                // TODO Auto-generated method stub
                Robot robot = new Robot(); //创建一个robot对象
                Runtime.getRuntime().exec("notepad");        //打开一个记事本程序
                robot.delay(2000);        //等待 2秒
                //窗口最大化
                keyPressWithAlt(robot, KeyEvent.VK_SPACE); //按下 alt+ 空格
                keyPress(robot, KeyEvent.VK_X);  //按下x键
                robot.delay(1000);  //等待 1秒
                keyPressString(robot, "大家好，我是一个小机器人，我有很多本领呢 ！"); //输入字符串
                robot.delay(3000);  //等待 3秒
                keyPress(robot, KeyEvent.VK_ENTER); // 按下 enter 换行
                keyPressString(robot, "现在，我就向大家展示一下.....嘻嘻"); //输入字符串
                robot.delay(3000);  //等待 3秒
                keyPress(robot, KeyEvent.VK_ENTER); // 按下 enter 换行
                keyPressString(robot, "首先，我能按下 键盘的任何一个键。下面,我单独按下a,b,c,d键"); //输入字符串
                keyPress(robot, KeyEvent.VK_ENTER); // 按下 enter 换行
                robot.delay(3000);  //等待 3秒
                keyPress(robot, KeyEvent.VK_A); //按下 a 键
                robot.delay(2000);  //等待 2秒
                keyPress(robot, KeyEvent.VK_B); //按下 b 键
                robot.delay(2000);  //等待 2秒
                keyPress(robot, KeyEvent.VK_C); //按下 c 键
                robot.delay(2000);  //等待 2秒
                keyPress(robot, KeyEvent.VK_D); //按下 d 键
                robot.delay(2000);  //等待 2秒
                keyPress(robot, KeyEvent.VK_ENTER); // 按下 enter 换行
                keyPressString(robot, "呵呵，完成了。。。。  ");
                robot.delay(3000);  //等待 3秒
                keyPress(robot, KeyEvent.VK_ENTER); // 按下 enter 换行
                keyPressString(robot, "恩，对了    上面 文字很多  是不是 感到 很乱呢？？？     我现在 帮你清空一下 ");
                robot.delay(2000);  //等待 2秒
                keyPressWithCtrl(robot,KeyEvent.VK_A); //按下 ctrl+A 全选
                robot.delay(2000);  //等待 2秒
                keyPress(robot,KeyEvent.VK_DELETE); //清除
                robot.delay(3000);  //等待 3秒
                keyPressString(robot, "恩，现在 是不是 觉得 清爽多了              另外 我还会按 组合键呢 ...");
                keyPress(robot, KeyEvent.VK_ENTER); // 按下 enter 换行
                robot.delay(3000);  //等待 3秒
                keyPressString(robot, "................好像已经 演示过了 吧 ，呵呵    ");
                keyPress(robot, KeyEvent.VK_ENTER); // 按下 enter 换行
                robot.delay(3000);  //等待 3秒
                keyPressString(robot, "其实，我还有很多本领呢                           现在就不向大家展示了 .....");
                keyPress(robot, KeyEvent.VK_ENTER); // 按下 enter 换行
                robot.delay(3000);  //等待 3秒
                keyPressString(robot, "谢谢大家！！！！！");
                
                

        }

        // shift+ 按键
        public static void keyPressWithShift(Robot r, int key) {
                r.keyPress(KeyEvent.VK_SHIFT);
                r.keyPress(key);
                r.keyRelease(key);
                r.keyRelease(KeyEvent.VK_SHIFT);
                r.delay(100);
        }

        // ctrl+ 按键
        public static void keyPressWithCtrl(Robot r, int key) {
                r.keyPress(KeyEvent.VK_CONTROL);
                r.keyPress(key);
                r.keyRelease(key);
                r.keyRelease(KeyEvent.VK_CONTROL);
                r.delay(100);
        }

        // alt+ 按键
        public static void keyPressWithAlt(Robot r, int key) {
                r.keyPress(KeyEvent.VK_ALT);
                r.keyPress(key);
                r.keyRelease(key);
                r.keyRelease(KeyEvent.VK_ALT);
                r.delay(100);
        }
        //打印出字符串
        public static void keyPressString(Robot r, String str){
                Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();//获取剪切板
                Transferable tText = new StringSelection(str);
                clip.setContents(tText, null); //设置剪切板内容
                keyPressWithCtrl(r, KeyEvent.VK_V);//粘贴
                r.delay(100);
        }
        
        //单个 按键
        public static void keyPress(Robot r,int key){
                r.keyPress(key);
                r.keyRelease(key);
                r.delay(100);
        }
}