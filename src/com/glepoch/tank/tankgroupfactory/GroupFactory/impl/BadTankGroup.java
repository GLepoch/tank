package com.glepoch.tank.tankgroupfactory.GroupFactory.impl;

import com.glepoch.tank.firebulletstrategy.FireBulletStrategy;
import com.glepoch.tank.tankgroupfactory.GroupFactory.TankGroupAbtractFactory;
import com.glepoch.tank.tankgroupfactory.bullet.BulletAbstarct;
import com.glepoch.tank.tankgroupfactory.bullet.impl.Bullet;
import com.glepoch.tank.tankgroupfactory.explode.ExplodeAbstract;
import com.glepoch.tank.tankgroupfactory.explode.impl.Explode;
import com.glepoch.tank.tankgroupfactory.tank.TankAbstract;
import com.glepoch.tank.tankgroupfactory.tank.impl.Tank;
import com.glepoch.tank.tankimageStrategy.TankImageStrategy;
import enums.Dir;
import enums.Group;
import gamemodelfacade.GameModel;

public class BadTankGroup extends TankGroupAbtractFactory {
    @Override
    public TankAbstract createTank(int x, int y, Dir dir, Group group, GameModel gm, FireBulletStrategy fireBulletStrategy, TankImageStrategy tankImageStrategy) {
        return new Tank(x, y, dir, group, gm, fireBulletStrategy, tankImageStrategy);
    }

    @Override
    public BulletAbstarct createBullet(int x, int y, Dir dir, Group group, GameModel gm) {
        return new Bullet(x, y, dir, group, gm);
    }

    @Override
    public ExplodeAbstract createExplode(int x, int y, GameModel gm) {
        return new Explode(x, y, gm);
    }
}
