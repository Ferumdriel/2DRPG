package dev.Ferumdriel.tilegame.tiles;

import dev.Ferumdriel.tilegame.gfx.Assets;

/**
 * Created by Binio on 2016-11-11.
 */
public class RockTile extends Tile{

    public RockTile(int id){
        super(Assets.stone, id);
    }

    @Override
    public boolean isSolid(){
        return true;
    }
}
