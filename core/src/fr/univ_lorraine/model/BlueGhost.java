package fr.univ_lorraine.model;

import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class BlueGhost extends Ghost{
	Random rand = new Random(System.currentTimeMillis());


	public BlueGhost(Vector2 pos, World w){
		super(pos, w, 2.5f);
		setDirection(UP);
	}

	@Override
	void rechercheDir(int currX, int currY) {
		int nb = rand.nextInt(2);

		if (nb == 0)
			this.aleaDir(currX, currY);
		else {
			int pacX = (int) world.getPacman().getPosition().x;
			int pacY = (int) world.getPacman().getPosition().y;

			//se rapprocher en X
			if(currX > pacX)
				if(this.tryDir(currX, currY, LEFT)){
					this.setDirection(LEFT);
					return;
				}

			if(currX < pacX)
				if(this.tryDir(currX, currY, RIGHT)){
					this.setDirection(RIGHT);
					return;
				}


			//se rapprocher en Y
			if(currY > pacY)
				if(this.tryDir(currX, currY, DOWN)){
					this.setDirection(DOWN);
					return;
				}

			if(currY < pacY)
				if(this.tryDir(currX, currY, UP)){
					this.setDirection(UP);
					return;
				}


			//continuer dans la meme dir pour eviter les demi tour inutile
			if(this.tryDir(currX, currY, this.getdirection()))
				return;


			//rien ne fonctionne
			this.aleaDir(currX, currY);

		}
	}

	@Override
	void rechercheFuiteDit(int currX, int currY){

		int nb = rand.nextInt(2);

		if (nb == 0)
			this.aleaDir(currX, currY);
		else {
			int pacX = (int) world.getPacman().getPosition().x;
			int pacY = (int) world.getPacman().getPosition().y;

			//seloigner en X
			if(currX > pacX)
				if(this.tryDir(currX, currY, LEFT)){
					this.setDirection(RIGHT);
					return;
				}

			if(currX < pacX)
				if(this.tryDir(currX, currY, RIGHT)){
					this.setDirection(LEFT);
					return;
				}


			//seloigner en Y
			if(currY > pacY)
				if(this.tryDir(currX, currY, DOWN)){
					this.setDirection(UP);
					return;
				}

			if(currY < pacY)
				if(this.tryDir(currX, currY, UP)){
					this.setDirection(DOWN);
					return;
				}


			//continuer dans la meme dir pour eviter les demi tour inutile
			if(this.tryDir(currX, currY, this.getdirection()))
				return;


			//rien ne fonctionne
			this.aleaDir(currX, currY);

		}
	}
}
