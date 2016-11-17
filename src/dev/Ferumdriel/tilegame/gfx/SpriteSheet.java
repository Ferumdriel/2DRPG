package dev.Ferumdriel.tilegame.gfx;

import java.awt.image.BufferedImage;

/**
 * Created by Binio on 2016-10-03.
 */
public class SpriteSheet {

    private BufferedImage sheet;

    public SpriteSheet(BufferedImage sheet){
        this.sheet = sheet;
    }

    public BufferedImage crop(int x, int y, int width, int height){
        return sheet.getSubimage(x,y,width,height);
    }
}
