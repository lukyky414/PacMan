package fr.univ_lorraine.model;

import fr.univ_lorraine.model.Movable;
import fr.univ_lorraine.model.World;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class RedGhost extends Movable {

    private int nextDir;
    private boolean isDead, isEscaping;
    private Pacman pacman = world.getPacman();
    Random rand = new Random(System.currentTimeMillis());


    public RedGhost(Vector2 pos, World w){
        super(pos, w, new int[]{0});
        nextDir = UP;
        isDead = false;
        isEscaping = false;
        setDirection(UP);
    }

    void changeBloquant(){
        int type = this.world.getMaze().getMap((int) pos.x, (int) pos.y);
        if (type != 3)
            setBloquant(new int[]{0,3});
    }

    void changeDir(){
       int type = this.world.getMaze().getMap((int) pos.x, (int) pos.y);
        int type2 = this.world.getMaze().getMap((int) pos.x -1, (int) pos.y -1);
       if(type == 2)
            setDirection(rand.nextInt(3) + 0);
        for(int non : this.bloquant) {
            if (type == non)
                setDirection(rand.nextInt(3) + 0);
        }

    }

    public boolean getDead(){return isDead;}
    public boolean getEscaping(){return isEscaping;}

}
