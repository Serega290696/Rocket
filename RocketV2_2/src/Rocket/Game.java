package Rocket;

import Rocket.GameObjects.*;
import Rocket.GameObjects.Enemy.GOMeteor;
import Rocket.GameObjects.Enemy.GOMine;
import Rocket.GameObjects.Enemy.GORocketEnemy;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

import org.lwjgl.input.Mouse;

public class Game {

    public static ArrayList<GO> objects = new ArrayList<GO>();
    public static ArrayList<GO> newObj = new ArrayList<GO>();
    public static ArrayList<GO> fon = new ArrayList<GO>();
    public static GOPlayer player;

    public static Date beginTime;
//    public static Date currentTime;
//    public static long bTime;
//    public static long bDTime;
    public static long curTime;
//    public static long curDTime;
//    public static int bsec = (int)(bTime % 60);
//    public static int bmin = (int)((bTime % 3600)/60);
//    public static int bhour =  (int)((bTime % (60 * 3600))/60);
//    public static int sec = (int)(curTime % 60);
//    public static int min = (int)((curTime % 3600)/60);
//    public static int hour =  (int)((curTime % (60 * 3600))/60);
    private static boolean changeObjects = false;
    private static float distanceToFringe = 25;//percent
    private static float minDtf = distanceToFringe;//percent
    private static float maxDtf = 40;//percent
    private static boolean nearFringe = false;
    private static int numDestroyObj = 0;
    public static int burstAmount = 0;

    private static boolean mute = true;
    public static boolean rocketControll = true;
    private static boolean inMenu = false;
    private static boolean inAdvert = true;
    public static int level = 1;
    public static int levelTime[] = {120, 180, 300};
    public static int levelScore[] = {300, 2000, 10000};

    public static int advert = 3;

    public static float xshiftT = Draw.xshift;
    public static float yshiftT = Draw.yshift;

    public Game() {
        Draw.init();
        clear();


        for(int i = 0; i < 100; i++)
            fon.add(new GOStars(DrawFigure.STAR_1));


        inAdvert();



    }

    public static void clear() {
        level = 1;
        player.hitPoints = player.defaultHitPoints;
        objects.clear();
        fon.clear();
        Draw.xshift = 0;
        Draw.yshift = 0;
        numDestroyObj = 0;
        beginTime = new Date();
//        currentTime = new Date();
//        bTime = System.currentTimeMillis()/1000;
//        bDTime = System.currentTimeMillis()/100;

        objects.add(player = new GOPlayer(DrawFigure.ROCKET_2, 50, 60*Main.ratio, 2.5f, 3, 180, objects));
        objects.add(new GORocketEnemy(DrawFigure.ROCKET_ENEMY, 50, 40*Main.ratio, 3, 3, 0, 0, 5.0f));

    }

    public static void inMenu() {
        Mouse.setGrabbed(false);
        rocketControll = false;
        inMenu = true;
    }
    public static void inAdvert() {
        Mouse.setGrabbed(false);
        rocketControll = false;
        inAdvert = true;
    }

    public static void outOfMenu() {
        if(!inAdvert) {
            Mouse.setGrabbed(true);
            rocketControll = true;
        }
        inMenu = false;
    }

