package fr.univ_lorraine.model;

import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class BlueGhost extends Ghost{
	Random rand = new Random(System.currentTimeMillis());


	public BlueGhost(Vector2 pos, World w){
		super(pos, w);
		setDirection(UP);
	}

	@Override
	void rechercheDir(int currX, int currY) {
		this.aleaDir(currX, currY);
	}
}
