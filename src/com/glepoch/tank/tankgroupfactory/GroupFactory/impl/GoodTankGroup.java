package com.glepoch.tank.tankgroupfactory.GroupFactory.impl;

import com.glepoch.tank.TankMainFrame;
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

public class GoodTankGroup extends TankGroupAbtractFactory {
    @Override
    public TankAbstract createTank(int x, int y, Dir dir, Group group, TankMainFrame tmf, FireBulletStrategy fireBulletStrategy, TankImageStrategy tankImageStrategy, TankGroupAbtractFactory tankGroupAbtractFactory) {
        return new Tank(x, y, dir, group, tmf, fireBulletStrategy, tankImageStrategy, tankGroupAbtractFactory);
    }

    @Override
    public BulletAbstarct createBullet(int x, int y, Dir dir, Group group, TankMainFrame tmf, TankGroupAbtractFactory tankGroupAbtractFactory) {
        return new Bullet(x, y, dir, group, tmf, tankGroupAbtractFactory);
    }

    @Override
    public ExplodeAbstract createExplode(int x, int y, TankMainFrame tmf) {
        return new Explode(x, y, tmf);
    }
}
