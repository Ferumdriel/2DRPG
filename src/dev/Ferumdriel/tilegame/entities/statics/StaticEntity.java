package dev.Ferumdriel.tilegame.entities.statics;

import dev.Ferumdriel.tilegame.Main.Handler;
import dev.Ferumdriel.tilegame.entities.Entity;
import dev.Ferumdriel.tilegame.gfx.Assets;

import java.awt.*;

/**
 * Created by Binio on 2016-11-21.
 */
public abstract class StaticEntity extends Entity {

    public StaticEntity(Handler handler, float x, float y, int width, int height){
        super(handler, x, y, width, height);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.tree, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }
}
