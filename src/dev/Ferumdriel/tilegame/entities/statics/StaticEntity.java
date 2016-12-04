package dev.Ferumdriel.tilegame.entities.statics;

import dev.Ferumdriel.tilegame.Main.Handler;
import dev.Ferumdriel.tilegame.entities.Entity;
import dev.Ferumdriel.tilegame.gfx.Assets;
import dev.Ferumdriel.tilegame.items.Item;

import java.awt.*;

/**
 * Created by Binio on 2016-11-21.
 */
public abstract class StaticEntity extends Entity {

    public StaticEntity(Handler handler, float x, float y, int width, int height){
        super(handler, x, y, width, height);
    }
}
