package fr.univ_lorraine.model;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Vector2;
import fr.univ_lorraine.PathFinding.Inondation;
import fr.univ_lorraine.screens.GameScreen;

import java.util.Random;


public abstract class Ghost extends Movable {
	Random rand = new Random(System.currentTimeMillis());
	protected Inondation shortPath;

    public final static int POURSUITE = 0, FUITE = 1, MORT = 2;
    public final static float FEARSPEED = 1.5f;
    public final static float DEATSPEED = 3f;
    public final static float SPAWNCOOLDOWN = 3f; //ChangeDir s'execute toutes les GameScreen.FRAME ms
	public final static float FEARCOOLDOWN = 20f;
	private float fearCooldown;
    private float cooldown;
    int etat;
    Vector2 SpawnPos;

    float baseSpeed;

    public Ghost(Vector2 pos, World world, float speed){
        super(pos, world, speed);
        SpawnPos = new Vector2(pos);
		this.baseSpeed = speed;
        this.resurect();
		this.shortPath = new Inondation(world);
    }

    @Override
	void changeState(){
    	if(cooldown > 0)
    		cooldown -= this.tickSpeed;

    	if(fearCooldown > 0){
    		fearCooldown -= this.tickSpeed;
    		if(fearCooldown <= 0) {
    			this.setSpeed(baseSpeed);
				this.etat = POURSUITE;
			}
		}
	}

    @Override
    void changeDir(int currX, int currY){

		int typeActuel = this.world.getMaze().getMap((int)this.pos.x, (int)this.pos.y);

        if(cooldown > 0) {
			this.setDirection(Movable.NOTHING);
			/*Le cooldown ne s'effectue que lorsque l'on est sur une position reel
			 * Une fois sur cette position, on ne bouge plus donc on reste sur une position reel
			 * Cela s'execute donc bien a chaque GameScreen.FRAME*/
		}
        else{
        	if(typeActuel == 3)
				switch(etat){
					case MORT:
						if(this.pos.equals(this.SpawnPos))
							this.resurect();
						else
							this.cheminRetour(currX, currY);


						break;

					default://On est cense sortir
						this.getOut(currX, currY);
				}
			else if (typeActuel == 2)
				switch(etat){
					case MORT:
						this.cheminRetour(currX, currY);

						if(this.pos.equals(this.SpawnPos))
							this.resurect();

						break;

					case FUITE:
						this.rechercheFuiteDit(currX, currY);

						break;

					case POURSUITE:
						this.rechercheDir(currX, currY);
				}
			else
				ContinueOnPath(currX, currY);

        }
    }

    private void getOut(int currX, int currY){
    	this.setDirection(
    			shortPath.getDirection(
    					new GridPoint2(currX, currY),
						new GridPoint2(
								(int) this.world.getPacman().getPosition().x,
								(int) this.world.getPacman().getPosition().y)
		));
	}

    protected void aleaDir(int currX, int currY){
    	int rdm;
    	do{
    		rdm = rand.nextInt(4);
		}while(!tryDir(currX, currY, rdm));
		this.setDirection(rdm);
	}

    abstract void rechercheDir(int currX, int currY);
    abstract void rechercheFuiteDit(int currX, int currY);

    private void cheminRetour(int currX, int currY) {
    	this.setDirection(shortPath.getDirection(
    			new GridPoint2(currX, currY),
				new GridPoint2((int)SpawnPos.x, (int)SpawnPos.y)));
    }

    public void kill(){
    	super.imADeadGhost();
    	this.setSpeed(DEATSPEED);
    	this.etat = MORT;
    	this.fearCooldown = 0f;
	}

	public void resurect(){
    	super.imALivingGhost();
    	this.setDirection(NOTHING);
    	this.etat=POURSUITE;
    	this.cooldown=SPAWNCOOLDOWN;
    	this.fearCooldown=0;
    	this.setSpeed(baseSpeed);
	}

	public void fear(){
    	if(etat != MORT) {
    		this.setSpeed(FEARSPEED);
			this.etat = FUITE;
			this.fearCooldown = Ghost.FEARCOOLDOWN;
		}
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

					if(tryDir(currX, currY, RIGHT))
						this.setDirection(RIGHT);
					else {
						if (tryDir(currX, currY, LEFT))
							this.setDirection(LEFT);
						else
							this.setDirection(DOWN);
					}
					break;
				case RIGHT:
					if(tryDir(currX, currY, UP))
						this.setDirection(UP);
					else {
						if (tryDir(currX, currY, DOWN))
							this.setDirection(DOWN);
						else
							this.setDirection(LEFT);
					}
					break;
				case DOWN:
					if(tryDir(currX, currY, RIGHT))
						this.setDirection(RIGHT);
					else {
						if (tryDir(currX, currY, LEFT))
							this.setDirection(LEFT);
						else
							this.setDirection(UP);
					}
					break;
				case LEFT:
					if(tryDir(currX, currY, UP))
						this.setDirection(UP);
					else {
						if (tryDir(currX, currY, DOWN))
							this.setDirection(DOWN);
						else
							this.setDirection(RIGHT);
					}
					break;
			}
		}
    }

    //Vérifie si la direction paramétrée est juste
	//Si le fantome veut aller tout droit et qu'il est face à un mur, retourner faux pour
	//Séléctionner une autre direction
    protected boolean tryDir(int currX, int currY, int direction){
    	if(direction == 4)
    		return false;
		int nextx = getNextX(currX, direction);
		int nexty = getNextY(currY, direction);

		int type = this.world.getMaze().getMap(nextx, nexty);
		if(type == 0 || (type == 3 && this.etat != MORT))
			return false;


		return true;
	}

	public int getEtat() {
    	return this.etat;
	}

	public void afterReset(){
		this.resurect();
	}

	public float getFearcooldown(){
    	return fearCooldown;
	}
}
