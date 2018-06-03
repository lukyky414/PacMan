package fr.univ_lorraine.model;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Vector2;
import fr.univ_lorraine.PathFinding.Inondation;

import java.util.Random;

public class YellowGhost extends Ghost {

	Inondation inonde;


	public YellowGhost(Vector2 pos, World w){
		super(pos, w);
		setDirection(UP);
		inonde = new Inondation(w, w.getPacman());
	}

	@Override
	void rechercheDir(int currX, int currY) {
		this.setDirection(inonde.getDirection(new GridPoint2(currX, currY)));
	}

	@Override
	void rechercheFuiteDit(int currX, int currY) {
		this.aleaDir(currX, currY);
	}
}
