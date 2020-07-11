package com.glepoch.tank;

import java.awt.*;

public class Bullet {
    private static final int SPEED = 10;
    int x = 10, y = 10;
    Dir dir = Dir.DOWN;
    private boolean alive = true;
    TankMainFrame tmf = null;
    private Group group=Group.BAD;

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

    public Bullet(int x, int y, Dir dir, Group group, TankMainFrame tmf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tmf = tmf;
        this.group=group;
    }

    public void paint(Graphics g) {
        if (!alive) {
            this.tmf.bulletList.remove(this);
        }
        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
        }
        move();
    }

    private void move() {
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
        if (this.x < 0 || this.y < 0 || x > tmf.getWidth() || y > tmf.getHeight()) alive = false;
    }

    public void collideWith(Tank tank) {
        if(this.group==tank.getGroup()) return;
        Rectangle brec = new Rectangle(this.x, this.y, ResourceMgr.BX, ResourceMgr.BY);
        Rectangle trec = new Rectangle(tank.getX(), tank.getY(), ResourceMgr.TX, ResourceMgr.TY);
        if (brec.intersects(trec)) {
            this.die();
            tank.die();
        }
    }

    private void die() {
        alive = false;
    }
}
