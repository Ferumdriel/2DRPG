package dev.Ferumdriel.tilegame.worlds;

import dev.Ferumdriel.tilegame.Main.Handler;
import dev.Ferumdriel.tilegame.entities.creatures.Player;
import dev.Ferumdriel.tilegame.entities.statics.EntityManager;
import dev.Ferumdriel.tilegame.entities.statics.Rock;
import dev.Ferumdriel.tilegame.entities.statics.Tree;
import dev.Ferumdriel.tilegame.items.ItemManager;
import dev.Ferumdriel.tilegame.tiles.Tile;
import utils.Utils;

import java.awt.*;

/**
 * Created by Binio on 2016-11-12.
 */
public class World {

    private Handler handler;
    private int width, height;
    private int spawnX, spawnY;
    private int[][] tiles;
    //Entitties
    private EntityManager entityManager;
    //Item
    private ItemManager itemManager;

    public World(Handler handler, String path) {
        this.handler = handler;
        entityManager = new EntityManager(handler, new Player(handler, 100, 100));
        itemManager = new ItemManager(handler);
        //Temporary entity code
        entityManager.addEntity(new Tree(handler, 100, 250));
        entityManager.addEntity(new Tree(handler, 100, 350));
        entityManager.addEntity(new Tree(handler, 100, 450));
        entityManager.addEntity(new Tree(handler, 100, 550));
        entityManager.addEntity(new Rock(handler, 100, 650));

        loadWorld(path);

        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);
    }

    public void tick() {
        itemManager.tick();
        entityManager.tick();
    }

    public void render(Graphics g) {
        int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
        int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
        int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEWIDTH);
        int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);




        for (int y = yStart; y < yEnd; y++) {
            for (int x = xStart; x < xEnd; x++) {
                getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()), (int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
            }
        }

        //Items
        itemManager.render(g);
        //Entitties
        entityManager.render(g);
    }

    public Tile getTile(int x, int y) {
        if(x < 0 || y < 0 || x >= width || y >= height){
            return Tile.grassTile; //if he goes out of the map game won't crash and will think he's still standing on grass tile
        }

        Tile t = Tile.tiles[tiles[x][y]];
        if (t == null) {
            return Tile.dirtTile;
        }
        return t;
    }

    private void loadWorld(String path) {
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+"); //every little number will be split
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);

        tiles = new int[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
            }
        }
    }

    public Handler getHandler() {
        return handler;
    }

    public ItemManager getItemManager() {
        return itemManager;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }
}
