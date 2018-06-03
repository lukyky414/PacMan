package fr.univ_lorraine.model;

import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class PinkGhost extends Ghost{

    public PinkGhost(Vector2 pos, World w){
        super(pos, w);
        setDirection(UP);
    }

    @Override
    void rechercheDir(int currX, int currY){
    	int pacX = (int) world.getPacman().getPosition().x;
		int pacY = (int) world.getPacman().getPosition().y;

    	//se rapprocher en X
		if(currX > pacX)
			if(this.tryDir(currX, currY, LEFT)) return;

		if(currX < pacX)
			if(this.tryDir(currX, currY, RIGHT)) return;


        //se rapprocher en Y
		if(currY > pacY)
			if(this.tryDir(currX, currY, DOWN)) return;

		if(currY < pacY)
			if(this.tryDir(currX, currY, UP)) return;


		//continuer dans la meme dir pour eviter les demi tour inutile
		if(this.tryDir(currX, currY, this.getdirection()))return;


		//rien ne fonctionne
		this.aleaDir(currX, currY);
    }

	@Override
	void rechercheFuiteDit(int currX, int currY){
		int pacX = (int) world.getPacman().getPosition().x;
		int pacY = (int) world.getPacman().getPosition().y;

		//seloigner en X
		if(currX > pacX)
			if(this.tryDir(currX, currY, RIGHT)) return;

		if(currX < pacX)
			if(this.tryDir(currX, currY, LEFT)) return;


		//seloigner en Y
		if(currY > pacY)
			if(this.tryDir(currX, currY, UP)) return;

		if(currY < pacY)
			if(this.tryDir(currX, currY, DOWN)) return;


		//continuer dans la meme dir pour eviter les demi tour inutile
		if(this.tryDir(currX, currY, this.getdirection()))return;


		//rien ne fonctionne
		this.aleaDir(currX, currY);
	}


}
