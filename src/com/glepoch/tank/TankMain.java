package com.glepoch.tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @description:
 * @author: ygl
 * @createDate: 2020/7/10/0010
 * @version: 1.0
 */
public class TankMain {
    public static void main(String[] args) {
        TankMainFrame tank=new TankMainFrame();
        for (int i = 0; i < 5; i++) {
            tank.badTankList.add(new Tank(100+i*100,50,Dir.DOWN,tank));
        }
        while (true){
            try {
                Thread.sleep(50);
                tank.repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
