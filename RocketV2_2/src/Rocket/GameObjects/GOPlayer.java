package Rocket.GameObjects;

import Rocket.*;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;

public class GOPlayer extends GO {

    public static float rotate = 0;
    public static float speed = 0;
    public static float defaultPosX = 0;
    public static float defaultPosY = 0;


    public static float timeToRegeneration = 1000;
    public static float regeneration = 0;
    public static float shootTime = 0;

    public static float speedup = (float)0.9;
    public static float speeddown = speedup / 5;
    public static float maxspeed = speedup * 4;
    public static final float Pi = (float)3.14;


    public static String name = "player";
    ArrayList<GO> objects;

    GO tempOb;

    //Game atributes
    public static int defaultHitPoints = 3;
    public static int hitPoints = defaultHitPoints;
    private static int score = 0;
    private static boolean aura = true;
    public static boolean immortal = false;
    public static int weapon = 1;
    public static float shootDelay = 260 - weapon * 40;


    public GOPlayer(DrawFigure figure, float x, float y, float sx, float sy, float rotate, ArrayList<GO> objects) {
        this.x = x;
        this.y = y;
        this.sx = sx;
        this.sy = sy;
        this.figure = figure;
        this.speed = 0;
        this.rotate = rotate;
        this.objects = objects;

        this.aura = true;
        defaultPosX = x;
        defaultPosY = y;
        score = 0;
        weapon = 1;
    }


    @Override
    public void update(ArrayList<GO> objects) {
        if(immortal) aura = true;
        speedup = 0.2f + 0.05f * weapon;
        speeddown = speedup / 5;
        maxspeed = speedup * 4;

        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+x + " : " + y + "\t\t" + Draw.xshift + " : " + Draw.yshift);
        this.objects = objects;

        x += speed * Math.sin((rotate) / 180 * Pi);
        y += speed * Math.cos((rotate) / 180 * Pi);
        if(Math.abs(speed) <= 0.1) speed = 0;
        speed += -speeddown * Math.signum(speed);


        shootDelay = 260 - weapon * 40;
        if(shootTime != 0)
            shootTime -= Main.fps;
        else if(shootTime < 0)
            shootTime = 0;
        else if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
            Game.addObj(new GOShot((float) (getX() + sy * Math.sin(rotate / 360 * 2 * Math.PI) ), (float) (getY() + sy * Math.cos(rotate / 360 * 2 * Math.PI) ), rotate, objects, this, weapon))   ;
            shootTime = shootDelay;
        }


        tempOb = null;
        for(GO ob : objects) {
            if(Physics.checkCollisions(this, ob) && ob != this && !ob.getName().equals("shot")) {
                tempOb = ob;
                break;
            }
        }
        if(tempOb != null) {
            collision(tempOb);
        }



        if(x <= Draw.xshift || x >= Draw.xshift + 100 || y <= Draw.yshift || y >= Draw.yshift + 100*Main.ratio) {
            x = 50;
            y = 40;
            Draw.xshift = 0;
            Draw.yshift = 0;
        }

        if(regeneration > 0)
            collision();

    }


    @Override
    public void render() {
        Draw.draw(figure, x, y, sx, sy, rotate);
    }


    public void rotate(float a) {
        //if(speed >= 0)
            rotate += a;
        //if(speed < 0)
            //rotate -= a;
    }
    public void move(int a) {
        if(speed > -maxspeed && speed < maxspeed)
            speed += a * speedup;
    }
    public void moveNUM(float a) {
        x+=a;
    }

    @Override
    public void collision() {

        if(aura) {
            aura = false;
            return;
        }
        figure = DrawFigure.VOID;

        if(regeneration == 0) Game.delGO(null, this, 5);

        regeneration += Main.delay;
        Game.rocketControll = false;

        if(regeneration >= timeToRegeneration) {

            hitPoints--;
            if(hitPoints <= 0)
                Game.lose();

            Game.rocketControll = true;
            figure = DrawFigure.ROCKET_2;
            regeneration = 0;
            System.out.println("Oops!");
            rotate = 180;
            Draw.xshift = 0;
            Draw.yshift = 0;
            x = defaultPosX;
            y = defaultPosY;
            aura = true;
        }
    }



    @Override
    public String getName() {
        return name;
    }
    public static int getHP() {
        return hitPoints;
    }
    public static int getWeapon() {
        return weapon;
    }
    public static int getScore() {
        return score;
    }

    public static void setScore(int a) {
        score+=a;
    }

    public static boolean getAura() {
        return aura;
    }
}
