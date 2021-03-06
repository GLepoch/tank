package com.glepoch.tank.firebulletstrategy.impl;

import com.glepoch.tank.ResourceMgr;
import com.glepoch.tank.firebulletstrategy.FireBulletStrategy;
import com.glepoch.tank.tankgroupfactory.tank.TankAbstract;
import enums.Group;

public class FireOneBulletStrategy implements FireBulletStrategy {
    @Override
    public void fire(TankAbstract tank) {
        int BX = tank.x + ResourceMgr.newInstance().TX / 2 - ResourceMgr.newInstance().BX / 2;
        int BY = tank.y + ResourceMgr.newInstance().TY / 2 - ResourceMgr.newInstance().BY / 2;
        switch (tank.dir) {
            case LEFT:
                if (Group.GOOD == tank.group) {
                    BX = tank.x;
                    BY = tank.y + 15;
                } else {
                    BX = tank.x + 24;
                    BY = tank.y + 27;
                }
                break;
            case RIGHT:
                if (Group.GOOD == tank.group) {
                    BX = tank.x + 24;
                    BY = tank.y + 14;
                } else {
                    BX = tank.x + 26;
                    BY = tank.y + 28;
                }
                break;
            case UP:
                if (Group.GOOD == tank.group) {
                    BX = tank.x + 15;
                    BY = tank.y + 5;
                } else {
                    BX = tank.x + 24;
                    BY = tank.y + 22;
                }
                break;
            case DOWN:
                if (Group.GOOD == tank.group) {
                    BX = tank.x + 16;
                    BY = tank.y + 22;
                } else {
                    BX = tank.x + 24;
                    BY = tank.y + 22;
                }
                break;
        }
        tank.gm.gameObjects.add(tank.gm.tankGroup.createBullet(BX, BY, tank.dir, tank.group, tank.gm));
    }
}
