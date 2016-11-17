package dev.Ferumdriel.tilegame.states;

import dev.Ferumdriel.tilegame.Main.Game;

import java.awt.*;

/**
 * Created by Binio on 2016-10-12.
 */
public abstract class State {

    private static State currentState = null;

    public static void setState(State state){
        currentState = state;
    }

    public static State getState(){
        return currentState;
    }

    protected Game game;

    public State(Game game){
        this.game = game;
    }
    //CLASS
    public abstract void tick(); //tick of every state

    public abstract void render(Graphics g);

}
