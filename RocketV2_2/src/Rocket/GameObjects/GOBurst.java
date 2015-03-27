package Rocket.GameObjects;

import Rocket.Draw;
import Rocket.DrawFigure;
import Rocket.GO;
import Rocket.Game;

import java.util.ArrayList;

public class GOBurst extends GO {


    private static final int TIME_SCALE = 100;
    private static final int FRAME_NUM = 17* TIME_SCALE;
    private static final float SPEED_ANIM = 50;
    private static final float TIME_LIMITED_ANIM = 100;
    private static boolean isLimited = false;
    private float burstSize = 2;

    private int curFrame = 0;
    public boolean animated = true;



    public GOBurst(float x, float y, float sx, float sy, float burstSize, boolean isLimited) {
        this(x, y, sx, sy);
        this.isLimited = isLimited;
        this.burstSize = burstSize;
        Game.burstAmount++;
    }
    public GOBurst(float x, float y, float sx, float sy) {
        this.x = x;
        this.y = y;
        this.sx = sx;
        this.sy = sy;
        Game.burstAmount++;
    }

    @Override
    public void update(ArrayList<GO> objects) {
        if(curFrame >= FRAME_NUM || curFrame >= TIME_LIMITED_ANIM && isLimited) {
            Game.burstAmount--;
            Game.delGO(this);
        }
        else {
            curFrame+= SPEED_ANIM;
        }

        if(!Draw.burstSound.isPlaying() && !Game.isMute()) {
            //Draw.burstSound.playAsMusic(1.0f, 1.0f, true);
        }

//
//        if(!Game.isMute())
//            SoundStore.get().poll(0);
    }

    @Override
    public void render() {
        if(curFrame < FRAME_NUM)
            Draw.draw(DrawFigure.DESTROY, x+sx*burstSize/4, y+sy*burstSize/8, burstSize *sy, burstSize *sy, rotate, (int)curFrame/ TIME_SCALE);
    }
}
