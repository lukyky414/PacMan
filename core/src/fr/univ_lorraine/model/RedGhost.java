package fr.univ_lorraine.model;

import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class RedGhost extends Ghost {



    public RedGhost(Vector2 pos, World w){
        super(pos, w, 3f);
        setDirection(UP);
    }

	@Override
	void rechercheDir(int currX, int currY) {
		this.aleaDir(currX, currY);
	}

	@Override
	void rechercheFuiteDit(int currX, int currY) {
		this.aleaDir(currX, currY);
	}
}
