package dev.Ferumdriel.tilegame.Main;

import dev.Ferumdriel.tilegame.gfx.GameCamera;
import dev.Ferumdriel.tilegame.input.KeyManager;
import dev.Ferumdriel.tilegame.input.MouseManager;
import dev.Ferumdriel.tilegame.worlds.World;

/**
 * Created by Binio on 2016-11-18.
 */
public class Handler {

    private Game game;
    private World world;

    public Handler(Game game){
        this.game = game;
    }

    //GETTERS AND SETTERS

    public GameCamera getGameCamera(){
        return game.getGameCamera();
    }

    public KeyManager getKeyManager(){
        return game.getKeyManager();
    }

    public MouseManager getMouseManager(){
        return game.getMouseManager();
    }

    public int getWidth(){
        return game.getWidth();
    }

    public int getHeight(){
        return game.getHeight();
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }
}
