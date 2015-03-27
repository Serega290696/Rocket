package Rocket;

import Rocket.GameObjects.GOPlayer;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glPopMatrix;

                     /*
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;


import java.awt.*;
import java.io.InputStream;   */


public class Draw {

    public static float xshift = 0;
    public static float yshift = 0;
    private static final float Pi = 180f;
    private static Texture textureRocket;
    private static Texture textureMeteor;
    private static Texture textureStar;
    private static Texture textureShotLvl1;
    private static Texture textureShotLvl2;
    private static Texture textureShotLvl3;
    private static Texture textureShotLvl4;
    private static Texture textureShotLvl5;
    private static Texture textureFon1;
    private static Texture textureFon2;
    private static Texture textureFon3;
    private static Texture textureShatl1;
    private static Texture textureShatl2;
    private static Texture textureShatl3;
    private static Texture textureInterfaceTable1;
    private static Texture textureInterfaceTable2;
    private static Texture textureAuraProtection;
    private static Texture textureRocketAuraOff;
    private static Texture textureRocketAura;
    private static Texture textureButton1;
    private static Texture textureButton1_2;
    private static Texture textureButton2;
    private static Texture textureButton2_2;
    private static Texture textureButton3;
    private static Texture textureButton4;
    private static Texture textureButtonH1;
    private static Texture textureButtonH1_2;
    private static Texture textureButtonH2;
    private static Texture textureButtonH2_2;
    private static Texture textureButtonH3;
    private static Texture textureButtonH4;
    private static Texture mine;
    private static Texture rocketEnemy;
    private static Texture advert;
    private static Texture bigButton;
    private static Texture bigButtonPush;

    private static Texture textureBurst[] = new Texture[17];
    private static int curFrame = 1;
    //private static int animation[] = new int[animationNum];
    //private static Map<String, Integer, Integer> animation;

    private static boolean antiAlias = true;
    static Font awtFont1 = new Font("Times New Roman", Font.BOLD, 16);
    static Font awtFont2 = new Font("Times New Roman", Font.BOLD, 34);
    static Font dsCrystal = new Font("DS Crystal", Font.BOLD, 76);
    private static TrueTypeFont font1_1 = new TrueTypeFont(awtFont1, antiAlias);
    private static TrueTypeFont font1_2 = new TrueTypeFont(awtFont2, antiAlias);
    private static TrueTypeFont font2 = new TrueTypeFont(awtFont2, antiAlias);
    private static TrueTypeFont font3 = new TrueTypeFont(dsCrystal, antiAlias);
    private static final String CONTENT_PATH = "content/";
    private static final String TEXTURE_PATH = CONTENT_PATH+"images/";
    private static final String SOUND_PATH = CONTENT_PATH+"sound/";
    private static final String FONTS_PATH = CONTENT_PATH+"fonts/";
    public static Audio burstSound;

    public static final float menuFirstButtonCoord = 300;
    public static final float buttonSizeX = Main.dWidth/4;
    public static final float buttonSizeY = Main.dHeight/15;
    public static final float advertSX = Main.dWidth/1.5f;
    public static final float advertSY = Main.dHeight/1.4f;
    public static final float advertX = (Main.dWidth)/2+10;
    public static final float advertY = (Main.dHeight)/2*1.1f;
    public static final float bigButtonSX = advertSX/2.5f;
    public static final float bigButtonSY = advertSY/7;

//    public static long curTime;

    public static void draw(DrawFigure figure, float x, float y, float sx, float sy, float rotate) {

        glDisable(GL_TEXTURE_2D);


        x -= xshift;
        y -= yshift;
        x *= Main.em;
        y *= Main.em;

        sx *= Main.em;
        sy *= Main.em;

        switch(figure) {
            case RECTANGLE: rect(x, y, sx, sy, rotate);
                break;
            case ROCKET_1: rocketType1(x, y, sx, sy, rotate);
                break;
            case ROCKET_2: rocketType2(x, y, sx, sy, rotate);
                break;
            case ROCKET_3: rocketType3(x, y, sx, sy, rotate);
                break;
            case STAR_1: star(x, y, sx, sy, rotate);
                break;
            case STAR_2: star2(x, y, sx, sy, rotate);
                break;
            case SHOT_1:
            case SHOT_2:
            case SHOT_3:
            case SHOT_4:
            case SHOT_5:
                shot(x, y, sx, sy, rotate, figure);
                break;
            case METEOR_1: meteor1(x, y, sx, sy, rotate);
                break;
            case METEOR_2: meteor2(x, y, sx, sy, rotate);
                break;
            case MINE: mine(x, y, sx, sy, rotate);
                break;
            case ROCKET_ENEMY: rocketEnemy(x, y, sx, sy, rotate);
                break;
            case DESTROY: destroy(x, y, sx, sy, rotate);
                break;
            case FON: fon(Main.dWidth*1.75f, Main.dHeight*1.25f);
                break;
            case VOID:
                break;
            default:
                break;
        }
    }


    public static void draw(DrawFigure figure) {
        draw(figure, 0, 0, 0, 0, 0);
    }
    public static void draw(DrawFigure figure, float x, float y, float sx, float sy) {
        draw(figure, x, y, sx, sy, 0);
    }

    public static void draw(DrawFigure figure, float x, float y, float sx, float sy, float rotate, int curFrame) {
        draw(figure, x, y, sx, sy, rotate);
        Draw.curFrame = curFrame;
    }

