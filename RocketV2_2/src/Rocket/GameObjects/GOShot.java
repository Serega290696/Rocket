package Rocket.GameObjects;

import Rocket.*;

import java.util.ArrayList;

public class GOShot extends GO {

    public static String name = "shot";

    public static final float Pi = (float) Math.PI;
    public float speed;
    ArrayList<GO> objects;

    public ArrayList<GO> untouchables = new ArrayList<GO>();
    GO tempOb = null;

    public GOShot(float x, float y, float rotate, ArrayList<GO> objects, GO go, int weapon) {
        this(x, y, 2f, 4f, rotate, objects, 2, go, weapon);
    }
    public GOShot(float x, float y, float sx, float sy, float rotate, ArrayList<GO> objects, float speed, GO go, int weapon) {
        this.speed = speed;
        this.x = x;
        this.y = y;
        this.sx = sx;
        this.sy = sy;
        this.rotate = rotate;
        this.objects = objects;
        switch(weapon) {
            case 1:
                this.figure = DrawFigure.SHOT_1;
                break;
            case 2:
                this.figure = DrawFigure.SHOT_2;
                break;
            case 3:
                this.figure = DrawFigure.SHOT_3;
                break;
            case 4:
                this.figure = DrawFigure.SHOT_4;
                break;
            case 5:
                this.figure = DrawFigure.SHOT_5;
                break;
            default:
                this.figure = DrawFigure.SHOT_4;
        }
        this.speed = 1+weapon;
        untouchables.add(go);
    }


    @Override
    public void update(ArrayList<GO> objects) {
        this.objects = objects;


        x += speed * Math.sin((rotate) / 180 * Pi);
        y += speed * Math.cos((rotate) / 180 * Pi);

        if(x - Draw.xshift <= 0 || x - Draw.xshift >= 100 || y - Draw.yshift <= 0 || y - Draw.yshift >= 100*Main.ratio) {
            Game.delGO(this);
        }


        tempOb = null;
        for(GO ob : objects) {
            boolean destroy;
            boolean giveBonusToPlayer = false;

            if(Physics.checkCollisions(this, ob) && ob != this) {
                destroy = true;
                if(untouchables.get(0) != null) {
                    for(GO untouchable : untouchables) {
                        if(untouchable.getClass().getSimpleName().equals("GOPlayer")) giveBonusToPlayer = true;
                        System.out.println("THIS:: " + untouchable.getClass().getSimpleName());
                        if(ob.equals(untouchable)) destroy = false;
                    }
                }
                if(destroy) {
                    if(giveBonusToPlayer)
                        GOPlayer.setScore(ob.getScoreBonus());
                    tempOb = ob;
                }
                break;
            }
        }
        if(tempOb != null) {
            collision(tempOb);
        }

    }
    @Override
    public void render() {
        Draw.draw(figure, x, y, sx/4, (float) (sy), rotate);
    }


    public String getName() {
        return name;
    }

}
