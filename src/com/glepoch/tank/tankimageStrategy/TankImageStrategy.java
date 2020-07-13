package com.glepoch.tank.tankimageStrategy;

import com.glepoch.tank.tankgroupfactory.tank.impl.Tank;

import java.awt.*;

public interface TankImageStrategy {
    void paint(Graphics g, Tank tank);
}
