package fr.univ_lorraine.model;

import com.badlogic.gdx.math.Vector2;
import fr.univ_lorraine.PathFinding.Inondation;

import java.util.Random;

public class YellowGhost extends Ghost {

	Inondation inonde = new Inondation();


	public YellowGhost(Vector2 pos, World w){
		super(pos, w);
		setDirection(UP);
	}

	@Override
	void rechercheDir(int currX, int currY) {
		inonde.getDirection(this.getPosition(), world.getPacman().getPosition());
	}

	@Override
	void rechercheFuiteDit(int currX, int currY) {
		this.aleaDir(currX, currY);
	}
}
