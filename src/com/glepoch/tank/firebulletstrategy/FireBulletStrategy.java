package com.glepoch.tank.firebulletstrategy;

import com.glepoch.tank.tankgroupfactory.GroupFactory.TankGroupAbtractFactory;
import com.glepoch.tank.tankgroupfactory.tank.TankAbstract;

public interface FireBulletStrategy {

    void fire(TankAbstract tank, TankGroupAbtractFactory tankGroupAbtractFactory);
}
