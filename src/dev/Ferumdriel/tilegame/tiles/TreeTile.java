package dev.Ferumdriel.tilegame.tiles;

import dev.Ferumdriel.tilegame.gfx.Assets;

/**
 * Created by Binio on 2016-11-18.
 */
public class TreeTile extends Tile{
    public TreeTile(int id){
        super(Assets.tree, id);
    }

    @Override
    public boolean isSolid(){
        return true;
    }
}