    public static void rocketType1(float x, float y, float sx, float sy, float rotate) {
        glPushMatrix();
        {
            //glColor3f(0.3f,0.7f,1.0f);
            glTranslatef(x, y, 0);
            glRotatef(-rotate, 0, 0, 1);
            glBegin(GL_POLYGON);
            {
                glColor3f((float)1/255*26,(float)1/255*164,(float)1/255*222);
                glVertex2f(0, sy / 2);
                glVertex2f(0, -sy/4);
                glColor3f((float) 1 / 255 * 1, (float) 1 / 255 * 21, (float) 1 / 255 * 36);
                glVertex2f(-sx / 2, -sy / 2);
                glColor3f((float) 1 / 255 * 26, (float) 1 / 255 * 164, (float) 1 / 255 * 222);
                glVertex2f(0,sy/2);
            }
            glEnd();
            glBegin(GL_POLYGON);
            {
                glColor3f((float) 1 / 255 * 26, (float) 1 / 255 * 164, (float) 1 / 255 * 222);
                glVertex2f(0, sy / 2);
                glColor3f((float) 1 / 255 * 1, (float) 1 / 255 * 21, (float) 1 / 255 * 36);
                glVertex2f(0, -sy / 4);
                glVertex2f(sx/2, -sy/2);
                glColor3f((float) 1 / 255 * 26, (float) 1 / 255 * 164, (float) 1 / 255 * 222);
                glVertex2f(0,sy/2);
            }
            glEnd();
        }
        glPopMatrix();
        if(GOPlayer.getAura())
            rocketAura(x, y, sx, sy);
    }
    public static void rocketType2(float x, float y, float sx, float sy, float rotate) {
        glPushMatrix();
        {
            //glEnable(GL_TEXTURE_2D);
            glTranslatef(x, y, 0);
            glRotatef(-rotate, 0, 0, 1);

            Color.white.bind();
            textureRocket.bind();
            glBegin(GL_QUADS);
            {
                glTexCoord2f(0, 0);
                glVertex2f(-sx / 2, -sy / 2);
                glTexCoord2f(0, 1);
                glVertex2f(-sx / 2, sy / 2);
                glTexCoord2f(1, 1);
                glVertex2f(sx/2, sy/2);
                glTexCoord2f(1, 0);
                glVertex2f(sx/2,-sy/2);
            }
            glEnd();
        }
        glPopMatrix();
        if(GOPlayer.getAura())
            rocketAura(x, y, sx, sy);
    }
    public static void rocketType3(float x, float y, float sx, float sy, float rotate) {
        glPushMatrix();
        {
            //glEnable(GL_TEXTURE_2D);
            sy *= 8;
            sx *= 1;
            glTranslatef(x, y, -36.0f);
            glRotatef(-rotate, 0, 0, 1);
            glRotatef(rotate, 0, 1, 0);
            glDisable(GL_TEXTURE_2D);

            Color.white.bind();
            glDisable(GL_TEXTURE_2D);
            glBegin(GL_QUADS);
            {
                glColor3f(0.0f, 1.0f, 0.0f);                    //ВЕРХ
                glVertex3f(sx / 2, sy / 2, -sx / 2);          // Право верх квадрата ер  
                glVertex3f(-sx / 2, sy / 2, -sx / 2);          // Лево верх
                glVertex3f(-sx / 2, sy / 2, sx / 2);          // Лево низ
                glVertex3f( sx/2, sy/2, sx/2);
            }
            glEnd();

            glBegin(GL_QUADS);
            {
                glColor3f(1.0f, 0.5f, 0.0f);             //НИЗ
                glVertex3f( sx/2,-sy/2, sx/2);          // Верх право квадрата (Низ)
                glVertex3f(-sx/2,-sy/2, sx/2);          // Верх лево
                glVertex3f(-sx/2,-sy/2,-sx/2);          // Низ лево
                glVertex3f(sx / 2, -sy / 2, -sx / 2);
            }
            glEnd();

            glBegin(GL_QUADS);
            {
                glColor3f(1.0f,1.0f,0.0f);               //ЗАД
                glVertex3f( sx/2,-sy/2,-sx/2);          // Верх право квадрата (Зад)
                glVertex3f(-sx/2,-sy/2,-sx/2);          // Верх лево
                glVertex3f(-sx/2, sy/2,-sx/2);          // Низ лево
                glVertex3f(sx / 2, sy / 2, -sx / 2);
            }
            glEnd();

            glEnable(GL_TEXTURE_2D);

            Color.white.bind();
            textureShatl3.bind();
            glBegin(GL_QUADS);
            {
                glColor3f(1.0f,1.0f,1.0f);               //Крылья
                glTexCoord2f(0, 0);
                glVertex3f(-sx, sy/5, 0);
                glTexCoord2f(1, 0);
                glVertex3f(-sx, -sy/5, 0);
                glTexCoord2f(1, 1);
                glVertex3f( sx, -sy/5, 0);
                glTexCoord2f(0, 1);
                glVertex3f( sx, sy/5, 0);
            }
            glEnd();



            if((rotate % 360 <= 180 && rotate % 360 >= 0) || (rotate % 360 <= -180 && rotate % 360 >= -360))
            {
                Color.white.bind();
            textureShatl2.bind();
            glBegin(GL_QUADS);
            {
                //glColor3f(0.0f,0.0f,1.0f);              //ЛЕВО
                glTexCoord2f(0, 0);
                glVertex3f(-sx/2, sy/2, sx/2);          // Верх право квадрата (Лево)
                glTexCoord2f(0, 1);
                glVertex3f(-sx/2, sy/2,-sx/2);          // Верх лево
                glTexCoord2f(1, 1);
                glVertex3f(-sx,-sy/2,-sx/2);          // Низ лево
                glTexCoord2f(1, 0);
                glVertex3f(-sx , -sy / 2, sx / 2);
            }
            glEnd();
            Color.white.bind();
            textureShatl2.bind();
            glBegin(GL_QUADS);
            {
                //glColor3f(1.0f,0.0f,1.0f);               //ПРАВО
                glTexCoord2f(0, 1);
                glVertex3f(sx / 2, sy / 2, -sx / 2);          // Верх право квадрата (Право)
                glTexCoord2f(0, 0);
                glVertex3f(sx / 2, sy / 2, sx / 2);          // Верх лево
                glTexCoord2f(1, 0);
                glVertex3f(sx / 2, -sy / 2, sx / 2);          // Низ лево
                glTexCoord2f(1, 1);
                glVertex3f( sx/2,-sy/2,-sx/2);
            }
            glEnd();
            }
            else {
            Color.white.bind();
            textureShatl2.bind();
            glBegin(GL_QUADS);
            {
                //glColor3f(1.0f,0.0f,1.0f);               //ПРАВО
                glTexCoord2f(0, 1);
                glVertex3f(sx / 2, sy / 2, -sx / 2);          // Верх право квадрата (Право)
                glTexCoord2f(0, 0);
                glVertex3f(sx / 2, sy / 2, sx / 2);          // Верх лево
                glTexCoord2f(1, 0);
                glVertex3f(sx / 2, -sy / 2, sx / 2);          // Низ лево
                glTexCoord2f(1, 1);
                glVertex3f( sx/2,-sy/2,-sx/2);
            }
            glEnd();
                Color.white.bind();
                textureShatl2.bind();
                glBegin(GL_QUADS);
                {
                    //glColor3f(0.0f,0.0f,1.0f);              //ЛЕВО
                    glTexCoord2f(0, 0);
                    glVertex3f(-sx/2, sy/2, sx/2);          // Верх право квадрата (Лево)
                    glTexCoord2f(0, 1);
                    glVertex3f(-sx/2, sy/2,-sx/2);          // Верх лево
                    glTexCoord2f(1, 1);
                    glVertex3f(-sx,-sy/2,-sx/2);          // Низ лево
                    glTexCoord2f(1, 0);
                    glVertex3f(-sx , -sy / 2, sx / 2);
                }
                glEnd();
            }
            Color.white.bind();
            textureShatl1.bind();
            sx*=1;
            glBegin(GL_QUADS);
            {
                //glColor3f(1.0f,0.0f,0.0f);                    //ПЕРЕД
                glTexCoord2f(0, 1);
                glVertex3f(sx, sy / 2, sx / 2);          // Верх право квадрата (Перед)
                glTexCoord2f(0, 0);
                glVertex3f(-sx/1.5f, sy / 2, sx / 2);          // Верх лево
                glTexCoord2f(1, 0);
                glVertex3f(-sx/1.5f, -sy / 2, sx / 2);          // Низ лево
                glTexCoord2f(1, 1);
                glVertex3f(sx, -sy / 2, sx / 2);
            }
            glEnd();
            sx/=1;
            System.out.println(rotate);
            System.out.println((rotate % 360 <= 180 && rotate % 360 >= 0) || (rotate % 360 >= -180 && rotate % 360 <= 360));

        }
        glPopMatrix();
        if(GOPlayer.getAura())
            rocketAura(x, y, sx, sy);
    }
    public static void rocketAura(float x, float y, float sx, float sy) {
        glPushMatrix();
        {
            sx *= 4;
            sy *= 3;
            glEnable(GL_TEXTURE_2D);
            glTranslatef(x+12, y+10, 0);

            Color.white.bind();
            textureRocketAura.bind();
            glBegin(GL_QUADS);
            {
                glTexCoord2f(0, 0);
                glVertex2f(-sx / 2, -sy / 2);
                glTexCoord2f(0, 1);
                glVertex2f(-sx / 2, sy / 2);
                glTexCoord2f(1, 1);
                glVertex2f(sx/2, sy/2);
                glTexCoord2f(1, 0);
                glVertex2f(sx/2,-sy/2);
            }
            glEnd();
        }
        glPopMatrix();
    }

