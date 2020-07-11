package com.glepoch.tank;

import java.awt.*;
import java.util.Random;

public class Tank {
    private static final int SPEED = 3;
    private boolean moving = false;
    TankMainFrame tmf = null;
    private int x = 50, y = 50;
    Dir dir = Dir.DOWN;
    private boolean alive = true;
    private Group group = Group.BAD;

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    private Random random = new Random();

    public Tank(int x, int y, Dir dir, Group group, TankMainFrame tmf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tmf = tmf;
        this.group = group;
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void paint(Graphics g) {
        if (!alive) return;
        switch (dir) {
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
        if (random.nextInt(10) > 8 && this.group != Group.GOOD) {
            this.fire();
        }
    }

    public void fire() {
        int BX = this.x + ResourceMgr.TX / 2 - ResourceMgr.BX / 2;
        int BY = this.y + ResourceMgr.TY / 2 - ResourceMgr.BY / 2;
        switch (dir) {
            case LEFT:
                BX = this.x;
                BY = this.y + 22;
                break;
            case RIGHT:
                BX = this.x + ResourceMgr.TX - 10;
                BY = this.y + 23;
                break;
            case UP:
                BX = this.x + 20;
                BY = this.y;
                break;
            case DOWN:
                BX = this.x + 18;
                BY = this.y + ResourceMgr.TY - 10;
                break;
        }
        tmf.bulletList.add(new Bullet(BX, BY, this.dir, this.group, this.tmf));
    }

    public void die() {
        alive = false;
        tmf.badTankList.remove(this);
    }
}
