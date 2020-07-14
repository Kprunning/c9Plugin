package com.c9.common.login;

import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 打开C9
 * 目前打开客户端无问题,但是进入不到游戏会弹出报错
 */
@Component
public class OpenC9 {
    public void start(){
        Runtime runtime = Runtime.getRuntime();
        String path = "C:\\Game\\C9Client\\C9Launcher.exe";
        try {
            runtime.exec(path);
        } catch (IOException e) {
            System.out.println("打开游戏失败,请检查安装路径!");
        }
    }
}