    public static void rect(float x, float y, float sx, float sy, float rotate) {
        glPushMatrix();
        {
            glColor3f(0.5f,0.5f,0.5f);
            glTranslatef(x, y, 0);
            glRotatef(-rotate, 0, 0, 1);
            glBegin(GL_POLYGON);
            {
                glVertex2f(-sx / 2, -sy / 2);
                glVertex2f(-sx/2, sy/2);
                glVertex2f(sx / 2, sy / 2);
                glVertex2f(sx/2,-sy/2);
            }
            glEnd();
        }
        glPopMatrix();
    }
    public static void shot(float x, float y, float sx, float sy, float rotate, DrawFigure weaponLevel) {
        glPushMatrix();
        {
            glEnable(GL_TEXTURE_2D);
            glTranslatef(x, y, 0);
            glRotatef(-rotate, 0, 0, 1);

            Color.white.bind();
            switch(weaponLevel) {
                case SHOT_1:
                    textureShotLvl1.bind();
                    break;
                case SHOT_2:
                    textureShotLvl2.bind();
                    break;
                case SHOT_3:
                    textureShotLvl3.bind();
                    break;
                case SHOT_4:
                    sx *= 2;
                    sy *= 1.2;
                    textureShotLvl4.bind();
                    break;
                case SHOT_5:
                    sx *= 6;
                    sy *= 1.4;
                    textureShotLvl5.bind();
                    break;
            }
            System.out.println(weaponLevel);
            glBegin(GL_QUADS);
            {
                glTexCoord2f(0, 0);
                glVertex2f(-sx/2, -sy/2);
                glTexCoord2f(0, 1);
                glVertex2f(-sx/2, sy/2);
                glTexCoord2f(1, 1);
                glVertex2f(sx/2, sy/2);
                glTexCoord2f(1, 0);
                glVertex2f(sx/2,-sy/2);
            }
            glEnd();
        }
        glPopMatrix();
    }


