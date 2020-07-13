package com.glepoch.tank;

import com.glepoch.tank.firebulletstrategy.impl.FireFoureBulletBulletStrategy;
import com.glepoch.tank.firebulletstrategy.impl.FireOneBulletBulletStrategy;
import com.glepoch.tank.tankgroupfactory.tank.impl.Tank;
import com.glepoch.tank.tankimageStrategy.impl.BadTankStrategy;
import enums.Dir;
import enums.Group;

import java.util.Random;

/**
 * @description:
 * @author: ygl
 * @createDate: 2020/7/10/0010
 * @version: 1.0
 */
public class TankMain {

    public static void main(String[] args) {
        Random random = new Random();
        TankMainFrame tank = new TankMainFrame();
        Integer badTankCount = PropertiesMgr.getInt("badTankCount");
        for (int i = 0; i < badTankCount; i++) {
            int btx = random.nextInt(TankMainFrame.GAME_WIDTH);
            int bty = random.nextInt(TankMainFrame.GAME_HEIGHT);
            Dir dir = Dir.values()[random.nextInt(4)];
            if (btx >= TankMainFrame.GAME_WIDTH - ResourceMgr.newInstance().TX) {
                btx = TankMainFrame.GAME_WIDTH - ResourceMgr.newInstance().TX;
            }
            if (bty >= TankMainFrame.GAME_HEIGHT - ResourceMgr.newInstance().TY) {
                bty = TankMainFrame.GAME_HEIGHT - ResourceMgr.newInstance().TY;
            }
            if (i % 5 != 0) {
                tank.badTankList.add(new Tank(btx, bty, dir, Group.BAD, tank, new FireOneBulletBulletStrategy(), new BadTankStrategy()));
            } else {
                tank.badTankList.add(new Tank(btx, bty, dir, Group.BAD, tank, new FireFoureBulletBulletStrategy(), new BadTankStrategy()));
            }
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
