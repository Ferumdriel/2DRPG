package dev.Ferumdriel.tilegame.display;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Binio on 2016-10-02.
 */
public class Display {

    private JFrame frame;
    private Canvas canvas;

    private String title;
    private int width, height;

    public Display(String title, int width, int height){
        this.title = title;
        this.width = width;
        this.height = height;

        createDisplay();
    }

    private void createDisplay(){
        prepFrame();
        prepCanvas();
        frame.add(canvas);
        frame.pack(); //resize window to see whole canvas
    }

    private void prepFrame(){
        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null); //frame will appear in the middle of the screen
        frame.setVisible(true);
    }

    private void prepCanvas(){
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width,height));
        canvas.setMaximumSize(new Dimension(width,height));
        canvas.setMinimumSize(new Dimension(width,height));
        canvas.setFocusable(false);
    }

    public Canvas getCanvas(){
        return canvas;
    }

    public JFrame getFrame(){
        return frame;
    }
}
