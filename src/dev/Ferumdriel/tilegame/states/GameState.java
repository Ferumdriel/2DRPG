package dev.Ferumdriel.tilegame.states;

import dev.Ferumdriel.tilegame.Main.Game;
import dev.Ferumdriel.tilegame.entities.creatures.Player;
import dev.Ferumdriel.tilegame.gfx.Assets;
import dev.Ferumdriel.tilegame.tiles.Tile;
import dev.Ferumdriel.tilegame.worlds.World;

import java.awt.*;

/**
 * Created by Binio on 2016-10-12.
 */
public class GameState extends State{

    private Player player;
    private World world;

    public GameState(Game game){
        super(game);
        player = new Player(game, 100, 100);
        world = new World(game, "res/worlds/world1.txt");
    }

    @Override
    public void tick() {
        world.tick();
        player.tick();
        game.getGameCamera().move(1,1);
    }

    @Override
    public void render(Graphics g) {
        world.render(g); //before rendering player so he'll be rendered above world and not behind it
        player.render(g);
    }
}
