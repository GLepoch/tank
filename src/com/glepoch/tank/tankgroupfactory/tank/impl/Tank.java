package com.glepoch.tank.tankgroupfactory.tank.impl;

import com.glepoch.tank.ResourceMgr;
import com.glepoch.tank.firebulletstrategy.FireBulletStrategy;
import com.glepoch.tank.tankgroupfactory.GameObject;
import com.glepoch.tank.tankgroupfactory.tank.TankAbstract;
import com.glepoch.tank.tankimageStrategy.TankImageStrategy;
import enums.Dir;
import enums.Group;
import gamemodelfacade.GameModel;

import java.awt.*;

public class Tank extends TankAbstract {

    public Tank(int x, int y, Dir dir, Group group, GameModel gm, FireBulletStrategy fireBulletStrategy, TankImageStrategy tankImageStrategy) {
        super(x, y, dir, group, gm, fireBulletStrategy, tankImageStrategy);

    }

    @Override
    public void paint(Graphics g) {
        tankImageStrategy.paint(g, this);
    }



    @Override
    public void fire() {
        fireBulletStrategy.fire(this);
    }

    @Override
    public void stop() {
        moving=false;
    }

    @Override
    public void back() {
        x=oldX;
        y=oldY;
    }


}
