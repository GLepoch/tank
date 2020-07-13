package com.glepoch.tank.tankgroupfactory.tank.impl;

import com.glepoch.tank.TankMainFrame;
import com.glepoch.tank.firebulletstrategy.FireBulletStrategy;
import com.glepoch.tank.tankgroupfactory.GroupFactory.TankGroupAbtractFactory;
import com.glepoch.tank.tankgroupfactory.tank.TankAbstract;
import com.glepoch.tank.tankimageStrategy.TankImageStrategy;
import enums.Dir;
import enums.Group;

import java.awt.*;

public class Tank extends TankAbstract {

    public Tank(int x, int y, Dir dir, Group group, TankMainFrame tmf, FireBulletStrategy fireBulletStrategy, TankImageStrategy tankImageStrategy, TankGroupAbtractFactory tankGroupAbtractFactory) {
        super(x, y, dir, group, tmf, fireBulletStrategy, tankImageStrategy,tankGroupAbtractFactory);

    }

    @Override
    public void paint(Graphics g) {
        tankImageStrategy.paint(g, this);
    }

    @Override
    public void fire() {
        fireBulletStrategy.fire(this,tankGroupAbtractFactory);
    }


}
