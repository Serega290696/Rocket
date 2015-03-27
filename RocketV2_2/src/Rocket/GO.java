package Rocket;

import Rocket.GameObjects.GOBurst;

import java.util.ArrayList;

public abstract class GO {

    public float x;
    public float y;
    public float sx;
    public float sy;
    public float rotate;
    public DrawFigure figure = DrawFigure.RECTANGLE;
    public String name = "default";
    public boolean animated = true;
    public static float[] probabilityAppearance = {0, 0, 0};

    private int scoreBonus = 10;
    public int weapon = 0;
    public ArrayList<GO> untouchables = new ArrayList<GO>();


    public abstract void update(ArrayList<GO> objects);
    public abstract void render();

    public void draw(DrawFigure figure) {
        Draw.draw(figure, x, y, sx, sy);
    }


    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public float getSX() {
        return sx;
    }
    public float getSY() {
        return sy;
    }

    public String getName(){return name;}

    public void destroy(){this.sx = 0; this.sy = 0; this.figure = DrawFigure.DESTROY; }

    public void collision(GO ob){
        ob.collision();
        collision();
    }
    public void collision() {
        System.out.println("\t#" + figure.toString()+"# was destroyed!");
        if(!this.getName().equals("shot"))
            Game.delGO(this, this);
        else
            Game.delGO(this, null);
    }
    public int getScoreBonus() {
        return scoreBonus;
    }
}
