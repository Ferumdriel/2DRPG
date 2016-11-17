package dev.Ferumdriel.tilegame.Main;

/**
 * Created by Binio on 2016-10-02.
 */

import dev.Ferumdriel.tilegame.display.Display;
import dev.Ferumdriel.tilegame.gfx.Assets;
import dev.Ferumdriel.tilegame.gfx.GameCamera;
import dev.Ferumdriel.tilegame.gfx.ImageLoader;
import dev.Ferumdriel.tilegame.gfx.SpriteSheet;
import dev.Ferumdriel.tilegame.input.KeyManager;
import dev.Ferumdriel.tilegame.states.GameState;
import dev.Ferumdriel.tilegame.states.MenuState;
import dev.Ferumdriel.tilegame.states.State;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import static java.awt.Color.green;
import static java.awt.Color.red;

/**
 * Holding all base code of our game
 */
public class Game implements Runnable{

    private Display display;
    private int width, height;
    public String title;
    private boolean running = false;
    private Thread thread;

    private BufferStrategy bs;
    private Graphics g;

    //States
    private State gameState;
    private State menuState;

    //Input
    private KeyManager keyManager;

    //Camera
    private GameCamera gameCamera;


    public Game(String title, int width, int height){
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
    }

    private void init(){
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        Assets.init();

        gameCamera = new GameCamera(this, 0, 0);
        handler = new Handler(this);

        gameState = new GameState(handler);
        menuState = new MenuState(handler);
        State.setState(gameState);
    }

    private void tick()
    {
        keyManager.tick();
        if(State.getState() != null){
            State.getState().tick();
        }
    }

    private void render(){
        bs = display.getCanvas().getBufferStrategy();
        if(bs==null){
            display.getCanvas().createBufferStrategy(3); //3 buffers
            return;
        }
        g = bs.getDrawGraphics();
        //Clear screen
        g.clearRect(0,0,width, height);
        //Draw here!
        if(State.getState() != null){
            State.getState().render(g);
        }

        //End Drawing!
        bs.show();
        g.dispose();
    }
    @Override
    public void run() {
        init();

        int fps = 60;
        //timePerTick maximum amount of time in nanoseconds that we can wait to achieve 60 fps
        double timePerTick = Math.pow(10, 9) / fps; //10^9 nanoseconds
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while(running){
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick; //we want to know if we want to run tick and render methods or not
            timer += now - lastTime;    //amount of nanoseconds since we called 79-82 for the last time
            lastTime = now;

            if(delta >= 1) {
                tick();
                render();
                ticks++;
                delta--;
            }

            if(timer >= Math.pow(10,9)){
                System.out.println("Ticks and Frames: " + ticks);
                ticks = 0;
                timer = 0;
            }
        }

        stop(); //in case it didn't stop earlier
    }

    public KeyManager getKeyManager(){
        return keyManager;
    }

    public GameCamera getGameCamera(){
        return gameCamera;
    }

    public Handler handler;

    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }

    public synchronized void start(){
        if(running) {
            return;
        }
            running = true;
            thread = new Thread(this);
            thread.start(); //will call run method
    }

    public synchronized void stop(){
        if(!running) {
            return;
        }
            running = false;
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

    }


}
