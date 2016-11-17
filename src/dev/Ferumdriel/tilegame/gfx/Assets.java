package dev.Ferumdriel.tilegame.gfx;

/**
 * Created by Binio on 2016-10-03.
 */

import java.awt.image.BufferedImage;

/** It's going to load in everything into our game and it will load only once **/
public class Assets {

    private static final int height = 32, width = 32;
    public static BufferedImage player, dirt, grass, stone, tree;

    public static void init(){
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));

        player = sheet.crop(0,0,width, height);
        dirt = sheet.crop(width,0,width, height);
        grass = sheet.crop(width * 2,0,width, height);
        stone = sheet.crop(width * 3,0,width, height);
        tree = sheet.crop(0,height,width, height);
    }
}
