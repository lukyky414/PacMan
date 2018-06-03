package fr.univ_lorraine.model;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Vector2;
import fr.univ_lorraine.PathFinding.Inondation;

import java.util.ArrayList;
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
		Vector2 tmp;
		float tmpDist;
		float distanceMax = 0;
		int max = Movable.NOTHING;

		for(int dir = 0; dir <4; dir++){
			tmp = getNextIntersection(currX, currY, dir);

			tmpDist = tmp.dst(this.world.getPacman().getPosition());
			if(tmpDist > distanceMax){
				max = dir;
				distanceMax = tmpDist;
			}

		}

		this.setDirection(max);
	}

	private Vector2 getNextIntersection(int currX, int currY, int direction){
		int nextX = getNextX(currX, direction);
		int nextY = getNextY(currY, direction);

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
