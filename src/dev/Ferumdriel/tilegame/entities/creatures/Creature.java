package dev.Ferumdriel.tilegame.entities.creatures;

import dev.Ferumdriel.tilegame.Main.Game;
import dev.Ferumdriel.tilegame.Main.Handler;
import dev.Ferumdriel.tilegame.entities.Entity;
import dev.Ferumdriel.tilegame.tiles.Tile;

import java.awt.*;

/**
 * Created by Binio on 2016-10-12.
 */
public abstract class Creature extends Entity{


    public static final float DEFAULT_SPEED = 3.0f;
    public static final int DEFAULT_CREATURE_WIDTH = 64,
                            DEFAULT_CREATURE_HEIGHT = 64;


    protected int health;
    protected float speed;
    protected float xMove, yMove;

    public Creature(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;
    }

    public void move(){
        if(!checkEntityCollisions(xMove, 0f)){
            moveX();
        }
        if(!checkEntityCollisions(0f, yMove)){
            moveY();
        }
    }

    public void moveX(){
        if(xMove > 0){ //moving right
            int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;
            if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
                    !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)){
                x += xMove;
            }else{ //if there's collision
                x = tx * Tile.TILEWIDTH - bounds.x - bounds.width - 1; //-1 so we will be able to move up and down near collision
            }
        }else if (xMove < 0){ //moving left
            int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;
            if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
                    !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)){
                x += xMove;
            }else{
                x = tx * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x;
            }
        }

    }

    public void moveY(){
        if(yMove < 0){
            int ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;

            if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) && !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)){
                y += yMove;
            }else{
                y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y;
            }
        }else if(yMove > 0){
            int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;

            if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) && !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)){
                y += yMove;
            }else{
                y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height - 1; //doesn't look perfect
            }
        }
    }

    protected boolean collisionWithTile(int x, int y){
        return handler.getWorld().getTile(x,y).isSolid();
    }

 /** GETTERS AND SETTERS **/

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getxMove() {
        return xMove;
    }

    public void setxMove(float xMove) {
        this.xMove = xMove;
    }

    public float getyMove() {
        return yMove;
    }

    public void setyMove(float yMove) {
        this.yMove = yMove;
    }
}
