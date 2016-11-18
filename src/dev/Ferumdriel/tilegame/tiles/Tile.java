package dev.Ferumdriel.tilegame.tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Binio on 2016-11-11.
 */
public class Tile {

    //STATIC STUFF HERE

    public static Tile[] tiles = new Tile[256];
    public static Tile grassTile = new GrassTile(0);
    public static Tile dirtTile = new DirtTile(1);
    public static Tile rockTile = new RockTile(2);
    public static Tile treeTile = new TreeTile(5);


    //CLASS


    public static final int TILEWIDTH = 64, TILEHEIGHT = 64;

    protected BufferedImage texture; //image of tile
    protected final int id;

    public Tile(BufferedImage texture, int id){
        this.texture = texture;
        this.id = id;

        tiles[id] = this;
    }

    public void tick(){
        //TODO
    }

    public void render(Graphics g, int x, int y){
        g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
    }

    public boolean isSolid(){
        return false; //you're allowed to walk through it
    }


    public int getId(){
        return id;
    }
}
