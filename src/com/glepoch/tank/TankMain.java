package com.glepoch.tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

/**
 * @description:
 * @author: ygl
 * @createDate: 2020/7/10/0010
 * @version: 1.0
 */
public class TankMain {

    public static void main(String[] args) {
        Random random=new Random();
        TankMainFrame tank = new TankMainFrame();
        Integer badTankCount = PropertiesMgr.getInt("badTankCount");
        for (int i = 0; i < badTankCount; i++) {
            int btx = random.nextInt(TankMainFrame.GAME_WIDTH);
            int bty = random.nextInt(TankMainFrame.GAME_HEIGHT);
            Dir dir = Dir.values()[random.nextInt(4)];
            if(btx>=TankMainFrame.GAME_WIDTH-ResourceMgr.TX){
                btx=TankMainFrame.GAME_WIDTH-ResourceMgr.TX;
            }
            if(bty>=TankMainFrame.GAME_HEIGHT-ResourceMgr.TY){
                bty=TankMainFrame.GAME_HEIGHT-ResourceMgr.TY;
            }
            tank.badTankList.add(new Tank(btx, bty, dir, Group.BAD, tank));
        }
        //new Thread(()->new Audio("audio/war1.wav").loop()).start();
        while (true) {
            try {
                Thread.sleep(20);
                tank.repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
