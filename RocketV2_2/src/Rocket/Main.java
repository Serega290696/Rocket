package Rocket;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Cursor;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static org.lwjgl.opengl.GL11.*;

public class Main {

    public static final String GAME_TITLE = "Aliens";
    public static Game game;

    public static final int delay = 50;
    public static final int fps = (int) (1000 / delay);

    public static final float ratio = 0.7f;
    public static final int dWidth = 1200;
    public static final int dHeight = (int)(dWidth * ratio);
    public static final int em = dWidth / 100;

//    public static final int dWidth = Display.getDesktopDisplayMode().getWidth();
//    public static final int dHeight = Display.getDesktopDisplayMode().getHeight();
//    public static final float ratio = 0.55f;
//    public static final int em = dWidth / 100;


    public static void main(String[] args) {

        initDisplay();
        initGL();

        restartGame();

    }

    public static void restartGame() {
        initGame();

        gameLoop();

        cleanUp();
    }

    private static void initDisplay() {

        try {
            Display.setDisplayMode(new DisplayMode(dWidth, dHeight));
//            setDisplayMode(dWidth, dHeight, true);
            Display.setFullscreen(true);
            Display.create();
            Display.setVSyncEnabled(true);
            Display.setTitle(GAME_TITLE);
            //Display.setIcon();
            Display.setResizable(false);
            Keyboard.create();
            Mouse.create();
            Mouse.setGrabbed(true);
        } catch (LWJGLException e) {
            e.printStackTrace();
        }

    }
    private static void initGL() {

        GL11.glShadeModel(GL11.GL_SMOOTH);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_LIGHTING);

        GL11.glClearDepth(1);

        //NEW
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        glViewport(0, 0, dWidth, dHeight);
        glMatrixMode(GL_MODELVIEW);
        // FONTS




        // ??????????????
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        //GL11.glOrtho(0, dWidth, 0, dHeight, -1, 1);
        GL11.glOrtho(0, dWidth, dHeight, 0, dWidth, -dWidth);
        glMatrixMode(GL_MODELVIEW);


        glClearColor(0.0f, 0.0f, 0.002f, 1);

        // ??????????????
    }
    private static void initGame() {
        game = new Game();

    }

    private static void gameLoop() {
        while(!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) ) {
            getInput();
            update();
            render();
            if(Keyboard.isKeyDown(Keyboard.KEY_R))
                restartGame();
        }
        cleanUp();
    }

    private static void getInput() {
        game.getInput();
    }
    private static void update() {
        game.update();
    }
    private static void render() {
        //glClear(GL_COLOR_BUFFER_BIT);
        glLoadIdentity();

        game.render();

        Display.update();
        Display.sync(fps);
    }

    public static void cleanUp() {
        Display.destroy();
        Keyboard.destroy();
        Mouse.destroy();
    }


    public static void setDisplayMode(int width, int height, boolean fullscreen) {

        // return if requested DisplayMode is already set
        if ((Display.getDisplayMode().getWidth() == width) &&
                (Display.getDisplayMode().getHeight() == height) &&
                (Display.isFullscreen() == fullscreen)) {
            return;
        }

        try {
            DisplayMode targetDisplayMode = null;

            if (fullscreen) {
                DisplayMode[] modes = Display.getAvailableDisplayModes();
                int freq = 0;

                for (int i=0;i<modes.length;i++) {
                    DisplayMode current = modes[i];

                    if ((current.getWidth() == width) && (current.getHeight() == height)) {
                        if ((targetDisplayMode == null) || (current.getFrequency() >= freq)) {
                            if ((targetDisplayMode == null) || (current.getBitsPerPixel() > targetDisplayMode.getBitsPerPixel())) {
                                targetDisplayMode = current;
                                freq = targetDisplayMode.getFrequency();
                            }
                        }

                        // if we've found a match for bpp and frequence against the
                        // original display mode then it's probably best to go for this one
                        // since it's most likely compatible with the monitor
                        if ((current.getBitsPerPixel() == Display.getDesktopDisplayMode().getBitsPerPixel()) &&
                                (current.getFrequency() == Display.getDesktopDisplayMode().getFrequency())) {
                            targetDisplayMode = current;
                            break;
                        }
                    }
                }
            } else {
                targetDisplayMode = new DisplayMode(width,height);
            }

            if (targetDisplayMode == null) {
                System.out.println("Failed to find value mode: "+width+"x"+height+" fs="+fullscreen);
                return;
            }

            Display.setDisplayMode(targetDisplayMode);
            Display.setFullscreen(fullscreen);

        } catch (LWJGLException e) {
            System.out.println("Unable to setup mode "+width+"x"+height+" fullscreen="+fullscreen + e);
        }
    }
}
