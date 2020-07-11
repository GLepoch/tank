package com.glepoch.tank;

import java.awt.*;

public class Tank {
    private static final int SPEED = 5;
    private boolean moving = false;
    TankMainFrame tmf = null;
    int x = 50, y = 50;
    Dir dir = Dir.DOWN;
    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public Tank(int x, int y, Dir dir, TankMainFrame tmf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tmf = tmf;
    }

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

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public void paint(Graphics g) {
        switch(dir) {
            case LEFT:
                g.drawImage(ResourceMgr.tankL, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.tankU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.tankR, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.tankD, x, y, null);
                break;
        }
        move();
    }

    private void move() {
        if (!moving)
            return;
        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }
    }

    public void fire() {
        int BX=this.x+ResourceMgr.TX/2-ResourceMgr.BX/2;
        int BY=this.y+ResourceMgr.TY/2-ResourceMgr.BY/2;
        switch (dir) {
            case LEFT:
                BX = this.x;
                BY=this.y+22;
                break;
            case RIGHT:
                BX = this.x+ResourceMgr.TX-10;
                BY=this.y+23;
                break;
            case UP:
                BX = this.x+20;
                BY=this.y;
                break;
            case DOWN:
                BX = this.x+18;
                BY=this.y+ResourceMgr.TY-10;
                break;
        }
        tmf.bulletList.add(new Bullet(BX, BY, this.dir, this.tmf));
    }
}
