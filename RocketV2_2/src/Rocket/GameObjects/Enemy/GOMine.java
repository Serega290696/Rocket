package Rocket.GameObjects.Enemy;

import Rocket.*;

import java.util.ArrayList;

public class GOMine extends GO {

    public static final float Pi = (float)3.14;

    public float u = 0;
    public float v = 0;
    public float al = - Pi / 2;
    public float remx = 0;
    public float remy = 0;

    public float rotate;
    public float speedRot;
    public float speed = 0.1f;
    public static String name = "mine";
    ArrayList<GO> objects;

    private int scoreBonus = 20;

    GO tempOb;

    public GOMine(DrawFigure figure, float x, float y, float sx, float sy, float rotate, float speedRot, float speed) {
        this.remx = x;
        this.remy = y;
        this.sx = sx;
        this.sy = sy;
        this.figure = figure;
        this.rotate = rotate;
        this.speedRot = speedRot;
        this.speed = speed;
        al = rotate;
    }

    @Override
    public void update(ArrayList<GO> objects) {

        if(x <= - 100 || x >= 200 || y <= -100*Main.ratio || y >= 200*Main.ratio) {
            Game.delGO(this);
        }

        this.objects = objects;
        rotate += speedRot;
        u+=speed;

        v = (float)Math.pow(u, 0.5) * 7;

        x = (float)(u * Math.cos(al) - v * Math.sin(al)) + remx;
        y = (float)(u * Math.sin(al) + v * Math.cos(al)) + remy;

        for(GO ob : objects) {
            if(Physics.checkCollisions(this, ob) && ob != this) {
                tempOb = ob;
                break;
            }
        }
        if(tempOb != null) {
            collision(tempOb);
        }
    }

    @Override
    public void render() {
        Draw.draw(figure, x, y, sx, sy, rotate);
    }

    @Override
    public String getName() {
        return name;
    }
    @Override
    public int getScoreBonus() {
        return scoreBonus;
    }
}
