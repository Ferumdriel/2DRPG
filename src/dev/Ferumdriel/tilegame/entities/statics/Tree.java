package dev.Ferumdriel.tilegame.entities.statics;

import dev.Ferumdriel.tilegame.Main.Handler;
import dev.Ferumdriel.tilegame.gfx.Assets;
import dev.Ferumdriel.tilegame.items.Item;
import dev.Ferumdriel.tilegame.tiles.Tile;

import java.awt.*;

/**
 * Created by Binio on 2016-11-21.
 */
public class Tree extends StaticEntity {

    public Tree(Handler handler, float x, float y){
        super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);

        bounds.x = 10;
        bounds.y = (int) (height / 1.5f);
        bounds.width = width - 20;
        bounds.height = (int) (height - height / 1.5f);
    }

    @Override
    public void tick(){

    }

    @Override
    public void die(){
        handler.getWorld().getItemManager().addItem(Item.woodItem.createNew((int) x, (int) y));
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.tree, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }

}
