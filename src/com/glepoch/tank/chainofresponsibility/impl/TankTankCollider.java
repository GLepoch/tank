package com.glepoch.tank.chainofresponsibility.impl;

import com.glepoch.tank.chainofresponsibility.Collider;
import com.glepoch.tank.tankgroupfactory.GameObject;
import com.glepoch.tank.tankgroupfactory.tank.TankAbstract;
import com.glepoch.tank.tankgroupfactory.tank.impl.Tank;

public class TankTankCollider implements Collider {
    @Override
    public boolean collider(GameObject o1, GameObject o2) {
        if (o1 instanceof TankAbstract && o2 instanceof TankAbstract) {
            TankAbstract t1 = (TankAbstract) o1;
            TankAbstract t2 = (TankAbstract) o2;
            if (t1.rect.intersects(t2.rect)) {
                t1.back();
                t2.back();
            }
        }
        return true;
    }
}
