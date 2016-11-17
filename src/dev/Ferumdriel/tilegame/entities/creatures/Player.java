package dev.Ferumdriel.tilegame.entities.creatures;

import dev.Ferumdriel.tilegame.Main.Game;
import dev.Ferumdriel.tilegame.Main.Handler;
import dev.Ferumdriel.tilegame.gfx.Assets;

import java.awt.*;

/**
 * Created by Binio on 2016-10-12.
 */
public class Player extends Creature {

    public Player(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
    }

    @Override
    public void tick() {
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
        g.drawImage(Assets.player, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }
}
