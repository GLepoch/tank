package com.glepoch.tank.chainofresponsibility.impl;

import com.glepoch.tank.chainofresponsibility.Collider;
import com.glepoch.tank.tankgroupfactory.GameObject;
import com.glepoch.tank.tankgroupfactory.Wall.WallAbstract;
import com.glepoch.tank.tankgroupfactory.tank.TankAbstract;

public class WallTankCollider implements Collider {
    @Override
    public boolean collider(GameObject o1, GameObject o2) {
        if (o1 instanceof WallAbstract && o2 instanceof TankAbstract) {
            WallAbstract wall = (WallAbstract) o1;
            TankAbstract tank = (TankAbstract) o2;
            if(wall.rect.intersects(tank.rect)){
                tank.back();
            }
        }else if(o2 instanceof WallAbstract && o1 instanceof TankAbstract){
            collider(o2,o1);
        }
        return true;
    }
}
