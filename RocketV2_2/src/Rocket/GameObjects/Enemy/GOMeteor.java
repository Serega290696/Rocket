package Rocket.GameObjects.Enemy;

import Rocket.*;

import java.util.ArrayList;

public class GOMeteor extends GO {

    public static final float Pi = (float)3.14;

    public float u = 0;
    public float v = 0;
    public float al = - Pi / 2;
    public float remx = 0;
    public float remy = 0;

    public float rotate;
    public float speedRot;
    public float speed = 0.1f;
    public static String name = "meteor";
    ArrayList<GO> objects;

    private int scoreBonus = 10;
    public static float[] probabilityAppearance = new float[]{80f, 20f, 10f};

    GO tempOb;

    public GOMeteor(DrawFigure figure, float x, float y, float sx, float sy, float rotate, float speedRot, float speed) {
        this.remx = x;
        this.remy = y;
        this.sx = sx;
        this.sy = sy;
        this.figure = DrawFigure.METEOR_1;
        this.rotate = rotate;
        this.speedRot = speedRot;
        this.speed = speed;
        al = rotate;
    }

    @Override
    public void update(ArrayList<GO> objects) {

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


        float tx = x - Draw.xshift;
        float ty = y - Draw.yshift;
//        if(tx <= - 100 || tx >= 200 || ty <= -100*Main.ratio || ty >= 200*Main.ratio) {
        if(tx <= - 10 || tx >= 110 || ty <= -10*Main.ratio || ty >= 110*Main.ratio) {
            System.out.println("\t\t #"+figure+"# was destroyed!\t\tOVER FRINGE!!!\t\t    " + x + " - " + Draw.xshift + "  :  " + y + " - " + Draw.yshift);
            System.out.print("\t\t                            \t\t               \t\t   " + (tx) + " : " + (ty) + "\t\t");
            if(tx <= - 100)
                System.out.println("Left");
            if(tx >= 200)
                System.out.println("Right");
            if(ty <= -100*Main.ratio)
                System.out.println("Up");
            if(ty >= 200*Main.ratio)
                System.out.println("Down");
            Game.delGO(this);
        }
//        if(x <= - 100 || x >= 200 || y <= -100*Main.ratio || y >= 200*Main.ratio) {
//            System.out.println((x) + " : " + (y));
//            Game.delGO(this);
//        }
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
