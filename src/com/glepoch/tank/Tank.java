package com.glepoch.tank;

import java.awt.*;
import java.util.Random;

public class Tank {
    private static final int SPEED = 1;
    public Rectangle rect=new Rectangle();;
    private boolean moving = true;
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
        rect.x=this.x;
        rect.y=this.y;
        rect.width=ResourceMgr.TX;
        rect.height=ResourceMgr.TY;
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
                if (Group.GOOD == this.group)
                    g.drawImage(ResourceMgr.tankL, x, y, null);
                else
                    g.drawImage(ResourceMgr.badTankL, x, y, null);
                break;
            case UP:
                if (Group.GOOD == this.group)
                    g.drawImage(ResourceMgr.tankU, x, y, null);
                else
                    g.drawImage(ResourceMgr.badTankU, x, y, null);
                break;
            case RIGHT:
                if (Group.GOOD == this.group)
                    g.drawImage(ResourceMgr.tankR, x, y, null);
                else
                    g.drawImage(ResourceMgr.badTankR, x, y, null);
                break;
            case DOWN:
                if (Group.GOOD == this.group)
                    g.drawImage(ResourceMgr.tankD, x, y, null);
                else
                    g.drawImage(ResourceMgr.badTankD, x, y, null);
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
        if (random.nextInt(100) > 95 && this.group != Group.GOOD) {
            this.fire();
        }
        if (random.nextInt(100) > 95 && this.group != Group.GOOD) {
            this.changeDir();
        }
        boundsCheck();
        rect.x=this.x;
        rect.y=this.y;
    }

    private void boundsCheck() {
        if (this.x < 0) this.x = 0;
        if (this.x > TankMainFrame.GAME_WIDTH - ResourceMgr.TX) this.x = TankMainFrame.GAME_WIDTH - ResourceMgr.TX;
        if (this.y < 50) this.y = 50;
        if (this.y > TankMainFrame.GAME_HEIGHT - ResourceMgr.TY) this.y = TankMainFrame.GAME_HEIGHT - ResourceMgr.TY;
    }

    private void changeDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    public void fire() {
        int BX = this.x + ResourceMgr.TX / 2 - ResourceMgr.BX / 2;
        int BY = this.y + ResourceMgr.TY / 2 - ResourceMgr.BY / 2;
        switch (dir) {
            case LEFT:
                if (Group.GOOD == this.group) {
                    BX = this.x;
                    BY = this.y + 15;
                } else {
                    BX = this.x + 24;
                    BY = this.y + 27;
                }
                break;
            case RIGHT:
                if (Group.GOOD == this.group) {
                    BX = this.x + 24;
                    BY = this.y + 14;
                } else {
                    BX = this.x + 26;
                    BY = this.y + 28;
                }
                break;
            case UP:
                if (Group.GOOD == this.group) {
                    BX = this.x + 15;
                    BY = this.y + 5;
                } else {
                    BX = this.x + 24;
                    BY = this.y + 22;
                }
                break;
            case DOWN:
                if (Group.GOOD == this.group) {
                    BX = this.x + 16;
                    BY = this.y + 22;
                } else {
                    BX = this.x + 24;
                    BY = this.y + 22;
                }
                break;
        }
        tmf.bulletList.add(new Bullet(BX, BY, this.dir, this.group, this.tmf));
    }

    public void die() {
        alive = false;
        System.out.println(this.alive + "   " + this.group);
        tmf.badTankList.remove(this);
        tmf.tank = null;
    }
}
