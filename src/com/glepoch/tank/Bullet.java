package com.glepoch.tank;

import java.awt.*;

public class Bullet {
    private static final int SPEED = 10;
    int x = 10, y = 10;
    Dir dir = Dir.DOWN;
    private boolean alive = true;
    TankMainFrame tmf = null;
    private Group group = Group.BAD;

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
    }

    public void paint(Graphics g) {
        if (!alive) {
            this.tmf.bulletList.remove(this);
        }
        switch (dir) {
            case LEFT:
                if (Group.GOOD == this.group)
                    g.drawImage(ResourceMgr.bulletL, x, y, null);
                else
                    g.drawImage(ResourceMgr.badBulletL, x, y, null);
                break;
            case UP:
                if (Group.GOOD == this.group)
                    g.drawImage(ResourceMgr.bulletU, x, y, null);
                else
                    g.drawImage(ResourceMgr.badBulletU, x, y, null);
                break;
            case RIGHT:
                if (Group.GOOD == this.group)
                    g.drawImage(ResourceMgr.bulletR, x, y, null);
                else
                    g.drawImage(ResourceMgr.badBulletR, x, y, null);
                break;
            case DOWN:
                if (Group.GOOD == this.group)
                    g.drawImage(ResourceMgr.bulletD, x, y, null);
                else
                    g.drawImage(ResourceMgr.badBulletD, x, y, null);
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
        if(tank==null) return;
        if (this.group == tank.getGroup()) return;
        Rectangle brec = new Rectangle(this.x, this.y, ResourceMgr.BX, ResourceMgr.BY);
        Rectangle trec = new Rectangle(tank.getX(), tank.getY(), ResourceMgr.TX, ResourceMgr.TY);
        if (brec.intersects(trec)) {
            this.die();
            tank.die();
            int EX = tank.getX()+ResourceMgr.TX/2-ResourceMgr.EX/2;
            int EY = tank.getY() +ResourceMgr.TY/2-ResourceMgr.EY/2;
            tmf.explodeList.add(new Explode(EX, EY, tmf));
        }
    }

    private void die() {
        alive = false;
    }
}
