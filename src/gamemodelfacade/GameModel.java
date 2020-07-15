package gamemodelfacade;

import com.glepoch.tank.PropertiesMgr;
import com.glepoch.tank.ResourceMgr;
import com.glepoch.tank.TankMainFrame;
import com.glepoch.tank.chainofresponsibility.Collider;
import com.glepoch.tank.chainofresponsibility.impl.BulletTankCollider;
import com.glepoch.tank.chainofresponsibility.impl.ColliderChain;
import com.glepoch.tank.chainofresponsibility.impl.TankTankCollider;
import com.glepoch.tank.chainofresponsibility.impl.WallTankCollider;
import com.glepoch.tank.firebulletstrategy.impl.FireFoureBulletStrategy;
import com.glepoch.tank.firebulletstrategy.impl.FireOneBulletStrategy;
import com.glepoch.tank.tankgroupfactory.GameObject;
import com.glepoch.tank.tankgroupfactory.GroupFactory.TankGroupAbtractFactory;
import com.glepoch.tank.tankgroupfactory.GroupFactory.impl.BadTankGroup;
import com.glepoch.tank.tankgroupfactory.GroupFactory.impl.GoodTankGroup;
import com.glepoch.tank.tankgroupfactory.Wall.impl.Wall;
import com.glepoch.tank.tankgroupfactory.bullet.BulletAbstarct;
import com.glepoch.tank.tankgroupfactory.explode.ExplodeAbstract;
import com.glepoch.tank.tankgroupfactory.tank.TankAbstract;
import com.glepoch.tank.tankimageStrategy.impl.BadTankStrategy;
import com.glepoch.tank.tankimageStrategy.impl.GoodTankStrategy;
import enums.Dir;
import enums.Group;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 * 门面设计模式，管理所有模型：坦克，子弹，爆炸，墙等
 * */
public class GameModel {
    public TankGroupAbtractFactory tankGroup = new GoodTankGroup();
    public List<GameObject> gameObjects = new ArrayList<>();
    public TankAbstract tank = tankGroup.createTank(PropertiesMgr.getInt("goodTankX"), PropertiesMgr.getInt("goodTankY"), Dir.UP, Group.GOOD, this, new FireOneBulletStrategy(), new GoodTankStrategy());
    ColliderChain chain=new ColliderChain();

    public GameModel() {
        createBadTank();
        chain.add(new BulletTankCollider()).add(new TankTankCollider()).add(new WallTankCollider());
    }

    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.CYAN);
/*        g.drawString("子弹数量：" + this.bulletList.size(), 20, 50);
        g.drawString("敌人数量：" + this.badTankList.size(), 20, 80);
        g.drawString("爆炸数量:" + explodeList.size(), 20, 100);*/
        for (int i = 0; i < gameObjects.size(); i++) {
            gameObjects.get(i).paint(g);
        }

        g.setColor(color);
        for (int i = 0; i < gameObjects.size(); i++) {
            for (int i1 = i + 1; i1 < gameObjects.size(); i1++) {
                chain.collider(gameObjects.get(i), gameObjects.get(i1));
            }
        }
    }

    void createBadTank() {
        Random random = new Random();
        Integer badTankCount = PropertiesMgr.getInt("badTankCount");
        for (int i = 0; i < badTankCount; i++) {
            int btx = random.nextInt(TankMainFrame.GAME_WIDTH);
            int bty = random.nextInt(TankMainFrame.GAME_HEIGHT);
            Dir dir = Dir.values()[random.nextInt(4)];
            if (btx >= TankMainFrame.GAME_WIDTH - ResourceMgr.newInstance().TX) {
                btx = TankMainFrame.GAME_WIDTH - ResourceMgr.newInstance().TX;
            }
            if (bty >= TankMainFrame.GAME_HEIGHT - ResourceMgr.newInstance().TY) {
                bty = TankMainFrame.GAME_HEIGHT - ResourceMgr.newInstance().TY;
            }
            if (i % 5 != 0) {
                this.gameObjects.add(tankGroup.createTank(btx, bty, dir, Group.BAD, this, new FireOneBulletStrategy(), new BadTankStrategy()));
            } else {
                this.gameObjects.add(tankGroup.createTank(btx, bty, dir, Group.BAD, this, new FireFoureBulletStrategy(), new BadTankStrategy()));
            }
        }
        gameObjects.add(tank);
        gameObjects.add(new Wall());
    }
}
