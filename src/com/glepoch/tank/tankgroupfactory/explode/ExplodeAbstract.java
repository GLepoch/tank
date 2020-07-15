package com.glepoch.tank.tankgroupfactory.explode;

import com.glepoch.tank.ResourceMgr;
import com.glepoch.tank.tankgroupfactory.GameObject;
import gamemodelfacade.GameModel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public abstract class ExplodeAbstract extends GameObject {
    public boolean alive = true;
    public GameModel gm = null;
    public List<BufferedImage> explodes = ResourceMgr.newInstance().explodes;
    public int index = 0;

    public ExplodeAbstract(int x, int y, GameModel gm) {
        this.x = x;
        this.y = y;
        this.gm = gm;
    }


    public void paint(Graphics g) {
        g.drawImage(explodes.get(index++), this.x, this.y, null);
        if (index >= explodes.size()) {
            alive = false;
            gm.gameObjects.remove(this);
        }

    }
}
