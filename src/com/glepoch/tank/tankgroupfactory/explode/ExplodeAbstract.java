package com.glepoch.tank.tankgroupfactory.explode;

import com.glepoch.tank.ResourceMgr;
import com.glepoch.tank.TankMainFrame;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public abstract class ExplodeAbstract {
    public int x = 10, y = 10;
    public boolean alive = true;
    public TankMainFrame tmf = null;
    public List<BufferedImage> explodes = ResourceMgr.newInstance().explodes;
    public int index = 0;

    public ExplodeAbstract(int x, int y, TankMainFrame tmf) {
        this.x = x;
        this.y = y;
        this.tmf = tmf;
    }


    public void paint(Graphics g) {
        g.drawImage(explodes.get(index++), this.x, this.y, null);
        if (index >= explodes.size()) {
            alive = false;
            tmf.explodeList.remove(this);
        }

    }
}
