package com.glepoch.tank;

import enums.Dir;
import enums.Group;

import java.awt.*;

public class Bullet {
    private static final int SPEED =  PropertiesMgr.getInt("bulletSpeed");;
    int x = 10, y = 10;
    Dir dir = Dir.DOWN;
    private boolean alive = true;
    TankMainFrame tmf = null;
    private Group group = Group.BAD;
    Rectangle rect=new Rectangle();

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
        this.group = group;
        rect.x=this.x;
        rect.y=this.y;
        rect.width=ResourceMgr.newInstance().TX;
        rect.height=ResourceMgr.newInstance().TY;
    }

    public void paint(Graphics g) {
        if (!alive) {
            this.tmf.bulletList.remove(this);
        }
        switch (dir) {
            case LEFT:
                if (Group.GOOD == this.group)
                    g.drawImage(ResourceMgr.newInstance().bulletL, x, y, null);
                else
                    g.drawImage(ResourceMgr.newInstance().badBulletL, x, y, null);
                break;
            case UP:
                if (Group.GOOD == this.group)
                    g.drawImage(ResourceMgr.newInstance().bulletU, x, y, null);
                else
                    g.drawImage(ResourceMgr.newInstance().badBulletU, x, y, null);
                break;
            case RIGHT:
                if (Group.GOOD == this.group)
                    g.drawImage(ResourceMgr.newInstance().bulletR, x, y, null);
                else
                    g.drawImage(ResourceMgr.newInstance().badBulletR, x, y, null);
                break;
            case DOWN:
                if (Group.GOOD == this.group)
                    g.drawImage(ResourceMgr.newInstance().bulletD, x, y, null);
                else
                    g.drawImage(ResourceMgr.newInstance().badBulletD, x, y, null);
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
        rect.x=this.x;
        rect.y=this.y;
        if (this.x < 0 || this.y < 0 || x > tmf.getWidth() || y > tmf.getHeight()) alive = false;
    }

    public void collideWith(Tank tank) {
        if(tank==null) return;
        if (this.group == tank.getGroup()) return;
        if (rect.intersects(tank.rect)) {
            this.die();
            tank.die();
            int EX = tank.getX()+ResourceMgr.newInstance().TX/2-ResourceMgr.newInstance().EX/2;
            int EY = tank.getY() +ResourceMgr.newInstance().TY/2-ResourceMgr.newInstance().EY/2;
            tmf.explodeList.add(new Explode(EX, EY, tmf));
        }
    }

    private void die() {
        alive = false;
    }
}
