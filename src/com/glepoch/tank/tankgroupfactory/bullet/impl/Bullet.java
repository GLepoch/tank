package com.glepoch.tank.tankgroupfactory.bullet.impl;

import com.glepoch.tank.tankgroupfactory.bullet.BulletAbstarct;
import enums.Dir;
import enums.Group;
import gamemodelfacade.GameModel;

public class Bullet extends BulletAbstarct {

    public Bullet(int x, int y, Dir dir, Group group, GameModel gm) {
        super(x, y, dir, group, gm);
    }
}
