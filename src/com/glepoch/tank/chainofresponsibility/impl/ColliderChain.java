package com.glepoch.tank.chainofresponsibility.impl;

import com.glepoch.tank.chainofresponsibility.Collider;
import com.glepoch.tank.tankgroupfactory.GameObject;

import java.util.ArrayList;
import java.util.List;

public class ColliderChain implements Collider {
    List<Collider> colliderList = new ArrayList<>();

    @Override
    public boolean collider(GameObject o1, GameObject o2) {
        for (int i = 0; i < colliderList.size(); i++) {
            boolean b = colliderList.get(i).collider(o1, o2);
            if (!b) {
                return false;
            }
        }
        return true;
    }

    public ColliderChain add(Collider collider) {
        this.colliderList.add(collider);
        return this;
    }
}
