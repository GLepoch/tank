package com.glepoch.tank.tankgroupfactory.bullet.impl;

import com.glepoch.tank.TankMainFrame;
import com.glepoch.tank.tankgroupfactory.bullet.BulletAbstarct;
import enums.Dir;
import enums.Group;

public class Bullet extends BulletAbstarct {

    public Bullet(int x, int y, Dir dir, Group group, TankMainFrame tmf) {
        super(x, y, dir, group, tmf);
    }
}
