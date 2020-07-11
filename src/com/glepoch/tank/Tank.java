package com.glepoch.tank;

import java.awt.*;

public class Tank {
    private static final int SPEED = 5;
    private boolean moving = false;
    TankMainFrame tmf=null;
    int x = 10, y = 10;
    Dir dir = Dir.DOWN;

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public Tank(int x, int y, Dir dir,TankMainFrame tmf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tmf=tmf;
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
        g.fillRect(x, y, 20, 20);
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
       tmf.bulletList.add(new Bullet(this.x,this.y,this.dir,this.tmf));
    }
}
