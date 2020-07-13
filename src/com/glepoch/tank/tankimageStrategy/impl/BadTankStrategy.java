package com.glepoch.tank.tankimageStrategy.impl;

import com.glepoch.tank.ResourceMgr;
import com.glepoch.tank.tankgroupfactory.tank.impl.Tank;
import com.glepoch.tank.tankimageStrategy.TankImageStrategy;

import java.awt.*;

public class BadTankStrategy implements TankImageStrategy {
    @Override
    public void paint(Graphics g, Tank tank) {
        if (!tank.alive) return;
        switch (tank.dir) {
            case LEFT:
                g.drawImage(ResourceMgr.newInstance().badTankL, tank.x, tank.y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.newInstance().badTankU, tank.x, tank.y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.newInstance().badTankR, tank.x, tank.y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.newInstance().badTankD, tank.x, tank.y, null);
                break;
        }
        tank.move();
    }
}
