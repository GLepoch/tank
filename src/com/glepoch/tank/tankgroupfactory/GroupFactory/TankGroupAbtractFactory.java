package com.glepoch.tank.tankgroupfactory.GroupFactory;

import com.glepoch.tank.TankMainFrame;
import com.glepoch.tank.firebulletstrategy.FireBulletStrategy;
import com.glepoch.tank.tankgroupfactory.bullet.BulletAbstarct;
import com.glepoch.tank.tankgroupfactory.explode.ExplodeAbstract;
import com.glepoch.tank.tankgroupfactory.tank.TankAbstract;
import com.glepoch.tank.tankimageStrategy.TankImageStrategy;
import enums.Dir;
import enums.Group;

public abstract class TankGroupAbtractFactory {
    public abstract TankAbstract createTank(int x, int y, Dir dir, Group group, TankMainFrame tmf, FireBulletStrategy fireBulletStrategy, TankImageStrategy tankImageStrategy);

    public abstract BulletAbstarct createBullet(int x, int y, Dir dir, Group group, TankMainFrame tmf);

    public abstract ExplodeAbstract createExplode(int x, int y, TankMainFrame tmf);
}
