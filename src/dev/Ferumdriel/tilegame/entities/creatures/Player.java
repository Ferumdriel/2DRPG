package dev.Ferumdriel.tilegame.entities.creatures;

import dev.Ferumdriel.tilegame.Main.Game;
import dev.Ferumdriel.tilegame.Main.Handler;
import dev.Ferumdriel.tilegame.gfx.Animation;
import dev.Ferumdriel.tilegame.gfx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Binio on 2016-10-12.
 */
public class Player extends Creature {

    //Animations
    private Animation animDown, animUp, animLeft, animRight;

    public Player(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

        bounds.x = 19;
        bounds.y = 32;
        bounds.width = 26;
        bounds.height = 30; //character tile has 2 pixels more below his boots so it had to be adjusted

        //Animations
        animDown = new Animation(500, Assets.player_down);
        animUp = new Animation(500, Assets.player_up);
        animRight = new Animation(500, Assets.player_right);
        animLeft = new Animation(500, Assets.player_left);
    }

    @Override
    public void tick() {
        //Animations
        animDown.tick();
        //Movement
        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);
    }

    private void getInput(){
        xMove = 0;
        yMove = 0;

        if(handler.getKeyManager().up) { yMove = -speed; } //y axis is moving from the top to the bottom
        if(handler.getKeyManager().down) { yMove = speed; }
        if(handler.getKeyManager().left) { xMove = -speed; }
        if(handler.getKeyManager().right) { xMove = speed; }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);

//        g.setColor(Color.red);
//        g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
//                    (int)(y + bounds.y - handler.getGameCamera().getyOffset()),
//                    bounds.width, bounds.height);

    }

    private BufferedImage getCurrentAnimationFrame() {
        if (xMove < 0) {
            return animLeft.getCurrentFrame();
        } else if (xMove > 0) {
            return animRight.getCurrentFrame();
        } else if (yMove < 0) {
            return animUp.getCurrentFrame();
        } else {
            return animDown.getCurrentFrame();
        }
    }
}