    private static void destroy(float x, float y, float sx, float sy, float rotate) {
        glPushMatrix();
        {
            glEnable(GL_TEXTURE_2D);
            glTranslatef(x, y, 0);
            glRotatef(-rotate, 0, 0, 1);

            Color.white.bind();
            textureBurst[curFrame].bind(); // or GL11.glBind(texture.getTextureID());
            glBegin(GL_QUADS);
            {
                glTexCoord2f(0, 0);
                glVertex2f(-sx/2, -sy/2);
                glTexCoord2f(0, 1);
                glVertex2f(-sx/2, sy/2);
                glTexCoord2f(1, 1);
                glVertex2f(sx/2, sy/2);
                glTexCoord2f(1, 0);
                glVertex2f(sx/2,-sy/2);
            }
            glEnd();
        }
        glPopMatrix();
    }
    private static void star(float x, float y, float sx, float sy, float rotate) {
        glPushMatrix();
        {
            glEnable(GL_TEXTURE_2D);
            glTranslatef(x, y, 0);
            glRotatef(-rotate, 0, 0, 1);

            Color.white.bind();
            textureStar.bind(); // or GL11.glBind(texture.getTextureID());
            glBegin(GL_QUADS);
            {
                glTexCoord2f(0, 0);
                glVertex2f(-sx/2, -sy/2);
                glTexCoord2f(0, 1);
                glVertex2f(-sx/2, sy/2);
                glTexCoord2f(1, 1);
                glVertex2f(sx/2, sy/2);
                glTexCoord2f(1, 0);
                glVertex2f(sx/2,-sy/2);
            }
            glEnd();
        }
        glPopMatrix();
    }
    private static void star2(float x, float y, float sx, float sy, float rotate) {
        glPushMatrix();
        {
            glTranslatef(x, y, 0);
            glRotatef(-rotate, 0, 0, 1);

            Color.white.bind();
            glBegin(GL_QUADS);
            {
                glVertex2f(-sx / 2, -sy / 2);
                glVertex2f(-sx/2, sy/2);
                glVertex2f(sx / 2, sy / 2);
                glVertex2f(sx/2,-sy/2);
            }
            glEnd();
        }
        glPopMatrix();
    }
    private static void meteor1(float x, float y, float sx, float sy, float rotate) {
        glPushMatrix();
        {
            glEnable(GL_TEXTURE_2D);
            glTranslatef(x, y, 0);
            glRotatef(-rotate, 0, 0, 1);

            Color.white.bind();
            textureMeteor.bind(); // or GL11.glBind(texture.getTextureID());
            glBegin(GL_QUADS);
            {
                glTexCoord2f(0, 0);
                glVertex2f(-sx/2, -sy/2);
                glTexCoord2f(0, 1);
                glVertex2f(-sx/2, sy/2);
                glTexCoord2f(1, 1);
                glVertex2f(sx / 2, sy / 2);
                glTexCoord2f(1, 0);
                glVertex2f(sx/2,-sy/2);
            }
            glEnd();
        }
        glPopMatrix();
    }
    private static void meteor2(float x, float y, float sx, float sy, float rotate) {
        glPushMatrix();
        {
            glTranslatef(x, y, 0);
            glRotatef(-rotate, 0, 0, 1);

            Color.white.bind();
            glBegin(GL_QUADS);
            {
                glVertex2f(-sx / 2, -sy / 2);
                glVertex2f(-sx / 2, sy / 2);
                glVertex2f(sx / 2, sy / 2);
                glVertex2f(sx/2,-sy/2);
            }
            glEnd();
        }
        glPopMatrix();
    }
    private static void mine(float x, float y, float sx, float sy, float rotate) {
        glPushMatrix();
        {
            glEnable(GL_TEXTURE_2D);
            glTranslatef(x, y, 0);
            glRotatef(-rotate, 0, 0, 1);

            Color.white.bind();
            mine.bind(); // or GL11.glBind(texture.getTextureID());
            glBegin(GL_QUADS);
            {
                glTexCoord2f(0, 0);
                glVertex2f(-sx/2, -sy/2);
                glTexCoord2f(0, 1);
                glVertex2f(-sx/2, sy/2);
                glTexCoord2f(1, 1);
                glVertex2f(sx / 2, sy / 2);
                glTexCoord2f(1, 0);
                glVertex2f(sx/2,-sy/2);
            }
            glEnd();
        }
        glPopMatrix();
    }
    private static void rocketEnemy(float x, float y, float sx, float sy, float rotate) {
        glPushMatrix();
        {
            glEnable(GL_TEXTURE_2D);
            glTranslatef(x, y, 0);
            glRotatef(-rotate, 0, 0, 1);

            rocketEnemy.bind(); // or GL11.glBind(texture.getTextureID());
            glBegin(GL_QUADS);
            {
                glTexCoord2f(0, 0);
                glVertex2f(-sx/2, -sy/2);
                glTexCoord2f(0, 1);
                glVertex2f(-sx/2, sy/2);
                glTexCoord2f(1, 1);
                glVertex2f(sx / 2, sy / 2);
                glTexCoord2f(1, 0);
                glVertex2f(sx/2,-sy/2);
            }
            glEnd();
        }
        glPopMatrix();
    }



    private static void fon(float sx, float sy) {
        glPushMatrix();
        {
            glEnable(GL_TEXTURE_2D);
            glTranslatef(0, 0, 0);
            glRotatef(0, 0, 0, 1);

            Color.white.bind();
            if(Game.getLevel() == 1)
                textureFon1.bind();
            if(Game.getLevel() == 2)
                textureFon2.bind();
            if(Game.getLevel() == 3)
                textureFon3.bind();

            glBegin(GL_QUADS);
            {
                glTexCoord2f(0, 0);
                glVertex2f(0, 0);
                glTexCoord2f(0,1);
                glVertex2f(0, sy);
                glTexCoord2f(1, 1);
                glVertex2f(sx, sy);
                glTexCoord2f(1, 0);
                glVertex2f(sx,0);
            }
            glEnd();
        }
        glPopMatrix();
    }

