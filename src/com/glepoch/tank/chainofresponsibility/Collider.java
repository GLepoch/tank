package com.glepoch.tank.chainofresponsibility;

import com.glepoch.tank.tankgroupfactory.GameObject;

public interface Collider {
    boolean collider(GameObject o1,GameObject o2);
}
