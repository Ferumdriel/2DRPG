package dev.Ferumdriel.tilegame.items;

import dev.Ferumdriel.tilegame.Main.Handler;
import dev.Ferumdriel.tilegame.gfx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Binio on 2016-12-04.
 */
public class Item {

    // HANDLER

    public static Item[] items = new Item[256];
    public static Item woodItem = new Item(Assets.wood, "Wood", 0);
    public static Item rockItem = new Item(Assets.stone, "Rock", 1);

    // CLASS

    public static final int ITEMWIDTH = 32, ITEMHEIGHT = 32, PICKED_UP = -1;

    protected Handler handler;
    protected BufferedImage texture;
    protected String name;
    protected final int id;

    protected int x, y, count;

    public Item(BufferedImage texture, String name, int id){
        this.texture = texture;
        this.name = name;
        this.id = id;
        count = 1;

        items[id] = this;
    }

    public void tick(){
    }

    //Item is in the world
    public void render(Graphics g){
        if(handler == null){
            return;
        }
        render(g,(int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()));
    }

    // Item is in player's inventory and we want to render it there
    public void render(Graphics g, int x, int y){
        g.drawImage(texture, x, y, ITEMWIDTH, ITEMHEIGHT, null);
    }

    public Item createNew(int x, int y){
        Item i = new Item(texture, name, id);
        i.setPosition(x,y);
        return i;
    }

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    //GETTERS AND SETTERS

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}