    public static void getInput() {

        boolean iMove = (Keyboard.isKeyDown(Keyboard.KEY_DOWN) || Keyboard.isKeyDown(Keyboard.KEY_LEFT) || Keyboard.isKeyDown(Keyboard.KEY_RIGHT) || Keyboard.isKeyDown(Keyboard.KEY_UP));
        boolean iMove2 = (Keyboard.isKeyDown(Keyboard.KEY_DOWN));
        boolean iShoot = Keyboard.isKeyDown(Keyboard.KEY_SPACE);


        if(Keyboard.isKeyDown(Keyboard.KEY_M)){
            mute = !mute;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_I)){
            player.immortal = true;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_P)){
            player.immortal = false;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_2)){
            player.weapon=2;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_3)){
            player.weapon=3;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_4)){
            player.weapon=4;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_5)){
            player.weapon=5;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_BACK)){
            inMenu();
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_RETURN)){
            outOfMenu();
        }

        if(Mouse.isButtonDown(0) && inAdvert) {
            if(Mouse.getX() >= Draw.advertX-10 - Draw.bigButtonSX/2 &&
                    Mouse.getX() <= Draw.advertX-10 + Draw.bigButtonSX/2 &&
                    Main.dHeight - Mouse.getY() >= Draw.advertY * 1.35f - Draw.bigButtonSY/2 &&
                    Main.dHeight - Mouse.getY() <= Draw.advertY * 1.35f + Draw.bigButtonSY/2)
                advert*=-1;
            if(advert == 8) {
                float my = Main.dHeight-Mouse.getY();
                if(my >= Draw.menuFirstButtonCoord && my <= Draw.menuFirstButtonCoord+Draw.buttonSizeY)
                    advert = 1;
                if(my >= Draw.menuFirstButtonCoord+2*1.55*Draw.buttonSizeY && my <= Draw.menuFirstButtonCoord+2*1.55*Draw.buttonSizeY+Draw.buttonSizeY)
                    scores();
                if(my >= Draw.menuFirstButtonCoord+3*1.55*Draw.buttonSizeY && my <= Draw.menuFirstButtonCoord+3*1.55*Draw.buttonSizeY+Draw.buttonSizeY)
                    exit();
            }
//            if(advert == -1) advert = 2;
//            if(advert == -2){
//                advert = 3;
//            }
//            if(advert == -3){
//            }
            switch(advert) {
                case -1: advert = 2;
                    break;
                case -2: advert = 3;
                    break;
                case -3:
                    inAdvert = false;
                    outOfMenu();
                    break;
                case -4:
                    inAdvert = false;
                    outOfMenu();
                    break;
                case -5:
                    inAdvert = false;
                    outOfMenu();
                    break;
                case -6:
                    inAdvert();
                    restartGame();
                    advert = 1;
                    break;
                case -7:
                    inAdvert();
                    restartGame();
                    advert = 8;
                    inMenu();
                    break;
                case -8: advert = 1;
                    inMenu();
                    break;
                case -9:
                    advert = level + 2;
                    inAdvert();
                    break;

            }
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(Mouse.isButtonDown(0) && inMenu) {

            if(Mouse.getX() >= 50*Main.em - Main.dWidth/4/2 && Mouse.getX() <= 50*Main.em + Main.dWidth/4/2) {
                float my = Main.dHeight-Mouse.getY();

                if(my >= Draw.menuFirstButtonCoord && my <= Draw.menuFirstButtonCoord+Draw.buttonSizeY)
                    outOfMenu();
                if(my >= Draw.menuFirstButtonCoord+1.55*Draw.buttonSizeY && my <= Draw.menuFirstButtonCoord+1.55*Draw.buttonSizeY+Draw.buttonSizeY)
                    restartGame();
                if(my >= Draw.menuFirstButtonCoord+2*1.55*Draw.buttonSizeY && my <= Draw.menuFirstButtonCoord+2*1.55*Draw.buttonSizeY+Draw.buttonSizeY)
                    scores();
                if(my >= Draw.menuFirstButtonCoord+3*1.55*Draw.buttonSizeY && my <= Draw.menuFirstButtonCoord+3*1.55*Draw.buttonSizeY+Draw.buttonSizeY)
                    exit();

            }
        }

        if(rocketControll) {


            Keyboard.enableRepeatEvents(true);
            if(Keyboard.isKeyDown(Keyboard.KEY_NUMPAD4)){
                player.moveNUM(-0.1f);
            }
            if(Keyboard.isKeyDown(Keyboard.KEY_NUMPAD6)){
                player.moveNUM(0.1f);
            }
            if(Keyboard.isKeyDown(Keyboard.KEY_LEFT) && !iShoot){
                if(iMove2)
                    player.rotate((float)-10);
                else
                    player.rotate((float)10);
            }
            if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT) && !iShoot){
                if(iMove2)
                    player.rotate((float)10);
                else
                    player.rotate((float)-10);
            }
            if(Keyboard.isKeyDown(Keyboard.KEY_LEFT) && iShoot){
                if(iMove2)
                    player.rotate((float)-5);
                else
                    player.rotate((float)5);
            }
            if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT) && iShoot){
                if(iMove2)
                    player.rotate((float)5);
                else
                    player.rotate((float)-5);
            }

            if(Keyboard.isKeyDown(Keyboard.KEY_UP)){
                player.move(1);
            }
            if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
                player.move(-1);
            }

            if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_Q)){
                player.maxspeed = 6;
            }
            else player.maxspeed = 1.2f;
        }



    }


    public static void update() {




        curTime = new Date().getTime() - Game.beginTime.getTime();
        if(levelTime[level-1]*1000 - curTime < 0) {
            inAdvert();
            beginTime = new Date();
            if(GOPlayer.getScore() >= levelScore[level-1]) {
                if(level == 3) {
                    win();
                }
                if(level < 3) {
                    level++;
                    advert = 9;
                }
            }
            else lose();



        }


        if(inMenu) return;
        if(inAdvert) return;

        //FRINGE
        nearFringe = false;
        if(player.x - Draw.xshift >= 100 - distanceToFringe){                          // ->
            Draw.xshift += Math.pow( (1 - (Draw.xshift + 100 - player.x) / (distanceToFringe-0)) * (player.maxspeed+0.5)*2, 2);
            nearFringe = true;
        }
        if(player.x - Draw.xshift <= distanceToFringe){                                // <-
            Draw.xshift -= Math.pow( Math.abs(1 - (Math.abs(Draw.xshift - 0 - player.x) / (distanceToFringe-0))) * (player.maxspeed+0.5)*2, 2);
            nearFringe = true;
        }
        if(player.y - Draw.yshift <= distanceToFringe * Main.ratio){                   // v
            Draw.yshift -= Math.pow( (1 - (Math.abs(Draw.yshift + 0* Main.ratio - player.y) / ((distanceToFringe-0) * Main.ratio))) * (player.maxspeed+0.5)*2, 2);
            nearFringe = true;
        }
        if(player.y - Draw.yshift >= (100 - distanceToFringe) * Main.ratio){           // ^
            Draw.yshift += Math.pow( (1 - ((Draw.yshift + 100 * Main.ratio - player.y) / ((distanceToFringe-0) * Main.ratio))) * (player.maxspeed+0.5)*2, 2);
            nearFringe = true;
        }

        if(nearFringe && distanceToFringe < maxDtf) {
            distanceToFringe++;
        }
        if(!nearFringe && distanceToFringe > minDtf) {
            distanceToFringe-=5;
        }


        if(new Date().getTime() - curTime >= 100) {
            int tempAmountHardGraph = 0;
            for(GO ob : fon) {
                if(ob.getClass().getSimpleName() == "GOBurst")
                    tempAmountHardGraph++;
            }

            for(GO ob : fon) {
                if(ob.getClass().getSimpleName() == "GOBurst") {
                    fon.remove(ob);
                    ob.destroy();
                    tempAmountHardGraph--;
                    if(tempAmountHardGraph == 0)
                        break;
                }
            }

        }




        create();


        if(!newObj.isEmpty()) {
            objects.addAll(newObj);
            newObj.clear();
        }

        changeObjects = false;
        for(GO ob : objects) {
            glClear(GL_COLOR_BUFFER_BIT);
            ob.update(objects);
            if(changeObjects == true) break;
        }
        for(GO ob : fon) {
            glClear(GL_COLOR_BUFFER_BIT);
            ob.update(objects);
            if(changeObjects == true) break;
        }



        if(player.hitPoints <= 0) {
            lose();
        }
    }


    public static void lose() {
        advert = 6;
        inAdvert();
    }

    private static void win() {
        advert = 7;
        inAdvert();
    }

    private static void create() {
        if(Math.random()*1000 <= (Main.delay)*15) {

            float randomizer = (float) Math.random()*100;

            xshiftT = Draw.xshift;
            yshiftT = Draw.yshift;
            float r1 = (float) (2+ Math.random()*3);
            float r2 = r1;
            DrawFigure figure = DrawFigure.METEOR_1;

            float a = (float) (Math.random() * 360);
            float b = (float) (Math.random() * 3);
            float c = (float) (0.4f + Math.random() * 0.2f);



            if(Math.random() >= 0.5) {
                if(Math.random() >= 0.5) {
                   GORandomizer(
                            figure,
                            (float) (100 + 5) + xshiftT,
                            (float) (Math.random() * 100 * Main.ratio + yshiftT),
                            (int) (r1),
                            (int) (r2),
                            a,
                            b,
                            c
                    );
                }
                else {
                    float mmm = (float) Math.random();
//                    System.out.println(mmm* 100*Main.ratio + yshift);
                    GORandomizer(
                            figure,
                            (float) (-5 + xshiftT),
                            (float) (mmm* 100*Main.ratio + yshiftT),
                            (int)(r1),
                            (int)(r2),
                            a,
                            b,
                            c
                    );
                }
            }
            if(Math.random() < 0.5) {
                if(Math.random() >= 0.5) {
                    GORandomizer(figure, (float) (Math.random()* 100 + xshiftT), 100*Main.ratio+5 + yshiftT, (int)(r1), (int)(r2), a, b, c);
                }
                else {
                    GORandomizer(figure, (float) (Math.random()* 100 + xshiftT),  -5 + yshiftT, (int)(r1), (int)(r2), a, b, c);
                }
            }
        }



    }

    private static void GORandomizer(DrawFigure figure, float tx, float ty, float tsx, float tsy, float a, float b, float c) {
        float randomizer = (float) Math.random()*100;
       // tx = (float) (Math.random()* 100 + Draw.xshift);
        //ty = 30 + Draw.yshift;
//        objects.add(new GOMeteor(figure, tx, ty, tsx, tsy, a, b, c));
        if(randomizer <= GOMeteor.probabilityAppearance[getLevel()-1]) {
            objects.add(new GOMeteor(figure, tx, ty, tsx, tsy, a, b, c));
            System.out.print("N Create!");
        }
        else if(randomizer <= GOMine.probabilityAppearance[getLevel()-1]) {
            objects.add(new GOMine(figure, tx, ty, tsx, tsy, a, b, 0.1f));
            System.out.print("N Create!");
        }
        else if(randomizer <= GORocketEnemy.probabilityAppearance[getLevel()-1]) {
            objects.add(new GORocketEnemy(figure, tx, ty, tsx, tsy, a, b, c));
            System.out.print("N Create!");
        }
        System.out.println("\t\tCREATE:" + tx +" : "+ty);




    }

    public static void render() {



        Draw.draw(DrawFigure.FON);
        for(GO ob : fon) {
            ob.render();
        }
        for(GO ob : objects) {
            ob.render();
        }
        Draw.drawInterface(player);


        if(advert > 0) {
            Draw.instruction(advert);
        }
        if(inMenu || inAdvert) {
            beginTime.setTime(beginTime.getTime() + Main.delay);
            if(inMenu)
                Draw.menuInterface();
        }
    }

    public static void delGO(GO obDel) {
        delGO(obDel, null);
    }
    public static void delGO(GO obDel, GO animated) {
        delGO(obDel, animated, 2);
    }
    public static void delGO(GO obDel, GO animated, int burstSize) {
        if(animated != null) {
            if(animated.getX()-Draw.xshift >= 0 && animated.getX()-Draw.xshift <= 100 && animated.getY()-Draw.yshift >= 0 && animated.getY()-Draw.yshift <= 100*Main.ratio) {
                if(burstAmount <= 5)
                    fon.add(new GOBurst(animated.getX(), animated.getY(), animated.getSX(), animated.getSY(), burstSize, false));
                if(burstAmount <= 20)
                    fon.add(new GOBurst(animated.getX(), animated.getY(), animated.getSX(), animated.getSY(), burstSize, true));
            }
        }
        if(obDel == null) return;
        numDestroyObj++;
        GO ob;
        Iterator<GO> iter = objects.iterator();
        while(iter.hasNext()) {
            ob = iter.next();
            if(ob.equals(obDel)) {
                changeObjects = true;
                iter.remove();
                ob.destroy();
            }
        }
        iter = fon.iterator();
        while(iter.hasNext()) {
            ob = iter.next();
            if(ob.equals(obDel)) {
                changeObjects = true;
                iter.remove();
                ob.destroy();
            }
        }

    }

    private static void restartGame() {
        clear();
    }
    private static void scores() {

    }
    private static void exit() {
        Main.cleanUp();
    }


    public static void addObj(GO ob) {
        newObj.add(ob);
    }
    public static int getObjectsLength() {
        return objects.size();
    }
    public static int getNumDestroyObj() {
        return numDestroyObj;
    }
    public static boolean isMute() {
        return mute;
    }

    public static int getLevel() {
        return level;
    }
}