    public static void drawInterface(GOPlayer player) {

        float sx = 220;
        float sy = (float) (sx * 0.65);

        Color.white.bind();

        font2.drawString(10*Main.em, 50, String.valueOf(Math.round(player.getX())) + ":" + String.valueOf(Math.round(player.getY())), Color.yellow);
        font2.drawString(20*Main.em, 50, "HP: " + String.valueOf(player.getHP()), Color.yellow);
        font2.drawString(30*Main.em, 50, "Kills: " + String.valueOf(player.getScore()), Color.yellow);
        font2.drawString(80*Main.em, 50, String.valueOf(Game.curTime/60000) + ":" + String.valueOf(Game.curTime%60000/1000), Color.yellow);
        font2.drawString(90*Main.em, 70*Main.em*Main.ratio, String.valueOf(Game.getObjectsLength()), Color.yellow);
        font2.drawString(90*Main.em, 80*Main.em*Main.ratio, String.valueOf(Game.getNumDestroyObj()), Color.yellow);
        font2.drawString(90*Main.em, 90*Main.em*Main.ratio, String.valueOf(Game.getNumDestroyObj()+Game.getObjectsLength()), Color.yellow);


        if(Game.isMute())
            font2.drawString(0, 90*Main.em*Main.ratio, "M", Color.yellow);



        sx *= 1.3;
        sy *= 2;

        glPushMatrix();
        {
            glEnable(GL_TEXTURE_2D);
            glTranslatef((float) (50* Main.em*1.07), 0, 0);
            glRotatef(0, 0, 0, 1);
            Color.white.bind();
            textureInterfaceTable1.bind(); // or GL11.glBind(texture.getTextureID());
            glBegin(GL_QUADS);
            {
                glTexCoord2f(0, 0);
                glVertex2f(-sx/2, 0);
                glTexCoord2f(0,1);
                glVertex2f(-sx/2, sy);
                glTexCoord2f(1, 1);
                glVertex2f(sx/2, sy);
                glTexCoord2f(1, 0);
                glVertex2f(sx/2,0);
            }
            glEnd();
        }
        glPopMatrix();


        sx *= 0.5;
        sy *= 0.5;


        glPushMatrix();
        {
            glEnable(GL_TEXTURE_2D);
            glTranslatef((float) (15* Main.em*1.07), 0, 0);
            glRotatef(0, 0, 0, 1);
            Color.white.bind();
            textureInterfaceTable2.bind(); // or GL11.glBind(texture.getTextureID());
            glBegin(GL_QUADS);
            {
                glTexCoord2f(0, 0);
                glVertex2f(-sx/2, 0);
                glTexCoord2f(0,1);
                glVertex2f(-sx/2, sy);
                glTexCoord2f(1, 1);
                glVertex2f(sx/2, sy);
                glTexCoord2f(1, 0);
                glVertex2f(sx/2,0);
            }
            glEnd();
        }
        glPopMatrix();
        glPushMatrix();
        {
            glEnable(GL_TEXTURE_2D);
            glTranslatef((float) (30* Main.em*1.07), 0, 0);
            glRotatef(0, 0, 0, 1);
            Color.white.bind();
            textureInterfaceTable2.bind(); // or GL11.glBind(texture.getTextureID());
            glBegin(GL_QUADS);
            {
                glTexCoord2f(0, 0);
                glVertex2f(-sx/2, 0);
                glTexCoord2f(0,1);
                glVertex2f(-sx/2, sy);
                glTexCoord2f(1, 1);
                glVertex2f(sx/2, sy);
                glTexCoord2f(1, 0);
                glVertex2f(sx/2,0);
            }
            glEnd();
        }
        glPopMatrix();
        glPushMatrix();
        {
            glEnable(GL_TEXTURE_2D);
            glTranslatef((float) ((100-15)* Main.em*1.07), 0, 0);
            glRotatef(0, 0, 0, 1);
            Color.white.bind();
            textureInterfaceTable2.bind(); // or GL11.glBind(texture.getTextureID());
            glBegin(GL_QUADS);
            {
                glTexCoord2f(0, 0);
                glVertex2f(-sx/2, 0);
                glTexCoord2f(0,1);
                glVertex2f(-sx/2, sy);
                glTexCoord2f(1, 1);
                glVertex2f(sx/2, sy);
                glTexCoord2f(1, 0);
                glVertex2f(sx/2,0);
            }
            glEnd();
        }
        glPopMatrix();
        glPushMatrix();
        {
            glEnable(GL_TEXTURE_2D);
            glTranslatef((float) ((100-30)* Main.em*1.07), 0, 0);
            glRotatef(0, 0, 0, 1);
            Color.white.bind();
            textureInterfaceTable2.bind(); // or GL11.glBind(texture.getTextureID());
            glBegin(GL_QUADS);
            {
                glTexCoord2f(0, 0);
                glVertex2f(-sx/2, 0);
                glTexCoord2f(0,1);
                glVertex2f(-sx/2, sy);
                glTexCoord2f(1, 1);
                glVertex2f(sx/2, sy);
                glTexCoord2f(1, 0);
                glVertex2f(sx/2,0);
            }
            glEnd();
        }
        glPopMatrix();

        sx *= 0.5;
        sy *= 0.5;
            glPushMatrix();
            {
                glEnable(GL_TEXTURE_2D);
                glTranslatef((float) ((100-15)* Main.em*1.07 - 6), 30, 0);
                glRotatef(0, 0, 0, 1);
                Color.white.bind();
                if(player.getAura())
                    textureAuraProtection.bind(); // or GL11.glBind(texture.getTextureID());
                else
                    textureRocketAuraOff.bind(); // or GL11.glBind(texture.getTextureID());
                glBegin(GL_QUADS);
                {
                    glTexCoord2f(0, 0);
                    glVertex2f(-sx/2, 0);
                    glTexCoord2f(0,1);
                    glVertex2f(-sx/2, sy);
                    glTexCoord2f(1, 1);
                    glVertex2f(sx/2, sy);
                    glTexCoord2f(1, 0);
                    glVertex2f(sx/2,0);
                }
                glEnd();
            }
            glPopMatrix();


        sx /= 0.5;
        sy /= 0.5;

        sx /= 0.5;
        sy /= 0.5;

        sx /= 1.3;
        sy /= 2;
        font3.drawString(50*Main.em -font3.getWidth("88")/2 + 12, sy /2 - 23, String.valueOf(Game.levelTime[Game.getLevel()-1]-Game.curTime%60000/1000), (Game.levelTime[Game.getLevel()-1]-Game.curTime%60000/1000)<11?Color.red:Color.white);
        font2.drawString(50*Main.em -font2.getWidth("level 1")/2 + 12, 10, "level " + String.valueOf(Game.getLevel()), Color.white);

        font1_1.drawString(15*Main.em -font1_1.getWidth("Hit points")/2 , 0, "Hit points", Color.white);
        font1_2.drawString(15*Main.em -font1_2.getWidth("8")/2 , sy /2 - 28, String.valueOf(GOPlayer.getHP()), Color.white);

        font1_1.drawString(30*Main.em -font1_1.getWidth("Score")/2 + 12, 0, "Score", Color.white);
        font1_2.drawString(30*Main.em -font1_2.getWidth(String.valueOf(GOPlayer.getScore()))/2 + 12, sy /2 - 28, String.valueOf(GOPlayer.getScore()), Color.white);

        font1_1.drawString((float) ((100-30) * Main.em*1.07 - font1_1.getWidth("Weapon LVL") / 2 - 15), 0, "Weapon LVL", Color.white);
        font1_2.drawString((float) ((100-30)* Main.em*1.07 -font1_2.getWidth("8")/2-15), sy /2 - 33, String.valueOf(GOPlayer.getWeapon()), Color.white);

        font1_1.drawString((float) ((100-15)* Main.em*1.07 -font1_1.getWidth("Protection")/2 - 15), 0, "Protection", Color.white);

//        font2.drawString(50*Main.em -font2.getWidth("Hit points")/2 + 12, 10, "Hit points", Color.white);
//        font3.drawString(50*Main.em -font3.getWidth("8")/2 + 12, sy /2 - 23, String.valueOf(GOPlayer.getHP()), Color.white);


    }
    public static void menuInterface() {

        glPushMatrix();
        {
            glDisable(GL_TEXTURE_2D);
            glTranslatef(0, 0, 0);
            Color.black.bind();

            glColor4f(0.0f, 0.0f, 0.0f, 0.75f);
            glBegin(GL_QUADS);
            {
                glVertex2f(0, 0);
                glVertex2f(100*Main.em, 0);
                glVertex2f(100*Main.em, 100*Main.em*Main.ratio);
                glVertex2f(0, 100*Main.em*Main.ratio);
            }
            glEnd();
        }
        glPopMatrix();

        glPushMatrix();
        {
            glEnable(GL_TEXTURE_2D);
            glTranslatef(50 * Main.em, menuFirstButtonCoord, 0);
            glRotatef(0, 0, 0, 1);
            Color.white.bind();
            if(Game.advert != 8) {
                if((Mouse.getX() >= 50*Main.em-buttonSizeX/2 && Mouse.getX() <= 50*Main.em+buttonSizeX/2) && (Mouse.getY() >= Main.dHeight-(menuFirstButtonCoord+buttonSizeY) && Mouse.getY() <=  Main.dHeight-(menuFirstButtonCoord)))
                    textureButtonH1.bind();
                else
                    textureButton1.bind();
            }
            else {

                if((Mouse.getX() >= 50*Main.em-buttonSizeX/2 && Mouse.getX() <= 50*Main.em+buttonSizeX/2) && (Mouse.getY() >= Main.dHeight-(menuFirstButtonCoord+buttonSizeY) && Mouse.getY() <=  Main.dHeight-(menuFirstButtonCoord)))
                    textureButtonH1_2.bind();
                else
                    textureButton1_2.bind();
            }

            glBegin(GL_QUADS);
            {
                glTexCoord2f(0, 0);
                glVertex2f(-buttonSizeX/2, 0);
                glTexCoord2f(0,0.53f);
                glVertex2f(-buttonSizeX/2, buttonSizeY);
                glTexCoord2f(0.575f, 0.53f);
                glVertex2f(buttonSizeX/2, buttonSizeY);
                glTexCoord2f(0.575f, 0);
                glVertex2f(buttonSizeX / 2, 0);
            }
            glEnd();
        }
        glPopMatrix();
        glPushMatrix();
        {
            float sx=Main.dWidth/4;
            float sy=Main.dHeight/15;
            glEnable(GL_TEXTURE_2D);
            glTranslatef(50*Main.em, (float) (menuFirstButtonCoord+1.55*sy), 0);
            glRotatef(0, 0, 0, 1);
            Color.white.bind();
            if(Game.advert != 8) {
                if((Mouse.getX() >= 50*Main.em-sx/2 && Mouse.getX() <= 50*Main.em+sx/2) && (Mouse.getY() >= Main.dHeight-(menuFirstButtonCoord+1.55*sy+sy) && Mouse.getY() <=  Main.dHeight-(menuFirstButtonCoord+1.55*sy)))
                    textureButtonH2.bind();
                else
                    textureButton2.bind();
            }
            else {
                textureButton2_2.bind();
            }
            glBegin(GL_QUADS);
            {
                glTexCoord2f(0, 0);
                glVertex2f(-sx/2, 0);
                glTexCoord2f(0,0.53f);
                glVertex2f(-sx/2, sy);
                glTexCoord2f(0.575f, 0.53f);
                glVertex2f(sx/2, sy);
                glTexCoord2f(0.575f, 0);
                glVertex2f(sx/2,0);
            }
            glEnd();
        }
        glPopMatrix();
        glPushMatrix();
        {
            float sx=Main.dWidth/4;
            float sy=Main.dHeight/15;
            glEnable(GL_TEXTURE_2D);
            glTranslatef(50*Main.em, (float) (menuFirstButtonCoord+2*1.55*sy), 0);
            glRotatef(0, 0, 0, 1);
            Color.white.bind();
            if((Mouse.getX() >= 50*Main.em-sx/2 && Mouse.getX() <= 50*Main.em+sx/2) && (Mouse.getY() >= Main.dHeight-(menuFirstButtonCoord+2*1.55*sy+sy) && Mouse.getY() <=  Main.dHeight-(menuFirstButtonCoord+2*1.55*sy)))
                textureButtonH3.bind();
            else
                textureButton3.bind();
            glBegin(GL_QUADS);
            {
                glTexCoord2f(0, 0);
                glVertex2f(-sx/2, 0);
                glTexCoord2f(0,0.53f);
                glVertex2f(-sx/2, sy);
                glTexCoord2f(0.575f, 0.53f);
                glVertex2f(sx/2, sy);
                glTexCoord2f(0.575f, 0);
                glVertex2f(sx/2,0);
            }
            glEnd();
        }
        glPopMatrix();
        glPushMatrix();
        {
            float sx=Main.dWidth/4;
            float sy=Main.dHeight/15;
            glEnable(GL_TEXTURE_2D);
            glTranslatef(50*Main.em, (float) (menuFirstButtonCoord+3*1.55*sy), 0);
            glRotatef(0, 0, 0, 1);
            Color.white.bind();
            if((Mouse.getX() >= 50*Main.em-sx/2 && Mouse.getX() <= 50*Main.em+sx/2) && (Mouse.getY() >= Main.dHeight-(menuFirstButtonCoord+3*1.55*sy+sy) && Mouse.getY() <=  Main.dHeight-(menuFirstButtonCoord+3*1.55*sy)))
                textureButtonH4.bind();
            else
                textureButton4.bind();
            glBegin(GL_QUADS);
            {
                glTexCoord2f(0, 0);
                glVertex2f(-sx/2, 0);
                glTexCoord2f(0,0.53f);
                glVertex2f(-sx/2, sy);
                glTexCoord2f(0.575f, 0.53f);
                glVertex2f(sx/2, sy);
                glTexCoord2f(0.575f, 0);
                glVertex2f(sx/2,0);
            }
            glEnd();
        }
        glPopMatrix();



    }

