package com.c9.common.base;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;

/**
 * 用来获取鼠标位置颜色
 */

public class ColorHelper extends Base{


    /**
     * 获取当前位置3原色信息并打印
     */
    public static void printColor(int x,int y) {

        robot.delay(500 + random);
        Color color = robot.getPixelColor(x, y);

        System.out.println(color);

        System.out.println(color.getRed());
        System.out.println(color.getGreen());
        System.out.println(color.getBlue());
    }

    /**
     * 直接通过坐标获取颜色
     * 缺点:游戏类窗口可能获取颜色失败,建议用getXYRGB替代
     * @param location
     * @return
     */
    public static Color getStringColor(String location){
        int[] xy = handleStingLocation(location);
        Color color = robot.getPixelColor(xy[0], xy[1]);
        return color;
    }


    /**
     * 通过屏幕截图,然后再获取点的颜色
     * @param location 字符类型
     * @return
     */
    public static int getRGBBySC(String location){
        int[] xy = handleStingLocation(location);
        int rgb = getRGBBySC(xy);
        return rgb;
    }

    public static int getRGBBySC(int[] xy){
        Toolkit tk = Toolkit.getDefaultToolkit();
        // 屏幕尺寸规格
        Dimension di = tk.getScreenSize();
        Rectangle rec = new Rectangle(0, 0, di.width, di.height);
        // 进行截屏并获取坐标位置的颜色
        int rgb  = robot.createScreenCapture(rec).getRGB(xy[0], xy[1]);
        return rgb;
    }

    /**
     * 将传入的字符串坐标进行处理
     * @param location 格式可以为(x,y)和x,y
     * @return 返回处理过得到的x和y坐标
     */
    private static int[] handleStingLocation(String location){
        robot.delay(500 + random);
        String[] xy = location.split(",");
        int x = 0, y = 0;
        // 进行分割后的元素判断
        // 如果元素的长度大于1,说明输入的坐标由口号进行包裹
        if(xy[0].length() == 1){
            x = Integer.parseInt(xy[0]);
            y = Integer.parseInt(xy[1]);
        }else{
            x = xy[0].charAt(1);
            y = xy[1].charAt(1);
        }

        return new int[]{x, y};
    }

    /**
     * 进行全屏截图
     */
    public static void saveScreenCapture(){
        Toolkit tk = Toolkit.getDefaultToolkit();
        // 屏幕尺寸规格
        Dimension di = tk.getScreenSize();
        Rectangle rec = new Rectangle(0, 0, di.width, di.height);
        BufferedImage bi = robot.createScreenCapture(rec);
        try {
            ImageIO.write(bi,"png",new File("D:\\store\\picCache\\" + "test" + ".png"));
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("截图成功!");
    }

}
