package Rocket;

import java.awt.*;

public class Physics {


    public static float sx1;
    public static float sy1;
    public static float sx2;
    public static float sy2;
    public static boolean checkCollisions(GO go1, GO go2) {
        //
        // rotate .......... 360
        // Math.sin, cos ... 2*Pi
        //
        //
            /*
        if(go1.rotate >= 0) {
            sx1 = go1.sx;
            sy1 = go1.sy;
        }
        if(go1.rotate >= 90) {
            sx1 = go1.sy;
            sy1 = -go1.sx;
        }
        if(go1.rotate >= 180) {
            sx1 = -go1.sx;
            sy1 = -go1.sy;
        }
        if(go1.rotate >= 360) {
            sx1 = -go1.sy;
            sy1 = go1.sx;
        }

        if(go2.rotate >= 0) {
            sx2 = go2.sx;
            sy2 = go2.sy;
        }
        if(go2.rotate >= 90) {
            sx2 = go2.sy;
            sy2 = -go2.sx;
        }
        if(go2.rotate >= 180) {
            sx2 = -go2.sx;
            sy2 = -go2.sy;
        }
        if(go2.rotate >= 360) {
            sx2 = -go2.sy;
            sy2 = go2.sx;
        }

        Rectangle r1 = new Rectangle((int)go1.getX(), (int)go1.getY(), (int)sx1, (int)sy1);
        Rectangle r2 = new Rectangle((int)go2.getX(), (int)go2.getY(), (int)sx2, (int)sy2);*/
        sx1 = go1.getSX();
        sy1 = go1.getSY();
        sx2 = go2.getSX();
        sy2 = go2.getSY();
        Rectangle r1 = new Rectangle((int)(go1.getX()-sx1/2), (int)(go1.getY()-sy1/2), (int)sx1, (int)sy1);
        Rectangle r2 = new Rectangle((int)(go2.getX()-sx2/2), (int)(go2.getY()-sy2/2), (int)sx2, (int)sy2);
        return r1.intersects(r2);
    }


}
