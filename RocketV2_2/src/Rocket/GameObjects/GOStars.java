package Rocket.GameObjects;

import Rocket.*;

import java.util.ArrayList;

public class GOStars extends GO {

    private static final float maxsize = 0.5f;

    public GOStars(DrawFigure figure) {
                 /*
        this.figure = figure;
        rotate = (float) (Math.random());
        this.x = (float) ((Math.random()-0.5)*1000);
        this.y = (float) (Math.random()*100*Main.ratio);
        this.y = (float) ((Math.random()-0.5)*1000*Main.ratio);
        this.sx = (float) (Math.random()*maxsize);
        if(sx == maxsize) sx -= (float) (Math.random()*0.3);
        if(sx >= maxsize*0.8) sx -= (float) (Math.random()*0.3);
        this.sy = sx;   */

        this.figure = figure;
        rotate = (float) (Math.random());
        this.x = (float) ((Math.random()* 120 - 10));
        this.y = (float) ((Math.random()* 120 - 10))*Main.ratio;
        this.sx = (float) (0.1 + Math.random()*maxsize)*3;
        if(sx >= maxsize*0.8) sx -= (float) (Math.random()*0.3);
        //if(sx >= maxsize*0.8) sx -= (float) (Math.random()*0.3);
        this.sy = sx;

        name= "star";
    }

    @Override
    public void update(ArrayList<GO> objects) {

    }

    @Override
    public void render() {
        Draw.draw(figure, (float) (x+ Draw.xshift*(Math.abs(1 - 0.5*(sx*1/maxsize)))), (float)(y+ Draw.yshift*(Math.abs(1 - 0.5*(sy*1/maxsize)))), sx, sy, rotate);
    }

//    @Override
//    public void collision() {
//        System.out.println("Star???");
//    }
    @Override
    public String getName() {
        return name;
    }
}
