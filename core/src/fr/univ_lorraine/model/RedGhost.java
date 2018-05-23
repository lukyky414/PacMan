package fr.univ_lorraine.model;

import fr.univ_lorraine.model.Movable;
import fr.univ_lorraine.model.World;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class RedGhost extends Movable {

    Random rand = new Random(System.currentTimeMillis());


    public RedGhost(Vector2 pos, World w){
        super(pos, w, new int[]{0});
        setDirection(UP);

    }


    void changeDir(){
        int type = this.world.getMaze().getMap((int) pos.x, (int) pos.y);
       if(type == 2)
           setDirection(rand.nextInt(3) + 0);

    }

    /* Changer direction : vérifier si on peut continuer dans la même direction
    Si non : vérifier à droite et à gauche. Commencer par gauche puis droite. Si gauche est possible prendre gauche
    Sinon droite
    Si aucun des trois (haut gauche droite) faire demi-tour
    Si les quatre possibles faire choix aléatoire
     */

}
