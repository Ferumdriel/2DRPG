package dev.Ferumdriel.tilegame.states;

import dev.Ferumdriel.tilegame.Main.Game;
import dev.Ferumdriel.tilegame.Main.Handler;
import dev.Ferumdriel.tilegame.gfx.Assets;
import ui.ClickListener;
import ui.UIImageButton;
import ui.UIManager;

import java.awt.*;

/**
 * Created by Binio on 2016-10-12.
 */
public class MenuState extends State{

    private UIManager uiManager;

    public MenuState(Handler handler){
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);

        uiManager.addObject(new UIImageButton(200, 200, 128, 64, Assets.btn_start, new ClickListener(){
            @Override
            public void onClick() {
                handler.getMouseManager().setUIManager(null); //to make sure user won't be able to click on start button even though it would be invisible for him
                State.setState(handler.getGame().gameState);
            }
        }));
    }

    @Override
    public void tick() {
        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        uiManager.render(g);
    }
}
