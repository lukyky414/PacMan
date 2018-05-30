package fr.univ_lorraine.model;

import com.badlogic.gdx.math.Vector2;
import fr.univ_lorraine.screens.GameScreen;

import java.util.Random;


public abstract class Ghost extends Movable {
	Random rand = new Random(System.currentTimeMillis());

    public final static int POURSUITE = 0, FUITE = 1, MORT = 2;
    public final static float SPAWNCOOLDOWN = 3f; //ChangeDir s'execute toutes les GameScreen.FRAME ms
	public final static float FEARCOOLDOWN = 15f;
	private float fearCooldown;
    private float cooldown;
    int etat;
    Vector2 SpawnPos;

    public Ghost(Vector2 pos, World world){
        super(pos, world);
        SpawnPos = new Vector2(pos);
        this.resurect();
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
        	switch(etat){
				case MORT:
					this.cheminRetour(currX, currY);

					break;

				case FUITE:
					if(fearCooldown > 0){
						this.fearCooldown -= GameScreen.FRAME;
						this.rechercheFuiteDit(currX, currY);
						break;
					}
					else{
						this.etat = POURSUITE;
					}


				case POURSUITE:
					if (typeActuel == 2 || typeActuel == 3)
						this.rechercheDir(currX, currY);
					else
						ContinueOnPath(currX, currY);

					break;
			}
        }
    }

    protected void aleaDir(int currX, int currY){
    	while(!tryDir(currX, currY, rand.nextInt(4))){}
	}

    abstract void rechercheDir(int currX, int currY);
    abstract void rechercheFuiteDit(int currX, int currY);

    private void cheminRetour(int currX, int currY) {



        //TODO Utiliser le plus court chemin avec inondation pour revenir à SpawnPos

    }

    public void kill(){
    	super.imADeadGhost();
    	this.etat = MORT;
	}

	public void resurect(){
    	super.imALivingGhost();
    	this.etat=POURSUITE;
    	this.cooldown=SPAWNCOOLDOWN;
    	this.fearCooldown=0;
	}

	public void fear(){
    	this.etat = FUITE;
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
    protected boolean tryDir(int currX, int currY, int direction){
		int nextx = getNextX(currX, direction);
		int nexty = getNextY(currY, direction);

		int type = this.world.getMaze().getMap(nextx, nexty);
		if(type == 0)
			return false;

		if(direction == NOTHING)
			return false;

		int typeActuel = this.world.getMaze().getMap((int)this.pos.x, (int)this.pos.y);
		if(type == 3 && typeActuel != 3 && etat != MORT)
			return false;

		this.setDirection(direction);
		return true;
	}

	public int getEtat() {
    	return this.etat;
	}

	public void afterReset(){
		this.resurect();
	}
}