    public static void advert() {
        glPushMatrix();
        {
            glDisable(GL_TEXTURE_2D);
            glTranslatef(0, 0, 0);
            Color.black.bind();

            glColor4f(0.0f, 0.0f, 0.0f, 0.75f);
            glBegin(GL_QUADS);
            {
                glVertex2f(0, 0);
                glVertex2f(100*Main.em, 0);
                glVertex2f(100*Main.em, 100*Main.em*Main.ratio);
                glVertex2f(0, 100*Main.em*Main.ratio);
            }
            glEnd();
        }
        glPopMatrix();

        glPushMatrix();
        {
            glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            glEnable(GL_TEXTURE_2D);
            glTranslatef(advertX, advertY, 0);
            glRotatef(0, 0, 0, 1);
            advert.bind();
            glBegin(GL_QUADS);
            {
                glTexCoord2f(0, 0);
                glVertex2f(-advertSX/2, -advertSY/2);
                glTexCoord2f(0,0.95f);
                glVertex2f(-advertSX/2, advertSY/2);
                glTexCoord2f(0.8f, 0.95f);
                glVertex2f(advertSX / 2, advertSY / 2);
                glTexCoord2f(0.8f, 0);
                glVertex2f(advertSX / 2, -advertSY / 2);
            }
            glEnd();
        }
        glPopMatrix();

        glPushMatrix();
        {
            glDisable(GL_TEXTURE_2D);
            glEnable(GL_TEXTURE_2D);
            glTranslatef(advertX-10, advertY * 1.35f, 0);
            glRotatef(0, 0, 0, 1);
            if(Mouse.getX() >= advertX-10 - bigButtonSX/2 &&
                    Mouse.getX() <= advertX-10 + bigButtonSX/2 &&
                    Main.dHeight - Mouse.getY() >= advertY * 1.35f - bigButtonSY/2 &&
                    Main.dHeight - Mouse.getY() <= advertY * 1.35f + bigButtonSY/2)
                bigButtonPush.bind();
            else
                bigButton.bind();
            glBegin(GL_QUADS);
            {
                glTexCoord2f(0, 0);
                glVertex2f(-bigButtonSX/2, -bigButtonSY/2);
                glTexCoord2f(0,0.95f);
                glVertex2f(-bigButtonSX/2, bigButtonSY/2);
                glTexCoord2f(0.62f, 0.95f);
                glVertex2f(bigButtonSX/2, bigButtonSY/2);
                glTexCoord2f(0.62f, 0);
                glVertex2f(bigButtonSX/2, -bigButtonSY/2);
            }
            glEnd();
        }
        glPopMatrix();

        switch(Game.advert) {
            case 1:
            case 2:
            case 9:
                fontDrawString(font1_2, "Continue. . .", 10.3f, Color.white);
                break;
            case 3:
            case 4:
            case 5:
                fontDrawString(font1_2, "Start!", 10.3f, Color.white);
                break;
            case 6:
                fontDrawString(font1_2, "AGAIN", 10.3f, Color.white);
                break;
            case 7:
                fontDrawString(font1_2, "Continue", 10.3f, Color.white);
        }
    }
    public static void loseMenu() {
        advert();
        fontDrawString(font1_2, ". . . . You lose . . . .", 2, Color.red);
        fontDrawString(font1_2, "Total score:       " + GOPlayer.getScore(), 4, Color.black);
        fontDrawString(font1_2, "Place in Top List: ", 6, Color.black);
    }
    public static void winMenu() {
        advert();
        fontDrawString(font1_2, "F A N T A S T I C !", 2, Color.red);
        fontDrawString(font1_2, "!!! YOU  W I N  !!!", 3, Color.red);
        fontDrawString(font1_2, "Total score:            ", 5, Color.black);
        fontDrawString(font1_2, "                     " + GOPlayer.getScore(), 5, Color.red);
        fontDrawString(font1_2, "Place in Top List: ", 7, Color.black);
    }
    public static void instruction(int advert) {
        switch(advert) {
            case 1: instructionIntro();
                break;
            case 2: instructionManual();
                break;
            case 3: instructionTask();
                break;
            case 4: instructionTask();
                break;
            case 5: instructionTask();
                break;
            case 6: loseMenu();
                break;
            case 7: winMenu();
                break;
            case 8: menuInterface();
                break;
            case 9: afterLevel();
                break;
            default:
                System.out.println("********************************************************");
                System.out.println("***  N  N OOO TTT    EEE X X I   SSS TTT             ***");
                System.out.println("***  NN N O O  T     Ee   X  I   SS   T              ***");
                System.out.println("***  N NN OOO  T     EEE X X I SSS    T              ***");
                System.out.println("********************************************************");
        }
    }
    public static void instructionIntro() {
        advert();
        fontDrawString(font1_2, "Hello!", 1, Color.black);
        fontDrawString(font1_2, "You must to collect", 3, Color.black);
        fontDrawString(font1_2, "enough points and save your rocket", 4, Color.black);
        fontDrawString(font1_2, "during determine time.", 5, Color.black);
        fontDrawString(font1_2, "You have 3 attempts", 7, Color.red);
    }
    public static void instructionManual() {
        advert();
        fontDrawString(font1_2, "Manual", 1, Color.red);
        fontDrawString(font1_2, "SPACE      -  fire", 3, Color.black);
        fontDrawString(font1_2, "Arrows     -  move rocket", 4, Color.black);
        fontDrawString(font1_2, "ESCAPE     -  menu", 5, Color.black);
        fontDrawString(font1_2, "BACKSPACE  -  exit", 6, Color.black);
    }
    public static void instructionTask() {
        advert();
        fontDrawString(font1_2, "Task!", 1, Color.red);
        fontDrawString(font1_2, "Time to remain:    " + Game.levelTime[Game.level-1], 3, Color.black);
        fontDrawString(font1_2, "Required score:    " + Game.levelScore[Game.level-1], 5, Color.black);
        fontDrawString(font1_2, "Current HitPoints: " + GOPlayer.hitPoints, 7, Color.black);
        fontDrawString(font1_2, "Current Weapon Level: " + GOPlayer.getWeapon(), 8, Color.black);
    }
    public static void afterLevel() {
        advert();
        fontDrawString(font1_2, "Time is up!", 1, Color.red);
        fontDrawString(font1_2, "Your score:    " + GOPlayer.getScore(), 3, Color.black);
        fontDrawString(font1_2, "Current HitPoints: " + GOPlayer.hitPoints, 4, Color.black);
        fontDrawString(font1_2, "Current Weapon Level: " + GOPlayer.getWeapon(), 5, Color.black);
    }

