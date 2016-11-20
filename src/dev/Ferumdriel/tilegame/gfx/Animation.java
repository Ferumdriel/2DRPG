package dev.Ferumdriel.tilegame.gfx;

import java.awt.image.BufferedImage;

/**
 * Created by Binio on 2016-11-20.
 */
public class Animation {

    private int speed, index;
    private long lastTime, timer;
    private BufferedImage[] frames;


    public Animation(int speed, BufferedImage[] frames){
        this.speed = speed;
        this.frames = frames;
        index = 0;
        timer = 0;
        lastTime = System.currentTimeMillis();
    }

    public void tick(){
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis(); //set time to the time tick was called

        if(timer > speed){
            index++;
            timer = 0;
            if(index >= frames.length){
                index = 0;
            }
        }
    }

    public BufferedImage getCurrentFrame(){
        return frames[index];
    }
}
