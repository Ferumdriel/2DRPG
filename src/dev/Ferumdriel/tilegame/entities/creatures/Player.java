package dev.Ferumdriel.tilegame.entities.creatures;

import dev.Ferumdriel.tilegame.Main.Game;
import dev.Ferumdriel.tilegame.gfx.Assets;

import java.awt.*;

/**
 * Created by Binio on 2016-10-12.
 */
public class Player extends Creature {

    public Player(Game game, float x, float y) {
        super(game, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
    }

    @Override
    public void tick() {
        getInput();
        move();
        game.getGameCamera().centerOnEntity(this);
    }

    private void getInput(){
        xMove = 0;
        yMove = 0;

        if(game.getKeyManager().up) { yMove = -speed; } //y axis is moving from the top to the bottom
        if(game.getKeyManager().down) { yMove = speed; }
        if(game.getKeyManager().left) { xMove = -speed; }
        if(game.getKeyManager().right) { xMove = speed; }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.player, (int) (x - game.getGameCamera().getxOffset()), (int) (y - game.getGameCamera().getyOffset()), width, height, null);
    }
}
