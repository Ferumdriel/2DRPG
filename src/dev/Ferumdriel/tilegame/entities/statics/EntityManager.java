package dev.Ferumdriel.tilegame.entities.statics;

import dev.Ferumdriel.tilegame.Main.Handler;
import dev.Ferumdriel.tilegame.entities.Entity;
import dev.Ferumdriel.tilegame.entities.creatures.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by Binio on 2016-11-21.
 * Holds information about all en titties
 */
public class EntityManager {

    private Handler handler;
    private Player player;
    private ArrayList<Entity> entitties;
    /** Compares en titties by their Y position to make sure they'll be rendered in proper order **/
    private Comparator<Entity> renderSorter = new Comparator<Entity>(){
        @Override
        public int compare(Entity a, Entity b) {
            if(a.getY() + a.getHeight() < b.getY() + b.getHeight()){
                return -1;
            }
            return 1;
        }
    };

    public EntityManager(Handler handler, Player player){
        this.handler = handler;
        this.player = player;
        entitties = new ArrayList<Entity>();
        addEntity(player);
    }

    public void tick(){
        for(int i = 0; i < entitties.size(); i++){
            Entity e = entitties.get(i);
            e.tick();
        }
        entitties.sort(renderSorter);
    }

    public void render(Graphics g){
        for(Entity e: entitties){
            e.render(g);
        }
    }

    public void addEntity(Entity e){
        entitties.add(e);
    }

    /** GETTERS AND SETTER **/

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ArrayList<Entity> getEntitties() {
        return entitties;
    }

    public void setEntitties(ArrayList<Entity> entitties) {
        this.entitties = entitties;
    }
}
