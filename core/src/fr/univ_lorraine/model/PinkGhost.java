package fr.univ_lorraine.model;

import com.badlogic.gdx.math.Vector2;
import fr.univ_lorraine.model.Ghost;

import java.lang.annotation.Documented;
import java.util.Random;
import java.util.Vector;

public class PinkGhost extends Ghost{

    public PinkGhost(Vector2 pos, World w){
        super(pos, w);
        setDirection(UP);
    }

    @Override
    int rechercheDir(int currX, int currY){

        /* est ce que le x est celui du pacmn (pas)
        test si le x du pacman est < à x onn tente à gauche
        else on tente à droite

         si y est celui du pacman (pas)
         test si le y du pacman est > à y on tente en haut
         sinon on tente en bas

         sinon aléatoire

         */

        Random rand = new Random(System.currentTimeMillis());

        if(currX == world.getPacman().getPosition().x){
            if(currX > world.getPacman().getPosition().x)
                return LEFT;
            else return RIGHT;
        }
        else if (currY == world.getPacman().getPosition().y){
            if(currY < world.getPacman().getPosition().y)
                return UP;
            else return DOWN;
        }
        else return rand.nextInt(3) + 0;

    }


}