    public static void fontDrawString(TrueTypeFont font, String text, float posY, Color color) {
        font.drawString(Main.dWidth/2 - font.getWidth(text)/2, advertY - advertSY/2.4f + 38 * posY, text, color);
    }


    public static void init() {



        try {
            textureShotLvl1 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(TEXTURE_PATH+"shot2_1.png"));
            textureShotLvl2 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(TEXTURE_PATH+"shot2_2.png"));
            textureShotLvl3 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(TEXTURE_PATH+"shot2_3.png"));
            textureShotLvl4 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(TEXTURE_PATH+"shot2_4.png"));
            textureShotLvl5 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(TEXTURE_PATH+"shot2_5.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            textureMeteor = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(TEXTURE_PATH+"meteor3.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            textureStar = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(TEXTURE_PATH+"star3.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            textureRocket = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(TEXTURE_PATH+"rocket.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            for(int i = 0; i < 17; i++)
                textureBurst[i] = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(TEXTURE_PATH+"burst2/"+(i+1)+".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            textureFon1 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(TEXTURE_PATH+"fon5.png"));
            textureFon2 = TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream(TEXTURE_PATH+"fon2.jpg"));
            textureFon3 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(TEXTURE_PATH+"fon3.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            textureShatl1 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(TEXTURE_PATH+"shatl/1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            textureShatl2 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(TEXTURE_PATH+"shatl/2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            textureShatl3 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(TEXTURE_PATH+"shatl/3.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            textureInterfaceTable1 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(TEXTURE_PATH+"interface/table1_2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            textureInterfaceTable2 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(TEXTURE_PATH+"interface/table2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            textureAuraProtection = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(TEXTURE_PATH+"interface/auraProtection.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            textureRocketAuraOff = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(TEXTURE_PATH+"interface/auraProtectionOff.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            textureRocketAura = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(TEXTURE_PATH+"interface/aura.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            textureButton1 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(TEXTURE_PATH+"interface/menu/button1.png"));
            textureButton1_2 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(TEXTURE_PATH+"interface/menu/button1_2.png"));
            textureButton2 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(TEXTURE_PATH+"interface/menu/button2.png"));
            textureButton2_2 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(TEXTURE_PATH+"interface/menu/button2_2.png"));
            textureButton3 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(TEXTURE_PATH+"interface/menu/button3.png"));
            textureButton4 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(TEXTURE_PATH+"interface/menu/button4.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            textureButtonH1 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(TEXTURE_PATH+"interface/menu/buttonH1.png"));
            textureButtonH1_2 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(TEXTURE_PATH+"interface/menu/buttonH1_2.png"));
            textureButtonH2 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(TEXTURE_PATH+"interface/menu/buttonH2.png"));
            textureButtonH3 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(TEXTURE_PATH+"interface/menu/buttonH3.png"));
            textureButtonH4 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(TEXTURE_PATH+"interface/menu/buttonH4.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            mine = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(TEXTURE_PATH+"enemy/mine2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            rocketEnemy = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(TEXTURE_PATH+"enemy/rocketEnemy.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            advert = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(TEXTURE_PATH+"interface/advert.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bigButton = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(TEXTURE_PATH+"interface/bigButton.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bigButtonPush = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(TEXTURE_PATH+"interface/bigButtonPush.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }



        try {
            InputStream inputStream	= ResourceLoader.getResourceAsStream(FONTS_PATH+"f1.ttf");

            Font awtFont2 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            awtFont2 = awtFont2.deriveFont(24f); // set font size
            font2 = new TrueTypeFont(awtFont2, antiAlias);
        } catch (Exception e) {
            e.printStackTrace();
        }



        try {
            // you can play oggs by loading the complete thing into
            // a sound
            burstSound = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream(SOUND_PATH + "burstSound1.wav"));

//            oggStream = AudioLoader.getStreamingAudio("OGG", ResourceLoader.getResource("testdata/bongos.ogg"));
//
//            modStream = AudioLoader.getStreamingAudio("MOD", ResourceLoader.getResource("testdata/SMB-X.XM"));
//
//            modStream.playAsMusic(1.0f, 1.0f, true);
//
//            aifEffect = AudioLoader.getAudio("AIF", ResourceLoader.getResourceAsStream("testdata/burp.aif"));
//
//            wavEffect = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("testdata/cbrown01.wav"));
        } catch (IOException e) {
            e.printStackTrace();
        }










    }

}
