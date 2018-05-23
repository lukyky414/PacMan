package fr.univ_lorraine.model;

import com.badlogic.gdx.math.Vector2;
import fr.univ_lorraine.model.Movable;
import fr.univ_lorraine.screens.GameScreen;


public class Ghost extends Movable {

    public final static int SPAWN = 0, POURSUITE = 1, FUITE = 2, MORT = 3;
    public final static float SPAWNCOOLDOWN = 3f; //ChangeDir s'execute toutes les GameScreen.FRAME ms
    private float cooldown = SPAWNCOOLDOWN;
    int etat = SPAWN;
    Vector2 SpawnPos;

    public Ghost(Vector2 pos, World world){
        super(pos, world);
        SpawnPos = pos;
    }

    @Override
    void changeDir(){
        if(cooldown > 0)
            this.cooldown -= GameScreen.FRAME;
        else{
            if(etat == MORT)
                this.cheminRetour();
            else
                /* recherche la prochaine direction si pos = 2 ou 3
                aller vers un 3 seulement si on est dans un 3 ou si on et mort

                Pas de chemin au spawn -> recherche du chemin comme si 3 &tait une intersection
                La sortie de la prison est une intersection donc pas de probleme*/
                this.setDirection(this.rechercheDir());
        }
    }

    abstract int rechercheDir();

    private void cheminRetour() {

        //Utiliser le plus court chemin avec inondation pour revenir à SpawnPos
    }

    void ContinueOnPath(int nextX, int nextY){
        //En fonction de la dir actuelle regarder la prochaine direction
        // Si la prochaine dir impossible tester virage vers gauche puis droite
        // Si g et d impossible demi-tour
    }

}
