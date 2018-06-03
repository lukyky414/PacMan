package fr.univ_lorraine.model;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Vector2;
import fr.univ_lorraine.PathFinding.Inondation;

import java.util.Random;

public class YellowGhost extends Ghost {


	public YellowGhost(Vector2 pos, World w){
		super(pos, w);
		setDirection(UP);
	}

	@Override
	void rechercheDir(int currX, int currY) {
		Pacman pac = this.world.getPacman();
		this.setDirection(shortPath.getDirection(new GridPoint2(currX, currY), new GridPoint2((int)pac.getPosition().x, (int)pac.getPosition().y)));
	}

	@Override
	void rechercheFuiteDit(int currX, int currY) {

		this.aleaDir(currX, currY);
	}
}
