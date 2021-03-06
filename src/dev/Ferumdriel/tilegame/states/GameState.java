package dev.Ferumdriel.tilegame.states;

import dev.Ferumdriel.tilegame.Main.Game;
import dev.Ferumdriel.tilegame.Main.Handler;
import dev.Ferumdriel.tilegame.entities.creatures.Player;
import dev.Ferumdriel.tilegame.entities.statics.Tree;
import dev.Ferumdriel.tilegame.gfx.Assets;
import dev.Ferumdriel.tilegame.tiles.Tile;
import dev.Ferumdriel.tilegame.worlds.World;

import java.awt.*;

/**
 * Created by Binio on 2016-10-12.
 */
public class GameState extends State{

    private World world;

    public GameState(Handler handler){
        super(handler);
        world = new World(handler, "res/worlds/world1.txt");
        handler.setWorld(world);

    }

    @Override
    public void tick() {
        world.tick();
    }

    @Override
    public void render(Graphics g) {
        world.render(g); //before rendering player so he'll be rendered above world and not behind it
    }
}
