package com.glepoch.tank.chainofresponsibility.impl;

import com.glepoch.tank.chainofresponsibility.Collider;
import com.glepoch.tank.tankgroupfactory.GameObject;
import com.glepoch.tank.tankgroupfactory.bullet.BulletAbstarct;
import com.glepoch.tank.tankgroupfactory.bullet.impl.Bullet;
import com.glepoch.tank.tankgroupfactory.tank.TankAbstract;
import com.glepoch.tank.tankgroupfactory.tank.impl.Tank;
import enums.Group;

public class BulletTankCollider implements Collider {
    @Override
    public boolean collider(GameObject o1, GameObject o2) {
        if (o1 instanceof BulletAbstarct && o2 instanceof TankAbstract) {
            Bullet bullet = (Bullet) o1;
            Tank tank = (Tank) o2;
            if (tank.group == Group.BAD)
                bullet.collideWith(tank);
        }else if(o2 instanceof Bullet && o1 instanceof Tank){
            collider(o2,o1);
        }
        return true;
    }
}
