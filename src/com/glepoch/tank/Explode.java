package com.glepoch.tank;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class Explode {
    int x = 10, y = 10;
    private boolean alive = true;
    TankMainFrame tmf = null;
    private List<BufferedImage> explodes = ResourceMgr.newInstance().explodes;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Explode(int x, int y, TankMainFrame tmf) {
        this.x = x;
        this.y = y;
        this.tmf = tmf;
    }

    int index = 0;

    public void paint(Graphics g) {
        g.drawImage(explodes.get(index++), this.x, this.y, null);
        if (index >= explodes.size()) {
            alive = false;
            tmf.explodeList.remove(this);
        }

    }
}
