package com.glepoch.tank.tankgroupfactory.Wall.impl;

import com.glepoch.tank.ResourceMgr;
import com.glepoch.tank.chainofresponsibility.Collider;
import com.glepoch.tank.tankgroupfactory.Wall.WallAbstract;

import java.awt.*;

public class Wall extends WallAbstract {
    public Wall() {
        rect.x = 100;
        rect.y = 100;
        rect.width = 100;
        rect.height = 100;
    }

    @Override
    public void paint(Graphics g) {
       Color color= g.getColor();
       g.setColor(Color.yellow);
       g.fillRect(100,100,100,100);
       g.setColor(color);
    }
}
