package fr.univ_lorraine.model;

import com.badlogic.gdx.math.Vector2;
import fr.univ_lorraine.model.Movable;
import fr.univ_lorraine.screens.GameScreen;


public abstract class Ghost extends Movable {

    public final static int POURSUITE = 0, FUITE = 1, MORT = 2;
    public final static float SPAWNCOOLDOWN = 3f; //ChangeDir s'execute toutes les GameScreen.FRAME ms
    private float cooldown = SPAWNCOOLDOWN;
    int etat = POURSUITE;
    Vector2 SpawnPos;

    public Ghost(Vector2 pos, World world){
        super(pos, world);
        super.imAGhost();
        SpawnPos = pos;
    }

    @Override
    void changeDir(int currX, int currY){
		int typeActuel = this.world.getMaze().getMap((int)this.pos.x, (int)this.pos.y);
        if(cooldown > 0) {
			this.cooldown -= GameScreen.FRAME;
			this.setDirection(Movable.NOTHING);
			/*Le cooldown ne s'effectue que lorsque l'on est sur une position reel
			 * Une fois sur cette position, on ne bouge plus donc on reste sur une position reel
			 * Cela s'execute donc bien a chaque GameScreen.FRAME*/
		}
        else{
            if(etat == MORT)
                this.cheminRetour(currX, currY);
            else {
				if (typeActuel == 2 || typeActuel == 3)
					this.setDirection(this.rechercheDir(currX, currY));
				else
					ContinueOnPath(currX, currY);
			}
        }
    }

    abstract int rechercheDir(int currX, int currY);

    private void cheminRetour(int currX, int currY) {



        //TODO Utiliser le plus court chemin avec inondation pour revenir à SpawnPos

    }


    //Fait en sorte qu'un chemin unique soit parcouru jusqu'au bout
	//et que le fantome ne s'arrête pas dès qu'il y a un angle
    private void ContinueOnPath(int currX, int currY){
    	int nextX = getNextX(currX, getdirection());
    	int nextY = getNextY(currY, getdirection());

		int type = this.world.getMaze().getMap(nextX, nextY);
		if(type == 0 || (type == 3 && this.etat != MORT)){
			switch(this.getdirection()){
				case UP:
					if(!tryDir(currX+1, currY, RIGHT))
						if(!tryDir(currX-1, currY, LEFT))
							this.setDirection(DOWN);
					break;
				case RIGHT:
					if(!tryDir(currX, currY+1, UP))
						if(!tryDir(currX, currY-1, DOWN))
							this.setDirection(LEFT);
					break;
				case DOWN:
					if(!tryDir(currX+1, currY, RIGHT))
						if(!tryDir(currX-1, currY, LEFT))
							this.setDirection(UP);
					break;
				case LEFT:
					if(!tryDir(currX, currY+1, UP))
						if(!tryDir(currX, currY-1, DOWN))
							this.setDirection(RIGHT);
					break;
			}
		}
    }

    //Vérifie si la direction paramétrée est juste
	//Si le fantome veut aller tout droit et qu'il est face à un mur, retourner faux pour
	//Séléctionner une autre direction
    private boolean tryDir(int nextX, int nextY, int direction){
		int type = this.world.getMaze().getMap(nextX, nextY);
		if(type == 0 || (type == 3 && this.etat != MORT))
			return false;
		this.setDirection(direction);
		return true;
	}

	public int getEtat() {
    	return this.etat;
	}
}
