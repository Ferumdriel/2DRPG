package dev.Ferumdriel.tilegame.entities.creatures;

import dev.Ferumdriel.tilegame.Main.Game;
import dev.Ferumdriel.tilegame.Main.Handler;
import dev.Ferumdriel.tilegame.entities.Entity;
import dev.Ferumdriel.tilegame.gfx.Animation;
import dev.Ferumdriel.tilegame.gfx.Assets;
import dev.Ferumdriel.tilegame.inventory.Inventory;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Binio on 2016-10-12.
 */
public class Player extends Creature {

    //Animations
    private Animation animDown, animUp, animLeft, animRight;
    //Attack timer
    private long lastAttackTimer, attackCooldown = 400, attackTimer = attackCooldown;
    //Inventory
    private Inventory inventory;

    public Player(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

        bounds.x = 19;
        bounds.y = 32;
        bounds.width = 26;
        bounds.height = 30; //character tile has 2 pixels more below his boots so it had to be adjusted

        //Animations
        animDown = new Animation(500, Assets.player_down);
        animUp = new Animation(500, Assets.player_up);
        animRight = new Animation(500, Assets.player_right);
        animLeft = new Animation(500, Assets.player_left);

        inventory = new Inventory(handler);
    }

    @Override
    public void tick() {
        //Animations
        animationTick();
        //Movement
        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);
        //Attack
        checkAttacks();
        //Inventory
        inventory.tick();
    }

    private void checkAttacks() {
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if(attackTimer < attackCooldown){
            return;
        }
        Rectangle cb = getCollisionBounds(0, 0);
        Rectangle ar = new Rectangle(); //attack rectangle
        int arSize = 20;
        ar.width = arSize;
        ar.height = arSize;

        if (handler.getKeyManager().aUp) {
            ar.x = cb.x + cb.width /2 - arSize / 2;
            ar.y = cb.y - arSize;
        }else if (handler.getKeyManager().aDown){
            ar.x = cb.x + cb.width /2 - arSize / 2;
            ar.y = cb.y + cb.height;
        }else if (handler.getKeyManager().aLeft){
            ar.x = cb.x - arSize;
            ar.y = cb.y + cb.height - arSize / 2;
        }else if (handler.getKeyManager().aRight){
            ar.x = cb.x + cb.width;
            ar.y = cb.y + cb.height / 2 - arSize / 2;
        }else{
            return;
        }

        attackTimer = 0; //entity is going to attack so we reset it

        for(Entity e: handler.getWorld().getEntityManager().getEntitties()){
            if(e.equals(this)){
                continue;
            }
            if(e.getCollisionBounds(0,0).intersects(ar)){
                e.hurt(1);
                return;
            }

        }
    }

    @Override
    public void die() {
        System.out.println("You lose");
    }

    private void getInput() {
        xMove = 0;
        yMove = 0;

        if (handler.getKeyManager().up) {
            yMove = -speed;
        } //y axis is moving from the top to the bottom
        if (handler.getKeyManager().down) {
            yMove = speed;
        }
        if (handler.getKeyManager().left) {
            xMove = -speed;
        }
        if (handler.getKeyManager().right) {
            xMove = speed;
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        inventory.render(g);
//        g.setColor(Color.red);
//        g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
//                    (int)(y + bounds.y - handler.getGameCamera().getyOffset()),
//                    bounds.width, bounds.height);

    }

    private void animationTick() {
        if (xMove < 0) {
            animLeft.tick();
        } else if (xMove > 0) {
            animRight.tick();
        } else if (yMove < 0) {
            animUp.tick();
        } else {
            animDown.tick();
        }
    }

    private BufferedImage getCurrentAnimationFrame() {
        if (xMove < 0) {
            return animLeft.getCurrentFrame();
        } else if (xMove > 0) {
            return animRight.getCurrentFrame();
        } else if (yMove < 0) {
            return animUp.getCurrentFrame();
        } else {
            return animDown.getCurrentFrame();
        }
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
