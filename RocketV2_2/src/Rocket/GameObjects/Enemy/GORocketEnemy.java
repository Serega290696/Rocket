package Rocket.GameObjects.Enemy;

import Rocket.*;
import Rocket.GameObjects.GOPlayer;
import Rocket.GameObjects.GOShot;

import java.util.ArrayList;

public class GORocketEnemy extends GO {

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

    public float shootDelay = 500;
    public float shootTime = 0;

    private int weapon = 1;
    private int scoreBonus = 50;

    GOShot shot = null;
    GO tempOb;
    public static float[] probabilityAppearance = new float[]{5, 40, 35};

    public GORocketEnemy(DrawFigure figure, float x, float y, float sx, float sy, float rotate, float speedRot, float speed) {
        this.remx = x;
        this.remy = y;
        this.sx = sx;
        this.sy = sy;
        this.figure = DrawFigure.ROCKET_ENEMY;
        this.rotate = rotate;
        this.speedRot = speedRot;
        this.speed = speed;
        if(this.speed >= 0.3) this.speed = 0.3f;
        al = rotate;
    }

    @Override
    public void update(ArrayList<GO> objects) {


        if(x <= - 100 || x >= 200 || y <= -100*Main.ratio || y >= 200*Main.ratio) {
            Game.delGO(this);
        }

        if(shootTime != 0)
            shootTime -= Main.fps;
        else if(shootTime < 0)
            shootTime = 0;
        if(shootTime <= 0 && (getX() >= 0 && getX() <= 100 && getY() >= 0 && getY() <= 100*Main.ratio)){
            Game.addObj(shot = new GOShot((float) (getX() + 1.5f*sy * Math.sin(rotate / 360 * 2 * Math.PI +speed) /2), (float) (getY() + 1.5f*sy * Math.cos(rotate / 360 * 2 * Math.PI /2)), 1.5f, 2f, rotate, objects, 1, this, 1));
            shootTime = shootDelay;
        }

        this.objects = objects;
        rotate += speedRot;
        u+=speed;

        v = (float)Math.pow(u, 0.75) * 7;

        x = (float)(u * Math.cos(al) - v * Math.sin(al)) + remx;
        y = (float)(u * Math.sin(al) + v * Math.cos(al)) + remy;

        for(GO ob : objects) {
            boolean destroy;
            boolean giveBonusToPlayer = false;

            if(Physics.checkCollisions(this, ob) && ob != this) {
                destroy = false;

                if(ob instanceof GOShot)
                    for(GO untouchable : ob.untouchables) {
                        if(untouchable.getClass().getSimpleName().equals("GOPlayer")) giveBonusToPlayer = true;
                        if(ob.equals(untouchable)) destroy = false;
                    }
                if(destroy) {
                    if(giveBonusToPlayer)
                        GOPlayer.setScore(ob.getScoreBonus());
                    tempOb = ob;
                }
                break;
            }
        }
        if(tempOb != null)
            collision(tempOb);

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
