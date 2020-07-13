package com.glepoch.tank.tankimageStrategy;

import com.glepoch.tank.tankgroupfactory.tank.TankAbstract;

import java.awt.*;

public interface TankImageStrategy {
    void paint(Graphics g, TankAbstract tank);
}
