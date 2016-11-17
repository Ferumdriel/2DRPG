package dev.Ferumdriel.tilegame.Main;

/**
 * Created by Binio on 2016-10-02.
 */
public class Launcher {

    public static void main(String[] args){
        Game game = new Game("Title!", 640, 300);
        game.start();
    }
}
